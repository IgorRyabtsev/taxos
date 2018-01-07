package com.tool.taxonomy.controller;

import com.tool.taxonomy.exception.csv.CsvIOException;
import com.tool.taxonomy.exception.excel.ExcelIOException;
import com.tool.taxonomy.model.Research;
import com.tool.taxonomy.model.Taxonomy;
import com.tool.taxonomy.service.CollectionService;
import com.tool.taxonomy.service.TaxonomyService;
import com.tool.taxonomy.util.Filter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("${api.root}")
public class TaxonomyController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private TaxonomyService taxonomyService;

    @RequestMapping(value = "/importTaxonomy", headers = "content-type=multipart/*", method = RequestMethod.POST)
    @ApiOperation(value = "Imports taxonomy", notes = "Imports taxonomy from given file and name of current taxonomy")
    public final ResponseEntity importTaxonomy(@RequestParam("taxonomyName") final String taxonomyName,
                                               @RequestParam("taxonomy") final MultipartFile file) throws IOException, CsvIOException {
        taxonomyService.importTaxonomyFromCSV(file, taxonomyName);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/importCollection", headers = "content-type=multipart/*", method = RequestMethod.POST)
    @ApiOperation(value = "Imports collection with researchs", notes = "Imports collection with researchs from given .xlsx file")
    public final ResponseEntity importCollection(@RequestParam("collection") final MultipartFile file) throws ExcelIOException {
        System.out.println(file.getOriginalFilename());
        collectionService.importResearchFromExcel(file);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/getRecord", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the record from collection", notes = "Returns the record from collection with researchs by given id")
    public final ResponseEntity<Research> getRecord(@RequestParam("id") final String id) {
        return new ResponseEntity<>(collectionService.getResearchById(Long.valueOf(id)), HttpStatus.OK);
    }

    @RequestMapping(value = "/suggestTaxonomy", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of taxonomies", notes = "Returns the list of taxonomies by taxonomy name " +
            "and prefix ordered by name. List maximim size is 10")
    public final List<? extends Taxonomy> suggestTaxonomy(@RequestParam("taxonomyName") final String taxonomyName,
                                                          @RequestParam("prefix") final String prefix) {
        return taxonomyService.getTaxonomiesByPrefixName(taxonomyName, prefix);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ApiOperation(value = "Returns the list of taxonomy by filter")
    public final ResponseEntity<List<Research>> searchTaxonomy(@ModelAttribute("filter") final Filter filter) {
        return new ResponseEntity<>(taxonomyService.getResearchsByFilter(filter), HttpStatus.OK);
    }
}
