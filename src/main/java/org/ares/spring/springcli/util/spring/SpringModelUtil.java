package org.ares.spring.springcli.util.spring;

public class SpringModelUtil {

    // TODO: Better method names.
    public void handleArguments(String options) {
        if (options.equals("all")) {
            // TODO: Load method that handles creating all
            System.out.println("Creating all!");
        }

        if (options.length() > 1) {
            System.out.println(3);
            String[] optionsArray = options.split("");

            for (String item : optionsArray) {
                loadArgument(item);
            }
        }

    }

    private void loadArgument(String item) {
        System.out.println(item + " test");

        switch(item) {
            case "c":
                System.out.println("Controller options");
                break;
            case "s":
                System.out.println("Service options");
                break;
            case "r":
                System.out.println("Repository options");
                break;
            default:
                System.out.println("WHAAAH");
        }

    }

}
