package com.commit451.unitytogradle;

import java.io.File;

/**
 * The project structure
 */
public class Structure {

    public File project;
    public File app;
    public File main;
    public File assets;
    public File java;
    public File res;

    public Structure() {
        project = new File("project");
        project.mkdir();

        app = new File(project, "app");
        app.mkdir();

        main = new File(app, "src" + File.pathSeparator + "main");
        main.mkdirs();

        assets = new File(main, "assets");
        assets.mkdir();

        java = new File(main, "java");
        java.mkdir();

        res = new File(main, "res");
        res.mkdir();
    }
}
