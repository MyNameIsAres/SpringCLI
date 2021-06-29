package org.ares.spring.springcli.commands;

import org.apache.velocity.VelocityContext;
import org.ares.spring.springcli.util.TemplateBuilder;
import org.ares.spring.springcli.util.YamlHandler;
import org.ares.spring.springcli.Buildable;
import org.ares.spring.springcli.util.spring.SpringModelUtil;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:model")
public class CreateModelCommand implements Runnable, Buildable {

    @Parameters()
    private String name;

    @Parameters(defaultValue = "")
    String options;

    final static String PROPERTY_KEY = "model_location";

    final static String TEMPLATE = "\\Model.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);


    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();

        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", name);
        context.put("NAME", name);

        return context;
    }

    @Override
    public void run() {

        System.out.println(options + " These are the options I get!");

        new SpringModelUtil().handleArguments(options);

        new TemplateBuilder(PROPERTY_KEY, name, TEMPLATE, buildContext()).buildCommand();
    }
}
