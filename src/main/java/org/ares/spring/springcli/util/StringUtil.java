package org.ares.spring.springcli.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class StringUtil {

    /*******************************************
                Spring String Util
     ******************************************/

    public static String getControllerName(final String name) {
        if (!name.contains("Controller")) {
            return name;
        }

        return Arrays.toString(name.split("Controller"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addControllerLabel(final String className) {
        return !className.contains("Controller") ? className + "Controller" : className;
    }

    public static String getRepositoryName(final String name) {
        if (!name.contains("Repository")) {
            return name;
        }

        return Arrays.toString(name.split("Repository"))
                .replace("[", "")
                .replace("]", "");
    }

    public static String addRepositoryLabel(final String className) {
        return !className.contains("Repository") ? className + "Repository" : className;
    }

    public static String addServiceLabel(final String className) {
        return !className.contains("Service") ? className + "Service" : className;
    }

//    // The warning is strange as it works fine.
//    public static String getValidControllerType(String controllerType) {
//        if (!controllerType.equals("Rest") || !controllerType.equals("RestController") || !controllerType.equals("Controller")) {
//            return "RestController";
//        }
//
//        return controllerType;
//    }



}
