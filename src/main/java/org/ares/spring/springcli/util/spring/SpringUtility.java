package org.ares.spring.springcli.util.spring;

import org.ares.spring.springcli.util.YamlHandler;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class SpringUtility {
    private static String filterName(String name) {
        return Arrays.toString(name.split("Controller"))
                .replace("[", "")
                .replace("]", "");
    }

    public static boolean modelExists(String name) {
       return Files.exists(Paths.get(new YamlHandler().getSpringModelPath() + filterName(name) + ".java"));
    }

    public static String getModelImport(final String name) {
        return new YamlHandler().getPackageSpringModelPath() + name + ";";
    }

}
