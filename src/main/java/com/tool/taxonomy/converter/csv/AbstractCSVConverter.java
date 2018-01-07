package com.tool.taxonomy.converter.csv;

import com.tool.taxonomy.exception.csv.CsvIOException;
import com.tool.taxonomy.model.Taxonomy;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public abstract class AbstractCSVConverter<T extends Taxonomy> {
    final int NUMBER_OF_COLUMNS = 3;
    final int ID = 0;
    final int PARENT_ID = 1;
    final int NAME = 2;
    final Long MOCK_ID = -1L;
    final Long MOCK_PARENT_ID = null;
    final String MOCK_NAME = null;

    abstract T importTaxonomy(final MultipartFile multipartFile) throws IOException, CsvIOException;
    Long getId(final String id) {
        return id.equals("") ? -1 : Long.valueOf(id);
    }
    abstract List<T> prepareForSaving(final List<T> taxonomies);
}
