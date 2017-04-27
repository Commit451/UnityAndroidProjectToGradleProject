package com.commit451.unitytogradle

import com.google.common.base.Charsets
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import java.util.*

/**
 * Cool stuff
 */
object Main {

    @JvmStatic fun main(args: Array<String>) {

        val reader = Scanner(System.`in`)

        val projectPath: String
        if (args.isNotEmpty()) {
            projectPath = args[0]
        } else {
            println("Please drag and drop in the generated project from Unity")
            projectPath = reader.nextLine().trim { it <= ' ' }
        }
        val projectFolder = File(projectPath)
        if (!projectFolder.exists()) {
            println("Folder does not exist")
            return
        }

        val structure = Structure.create()

        val unityProject = UnityProject.from(projectPath)
        createOutermostFiles(structure)
        moveFiles(structure, unityProject)
        generateBuildGradle(structure, unityProject)
        println("Gradle project built successfully and can be found at")
        println(structure.project.absolutePath)
    }

    private fun createOutermostFiles(structure: Structure) {
        //Have to do it like this, since the . hidden files do not get included
        //in resources
        val gitIgnoreText = Utils.loadResourceAsString("gitignore.txt")
        val gitignore = File(structure.project, ".gitignore")
        gitignore.createNewFile()
        FileUtils.writeStringToFile(gitignore, gitIgnoreText, Charsets.UTF_8)
        val wrapperFolder = File(structure.project, "gradle" + File.separator + "wrapper")
        wrapperFolder.mkdirs()
        Utils.copyFromResourcesToDir(structure.project, "build.gradle")
        Utils.copyFromResourcesToDir(wrapperFolder, "gradle-wrapper.properties")
        Utils.copyFromResourcesToDir(wrapperFolder, "gradle-wrapper.jar")
        Utils.copyFromResourcesToDir(structure.project, "gradlew")
        Utils.copyFromResourcesToDir(structure.project, "gradlew.bat")
        Utils.copyFromResourcesToDir(structure.project, "settings.gradle")
        Utils.copyFromResourcesToDir(structure.project, "gradle.properties")
    }
    
    fun moveFiles(structure: Structure, project: UnityProject) {
        FileUtils.copyDirectoryToDirectory(project.assets, structure.main)
        for (file in project.src.listFiles()!!) {
            FileUtils.copyDirectoryToDirectory(file, structure.java)
        }
        FileUtils.copyDirectoryToDirectory(project.res, structure.main)
        for (file in project.libs.listFiles()!!) {
            if (file.name.endsWith(".jar")) {
                FileUtils.copyFileToDirectory(file, structure.libs)
            } else {
                FileUtils.copyDirectoryToDirectory(file, structure.jniLibs)
            }
        }
        FileUtils.copyFileToDirectory(project.manifest, structure.main)
        FileUtils.copyFileToDirectory(project.proguard, structure.main)
        val movedProguard = File(structure.main, project.proguard.name)
        val newProguardName = File(structure.app, "proguard-rules.pro")
        Files.move(movedProguard.toPath(), newProguardName.toPath(), StandardCopyOption.REPLACE_EXISTING)
    }

    @Throws(IOException::class)
    fun generateBuildGradle(structure: Structure, project: UnityProject) {
        var content = Utils.loadResourceAsString("innerbuild.gradle")
        content = content.replace("\$compile_sdk_version$", project.targetSdkVersion)
        content = content.replace("\$target_sdk_version$", project.targetSdkVersion)
        content = content.replace("\$application_id$", project.packageName)
        content = content.replace("\$min_sdk_version$", project.minSdkVersion)
        content = content.replace("\$version_code$", project.versionCode)
        content = content.replace("\$version_name$", project.versionName)
        val innerBuildGradle = File(structure.app, "build.gradle")
        innerBuildGradle.createNewFile()
        FileUtils.writeStringToFile(innerBuildGradle, content, Charsets.UTF_8)
    }

}
