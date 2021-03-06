package org.ares.spring.springcli.commands;

import org.apache.velocity.VelocityContext;
import org.ares.spring.springcli.util.StringUtil;
import org.ares.spring.springcli.util.TemplateBuilder;
import org.ares.spring.springcli.util.YamlHandler;
import org.ares.spring.springcli.Buildable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:service")
public class CreateServiceCommand implements Runnable, Buildable {

    @Parameters()
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    final static String PROPERTY_KEY = "service_location";

    final static String TEMPLATE = "\\spring\\Service.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();
        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addServiceLabel(name));

        return context;
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, name, TEMPLATE, buildContext()).buildCommand();
    }
}
