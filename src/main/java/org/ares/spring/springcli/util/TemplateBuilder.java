package org.ares.spring.springcli.util;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class TemplateBuilder {

    private final String propertyKey;
    private final String name;
    private final String template;
    private final VelocityContext context;

    private final YamlHandler yamlHandler = new YamlHandler();

    public TemplateBuilder(String propertyKey, String name, String template, VelocityContext context) {
        this.propertyKey = propertyKey;
        this.name = name;
        this.template = template;
        this.context = context;
    }

    public void buildCommand() {
        final Writer writer = this.createFileWriter(propertyKey, name);
        this.createTemplate(writer, template, context);
        this.flushFileWriter(writer);
    }

    private Writer createFileWriter(String propertyKey, String name) {
        try {
            return new FileWriter(yamlHandler.getTargetLocation(yamlHandler.getProjectPath(), yamlHandler.getKeyValue(propertyKey), name) + ".java");
        } catch (IOException ioException) {
            System.out.println(ioException.getMessage());
        }

        return null;
    }

    private void flushFileWriter(Writer writer) {
        try {
            writer.flush();
            writer.close();
        } catch (IOException | NullPointerException exception) {
            System.out.println("We couldn't flush this due to an exception!");
        }
    }

    private void createTemplate(Writer writer, String template, VelocityContext context) {
        VelocityEngine engine = new VelocityBuilder().createVelocityEngineSpring();
        Template templateName = engine.getTemplate(template);

        try {
            engine.mergeTemplate(templateName.getName(), "UTF-8", context, writer);
        } catch(NullPointerException exception) {
            exception.printStackTrace();
            System.out.println("We can't find this directory!");
        }
    }
}
