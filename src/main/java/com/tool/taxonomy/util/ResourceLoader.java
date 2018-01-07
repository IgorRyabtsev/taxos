package com.tool.taxonomy.util;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component
public class ResourceLoader {

    public final MockMultipartFile getResourceAsMultipartFileExcel(final String filePath) throws IOException {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        MockMultipartFile firstFile = null;
        try {
            firstFile = new MockMultipartFile("excel", "pk.xlsx",
                    "application/vnd.ms-excel",
                    Files.readAllBytes(Paths.get(classLoader.getResource(filePath).toURI())));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return firstFile;
    }

    public final MockMultipartFile getResourceAsMultipartFileCsv(final String filePath) throws IOException {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        MockMultipartFile firstFile = null;
        try {
            firstFile = new MockMultipartFile("excel", "taxonomy.csv",
                    "text/csv",
                    Files.readAllBytes(Paths.get(classLoader.getResource(filePath).toURI())));
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return firstFile;
    }

}
