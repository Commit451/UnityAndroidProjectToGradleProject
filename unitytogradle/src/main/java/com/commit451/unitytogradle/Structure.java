package com.commit451.unitytogradle;

import java.io.File;

/**
 * The project structure
 */
public class Structure {

    public File project;
    public File app;
    public File libs;
    public File main;
    public File assets;
    public File java;
    public File jniLibs;
    public File res;

    public Structure() {
        project = new File("gradle-project");
        project.mkdir();

        app = new File(project, "app");
        app.mkdir();

        libs = new File(app, "libs");
        libs.mkdir();

        main = new File(app, "src" + File.separator + "main");
        main.mkdirs();

        assets = new File(main, "assets");
        assets.mkdir();

        java = new File(main, "java");
        java.mkdir();

        jniLibs = new File(main, "jniLibs");
        jniLibs.mkdir();

        res = new File(main, "res");
        res.mkdir();
    }
}
