package org.ares.spring.springcli.commands;

import org.apache.velocity.VelocityContext;
import org.ares.spring.springcli.util.StringUtil;
import org.ares.spring.springcli.util.TemplateBuilder;
import org.ares.spring.springcli.util.YamlHandler;
import org.ares.spring.springcli.Buildable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:repository")
public class CreateRepositoryCommand implements Runnable, Buildable {

    @Parameters()
    String name;

    final static String PROPERTY_KEY = "repository_location";

    final static String TEMPLATE = "\\spring\\Repository.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();
        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addRepositoryLabel(name));
        context.put("TYPE", "");
        context.put("ID", "");

        return context;
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, name, TEMPLATE, buildContext()).buildCommand();
    }

}
