package org.ares.spring.springcli.commands;

import org.apache.velocity.VelocityContext;
import org.ares.spring.springcli.util.StringUtil;
import org.ares.spring.springcli.util.TemplateBuilder;
import org.ares.spring.springcli.util.YamlHandler;
import org.ares.spring.springcli.Buildable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.Writer;

@Command(name = "make:repository")
public class SpringCreateRepositoryCommand implements Runnable, Buildable {

    @Parameters()
    String name;

    final static String PROPERTY_KEY = "repository_location";

    final static String TEMPLATE = "\\spring\\Repository.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();
        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addCommandLabel(name));
        context.put("TYPE", "");
        context.put("ID", "");

        return context;
    }

    @Override
    public void run() {
        TemplateBuilder templateBuilder = new TemplateBuilder();
        Writer writer = templateBuilder.createFileWriter(PROPERTY_KEY, name);
        templateBuilder.createTemplate(writer, TEMPLATE, buildContext());
        templateBuilder.flushFileWriter(writer);
    }

}
