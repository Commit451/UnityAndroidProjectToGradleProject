package com.commit451.unitytogradle

import com.google.common.base.Charsets
import com.google.common.io.Resources
import okio.Okio
import java.io.File
import java.io.IOException
import java.net.URISyntaxException
import java.nio.file.Files

/**
 * Stuff
 */
internal object Utils {

    @Throws(IOException::class, URISyntaxException::class)
    fun copyFromResourcesToDir(folder: File, resource: String) {
        copyFromResourcesToDirWithAlternateName(folder, resource, resource)
    }

    @Throws(IOException::class, URISyntaxException::class)
    fun copyFromResourcesToDirWithAlternateName(folder: File, resource: String, newName: String) {
        val inputStream = Utils::class.java.getClassLoader().getResourceAsStream(resource)
        val source = Okio.buffer(Okio.source(inputStream))
        val newFile = File(folder, newName)
        val sink = Okio.buffer(Okio.sink(newFile))
        sink.writeAll(source)
        sink.flush()
        sink.close()
        source.close()
    }

    @Throws(IOException::class)
    fun loadResourceAsString(resourceName: String): String {
        val url = Resources.getResource(resourceName)
        return Resources.toString(url, Charsets.UTF_8)
    }

    @Throws(IOException::class)
    fun loadFileAsString(file: File): String {
        val encoded = Files.readAllBytes(file.toPath())
        return String(encoded, Charsets.UTF_8)
    }
}
