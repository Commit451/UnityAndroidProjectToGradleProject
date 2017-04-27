package com.commit451.unitytogradle

import java.io.File

/**
 * Defines the structure of the current Unity project
 */
class UnityProject private constructor() {

    lateinit var project: File
    lateinit var assets: File
    lateinit var libs: File
    lateinit var res: File
    lateinit var src: File
    lateinit var manifest: File
    lateinit var proguard: File

    lateinit var packageName: String
    lateinit var versionName: String
    lateinit var versionCode: String
    lateinit var minSdkVersion: String
    lateinit var targetSdkVersion: String

    companion object {

        fun from(pathToRootOfProject: String): UnityProject {
            val unityProject = UnityProject()
            unityProject.project = File(pathToRootOfProject)
            unityProject.assets = File(unityProject.project, "assets")
            unityProject.libs = File(unityProject.project, "libs")
            unityProject.res = File(unityProject.project, "res")
            unityProject.src = File(unityProject.project, "src")
            unityProject.manifest = File(unityProject.project, "AndroidManifest.xml")
            unityProject.proguard = File(unityProject.project, "proguard-unity.txt")

            val fileContent = Utils.loadFileAsString(unityProject.manifest)
            unityProject.packageName = parsePackageNameFromManifest(fileContent)
            unityProject.versionName = parseVersionNameFromManifest(fileContent)
            unityProject.versionCode = parseVersionCodeFromManifest(fileContent)
            unityProject.minSdkVersion = parseMinSdkVersion(fileContent)
            unityProject.targetSdkVersion = parseTargetSdkVersion(fileContent)
            return unityProject
        }

        private fun parsePackageNameFromManifest(fileContent: String): String {
            val text = "package=\""
            val indexOfStart = fileContent.indexOf(text) + text.length
            val inextOfEnd = fileContent.indexOf("\"", indexOfStart)
            return fileContent.substring(indexOfStart, inextOfEnd)
        }

        private fun parseVersionNameFromManifest(fileContent: String): String {
            val text = "versionName=\""
            val indexOfStart = fileContent.indexOf(text) + text.length
            val inextOfEnd = fileContent.indexOf("\"", indexOfStart)
            return fileContent.substring(indexOfStart, inextOfEnd)
        }

        private fun parseVersionCodeFromManifest(fileContent: String): String {
            val text = "versionCode=\""
            val indexOfStart = fileContent.indexOf(text) + text.length
            val inextOfEnd = fileContent.indexOf("\"", indexOfStart)
            return fileContent.substring(indexOfStart, inextOfEnd)
        }

        private fun parseMinSdkVersion(fileContent: String): String {
            val text = "minSdkVersion=\""
            val indexOfStart = fileContent.indexOf(text) + text.length
            val inextOfEnd = fileContent.indexOf("\"", indexOfStart)
            return fileContent.substring(indexOfStart, inextOfEnd)
        }

        private fun parseTargetSdkVersion(fileContent: String): String {
            val text = "targetSdkVersion=\""
            val indexOfStart = fileContent.indexOf(text) + text.length
            val inextOfEnd = fileContent.indexOf("\"", indexOfStart)
            return fileContent.substring(indexOfStart, inextOfEnd)
        }
    }


}
