package com.commit451.unitytogradle;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import okio.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;

/**
 * Stuff
 */
class Utils {

    static void copyFromResourcesToDir(File folder, String resource) throws IOException, URISyntaxException {
        copyFromResourcesToDirWithAlternateName(folder, resource, resource);
    }

    static void copyFromResourcesToDirWithAlternateName(File folder, String resource, String newName) throws IOException, URISyntaxException {
        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(resource);
        BufferedSource source = Okio.buffer(Okio.source(inputStream));
        File newFile = new File(folder, newName);
        BufferedSink sink = Okio.buffer(Okio.sink(newFile));
        sink.writeAll(source);
        sink.flush();
        sink.close();
        source.close();
    }

    static String loadResourceAsString(String resourceName) throws IOException {
        URL url = Resources.getResource(resourceName);
        return Resources.toString(url, Charsets.UTF_8);
    }

    static String loadFileAsString(File file) throws IOException {
        byte[] encoded = Files.readAllBytes(file.toPath());
        return new String(encoded, Charsets.UTF_8);
    }
}
