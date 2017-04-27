package com.commit451.unitytogradle

import com.google.common.base.Charsets
import com.google.common.io.Resources
import okio.Okio
import java.io.File
import java.nio.file.Files

/**
 * Stuff
 */
object Utils {

    fun copyFromResourcesToDir(folder: File, resource: String) {
        copyFromResourcesToDirWithAlternateName(folder, resource, resource)
    }

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

    fun loadResourceAsString(resourceName: String): String {
        val url = Resources.getResource(resourceName)
        return Resources.toString(url, Charsets.UTF_8)
    }

    fun loadFileAsString(file: File): String {
        val encoded = Files.readAllBytes(file.toPath())
        return String(encoded, Charsets.UTF_8)
    }
}
