package com.commit451.unitytogradle;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

/**
 * Stuff
 */
class Utils {

    static void copyFromResourcesToDir(File folder, String resource) throws IOException, URISyntaxException {
        URL url = Resources.getResource(resource);
        File file = new File(url.toURI());
        FileUtils.copyFileToDirectory(file, folder);
    }

    static String loadResourceAsString(String resourceName) throws IOException {
        URL url = Resources.getResource(resourceName);
        return Resources.toString(url, Charsets.UTF_8);
    }

    static String loadFileAsString(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, Charsets.UTF_8);
    }

    static String replaceInFile(String resourceName, String textToReplace, String text) throws IOException {
        String content = loadResourceAsString(resourceName);
        return replaceInLoadedFile(content, textToReplace, text);
    }

    static String replaceInLoadedFile(String fileContents, String textToReplace, String text) {
        return  fileContents.replaceAll(textToReplace, text);
    }
}
