package org.ares.spring.springcli.commands;

import org.ares.spring.springcli.Buildable;
import org.apache.velocity.VelocityContext;

import org.ares.spring.springcli.util.StringUtil;
import org.ares.spring.springcli.util.TemplateBuilder;
import org.ares.spring.springcli.util.YamlHandler;
import org.ares.spring.springcli.util.spring.SpringUtility;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:controller")
public class CreateControllerCommand implements Runnable, Buildable {

    @Parameters()
    String name;

    @Parameters(defaultValue = "Rest")
    String controllerType = "";

    @Parameters(defaultValue = "true")
    String createCrudMethods = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    final static String PROPERTY_KEY = "controller_location";

    final static String templatePrefix = "\\controller\\";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();
        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", name);
//        context.put("CONTROLLER_TYPE",  StringUtil.getValidControllerType(controllerType));

        context.put("TYPE", StringUtil.getControllerName(name));
        context.put("LOWER_CASE_TYPE", StringUtil.getControllerName(name.toLowerCase()));
        context.put("TYPE_PLURAL", name + "s");

        return context;
    }

    private String createCrudIfModelExists() {
        if (SpringUtility.modelExists(name)) {
          return templatePrefix + "ControllerCrud.vm";
        }
        // Not as clean but necessary for now.
        if (createCrudMethods.equals("false")) {
            return templatePrefix + "Controller.vm";
        }

        return templatePrefix + "Controller.vm";
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, name, createCrudIfModelExists(), buildContext()).buildCommand();
    }


}
