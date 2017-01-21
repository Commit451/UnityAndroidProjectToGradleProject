package com.commit451.unitytogradle

import java.io.File

/**
 * The project structure
 */
class Structure private constructor() {

    companion object {

        fun create(): Structure {
            val structure = Structure()
            structure.project = File("gradle-project")
            structure.project.mkdir()

            structure.app = File(structure.project, "app")
            structure.app.mkdir()

            structure.libs = File(structure.app, "libs")
            structure.libs.mkdir()

            structure.main = File(structure.app, "src" + File.separator + "main")
            structure.main.mkdirs()

            structure.assets = File(structure.main, "assets")
            structure.assets.mkdir()

            structure.java = File(structure.main, "java")
            structure.java.mkdir()

            structure.jniLibs = File(structure.main, "jniLibs")
            structure.jniLibs.mkdir()

            structure.res = File(structure.main, "res")
            structure.res.mkdir()
            return structure
        }
    }

    lateinit var project: File
    lateinit var app: File
    lateinit var libs: File
    lateinit var main: File
    lateinit var assets: File
    lateinit var java: File
    lateinit var jniLibs: File
    lateinit var res: File
}
