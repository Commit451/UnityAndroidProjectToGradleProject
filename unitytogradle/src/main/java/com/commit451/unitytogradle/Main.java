package com.commit451.unitytogradle;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createOutermostFiles(Structure structure) throws IOException, URISyntaxException {
        File wrapperFolder = new File(structure.project, "gradle" + File.separator + "wrapper");
        wrapperFolder.mkdirs();

        Utils.copyFromResourcesToDir(structure.project, "build.gradle");
        Utils.copyFromResourcesToDir(wrapperFolder, "gradle-wrapper.properties");
        Utils.copyFromResourcesToDir(wrapperFolder, "gradle-wrapper.jar");
        Utils.copyFromResourcesToDir(structure.project, "gradlew");
        Utils.copyFromResourcesToDir(structure.project, "gradlew.bat");
        Utils.copyFromResourcesToDir(structure.project, "settings.gradle");
    }

    private static void moveFiles(Structure structure, UnityProject project) throws IOException {
        FileUtils.copyDirectoryToDirectory(project.assets, structure.main);
        for (File file : project.src.listFiles()) {
            FileUtils.copyDirectoryToDirectory(file, structure.java);
        }
        FileUtils.copyDirectoryToDirectory(project.res, structure.main);
        FileUtils.copyDirectoryToDirectory(project.libs, structure.app);
        FileUtils.copyFileToDirectory(project.manifest, structure.main);
    }

}
