package com.commit451.unitytogradle;

import java.io.File;

/**
 * The project structure
 */
public class Structure {

    public static Structure create() {
        Structure structure = new Structure();
        structure.project = new File("gradle-project");
        structure.project.mkdir();

        structure.app = new File(structure.project, "app");
        structure.app.mkdir();

        structure.libs = new File(structure.app, "libs");
        structure.libs.mkdir();

        structure.main = new File(structure.app, "src" + File.separator + "main");
        structure.main.mkdirs();

        structure.assets = new File(structure.main, "assets");
        structure.assets.mkdir();

        structure.java = new File(structure.main, "java");
        structure.java.mkdir();

        structure.jniLibs = new File(structure.main, "jniLibs");
        structure.jniLibs.mkdir();

        structure.res = new File(structure.main, "res");
        structure.res.mkdir();
        return structure;
    }

    public File project;
    public File app;
    public File libs;
    public File main;
    public File assets;
    public File java;
    public File jniLibs;
    public File res;

    private Structure() {
    }
}
