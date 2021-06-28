package org.ares.spring.springcli.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringUtil {

    /*******************************************
                Spring String Util
     ******************************************/

    public static String getControllerName(String name) {
        if (!name.contains("Controller")) {
            return name;
        }

        return Arrays.toString(name.split("Controller"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addControllerLabel(String className) {
        return !className.contains("Controller") ? className + "Controller" : className;
    }

    public static String addRepositoryLabel(String className) {
        return !className.contains("Repository") ? className + "Repository" : className;
    }

    public static String addServiceLabel(String className) {
        return !className.contains("Service") ? className + "Service" : className;
    }

    // The warning is strange as it works fine.
    public static String getValidControllerType(String controllerType) {
        if (!controllerType.equals("Rest") || !controllerType.equals("RestController") || !controllerType.equals("Controller")) {
            return "RestController";
        }

        return controllerType;
    }



}
