package com.smallworld.data.reader;

import com.smallworld.TransactionDataFetcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class FileReader{
    private static final Logger logger = Logger.getLogger(TransactionDataFetcher.class.getName());

    private static final String FILE_NAME = "transactions.json";

    public static String retrieveJson() throws IOException {
        Path jsonFilePath = Path.of(FILE_NAME);
        logger.info("Retrieved file is "+jsonFilePath.toString());
        return Files.readString(jsonFilePath);
    }
}