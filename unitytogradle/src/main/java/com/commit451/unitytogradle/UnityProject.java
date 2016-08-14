package com.commit451.unitytogradle;

import java.io.File;
import java.io.IOException;

/**
 * Defines the structure of the current Unity project
 */
public class UnityProject {

    public static UnityProject from(String pathToRootOfProject) throws IOException {
        UnityProject unityProject = new UnityProject();
        unityProject.project = new File(pathToRootOfProject);
        unityProject.assets = new File(unityProject.project, "assets");
        unityProject.libs = new File(unityProject.project, "libs");
        unityProject.res = new File(unityProject.project, "res");
        unityProject.src = new File(unityProject.project, "src");
        unityProject.manifest = new File(unityProject.project, "AndroidManifest.xml");
        unityProject.proguard = new File(unityProject.project, "proguard-unity.txt");

        String fileContent = Utils.loadFileAsString(unityProject.manifest);
        unityProject.packageName = parsePackageNameFromManifest(fileContent);
        unityProject.versionName = parseVersionNameFromManifest(fileContent);
        unityProject.versionCode = parseVersionCodeFromManifest(fileContent);
        unityProject.minSdkVersion = parseMinSdkVersion(fileContent);
        unityProject.targetSdkVersion = parseTargetSdkVersion(fileContent);
        return unityProject;
    }

    private static String parsePackageNameFromManifest(String fileContent) throws IOException {
        String text = "package=\"";
        int indexOfStart = fileContent.indexOf(text) + text.length();
        int inextOfEnd = fileContent.indexOf("\"", indexOfStart);
        return fileContent.substring(indexOfStart, inextOfEnd);
    }

    private static String parseVersionNameFromManifest(String fileContent) throws IOException {
        String text = "versionName=\"";
        int indexOfStart = fileContent.indexOf(text) + text.length();
        int inextOfEnd = fileContent.indexOf("\"", indexOfStart);
        return fileContent.substring(indexOfStart, inextOfEnd);
    }

    private static String parseVersionCodeFromManifest(String fileContent) throws IOException {
        String text = "versionCode=\"";
        int indexOfStart = fileContent.indexOf(text) + text.length();
        int inextOfEnd = fileContent.indexOf("\"", indexOfStart);
        return fileContent.substring(indexOfStart, inextOfEnd);
    }

    private static String parseMinSdkVersion(String fileContent) throws IOException {
        String text = "minSdkVersion=\"";
        int indexOfStart = fileContent.indexOf(text) + text.length();
        int inextOfEnd = fileContent.indexOf("\"", indexOfStart);
        return fileContent.substring(indexOfStart, inextOfEnd);
    }

    private static String parseTargetSdkVersion(String fileContent) throws IOException {
        String text = "targetSdkVersion=\"";
        int indexOfStart = fileContent.indexOf(text) + text.length();
        int inextOfEnd = fileContent.indexOf("\"", indexOfStart);
        return fileContent.substring(indexOfStart, inextOfEnd);
    }

    public File project;
    public File assets;
    public File libs;
    public File res;
    public File src;
    public File manifest;
    public File proguard;

    public String packageName;
    public String versionName;
    public String versionCode;
    public String minSdkVersion;
    public String targetSdkVersion;

    private UnityProject() {
    }


}
