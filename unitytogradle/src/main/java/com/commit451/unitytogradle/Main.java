package com.commit451.unitytogradle;

import com.google.common.base.Charsets;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

/**
 * Cool stuff
 */
public class Main {

    static final boolean DEBUG = true;

    static Scanner sReader;

    public static void main(String[] args) {

        sReader = new Scanner(System.in);

        String projectPath;
        if (DEBUG) {
            projectPath = "C:\\Users\\Jawn\\Desktop\\AndroidWearOrientationTest";
        } else {
            System.out.println("Please drag and drop in the generated project from Unity");
            projectPath = sReader.nextLine().trim();
        }

        Structure structure = new Structure();

        try {
            UnityProject unityProject = UnityProject.from(projectPath);
            createOutermostFiles(structure);
            moveFiles(structure, unityProject);
            generateBuildGradle(structure, unityProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createOutermostFiles(Structure structure) throws IOException, URISyntaxException {
        File wrapperFolder = new File(structure.project, "gradle" + File.separator + "wrapper");
        wrapperFolder.mkdirs();

        Utils.copyFromResourcesToDir(structure.project, ".gitignore");
        Utils.copyFromResourcesToDir(structure.project, "build.gradle");
        Utils.copyFromResourcesToDir(wrapperFolder, "gradle-wrapper.properties");
        Utils.copyFromResourcesToDir(wrapperFolder, "gradle-wrapper.jar");
        Utils.copyFromResourcesToDir(structure.project, "gradlew");
        Utils.copyFromResourcesToDir(structure.project, "gradlew.bat");
        Utils.copyFromResourcesToDir(structure.project, "settings.gradle");
        Utils.copyFromResourcesToDir(structure.project, "gradle.properties");
    }

    private static void moveFiles(Structure structure, UnityProject project) throws IOException {
        FileUtils.copyDirectoryToDirectory(project.assets, structure.main);
        for (File file : project.src.listFiles()) {
            FileUtils.copyDirectoryToDirectory(file, structure.java);
        }
        FileUtils.copyDirectoryToDirectory(project.res, structure.main);
        for (File file : project.libs.listFiles()) {
            if (file.getName().endsWith(".jar")) {
                FileUtils.copyFileToDirectory(file, structure.libs);
            } else {
                FileUtils.copyDirectoryToDirectory(file, structure.jniLibs);
            }
        }
        FileUtils.copyFileToDirectory(project.manifest, structure.main);
        FileUtils.copyFileToDirectory(project.proguard, structure.main);
        File movedProguard = new File(structure.main, project.proguard.getName());
        File newProguardName = new File(structure.app, "proguard-rules.pro");
        Files.move(movedProguard.toPath(), newProguardName.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private static void generateBuildGradle(Structure structure, UnityProject project) throws IOException {
        String content = Utils.loadResourceAsString("inner" + File.separator + "build.gradle");
        content = content.replace("$compile_sdk_version$", project.targetSdkVersion);
        content = content.replace("$target_sdk_version$", project.targetSdkVersion);
        content = content.replace("$application_id$", project.packageName);
        content = content.replace("$min_sdk_version$", project.minSdkVersion);
        content = content.replace("$version_code$", project.versionCode);
        content = content.replace("$version_name$", project.versionName);
        File innerBuildGradle = new File(structure.app, "build.gradle");
        innerBuildGradle.createNewFile();
        FileUtils.writeStringToFile(innerBuildGradle, content, Charsets.UTF_8);
    }

}
