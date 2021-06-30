package org.ares.spring.springcli.commands;

import org.apache.velocity.VelocityContext;
import org.ares.spring.springcli.util.StringUtil;
import org.ares.spring.springcli.util.TemplateBuilder;
import org.ares.spring.springcli.util.YamlHandler;
import org.ares.spring.springcli.Buildable;
import org.ares.spring.springcli.util.spring.SpringUtility;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "make:repository")
public class CreateRepositoryCommand implements Runnable, Buildable {

    @Parameters
    String name;

    @Parameters(defaultValue = "")
    String modelType;

    final static String PROPERTY_KEY = "repository_location";

    final static String TEMPLATE = "\\Repository.vm";

    final static String PACKAGE_NAME = new YamlHandler().getPackageName(PROPERTY_KEY);

    @Override
    public VelocityContext buildContext() {
        VelocityContext context = new VelocityContext();
        context.put("PACKAGE_NAME", PACKAGE_NAME);
        context.put("CLASS_NAME", StringUtil.addRepositoryLabel(name));
        context.put("TYPE", this.setModelName(name, modelType));
        context.put("MODEL_IMPORT", this.setModelImport(name, modelType));
        return context;
    }

    @Override
    public void run() {
        new TemplateBuilder(PROPERTY_KEY, StringUtil.addRepositoryLabel(name), TEMPLATE, buildContext()).buildCommand();
    }

    private boolean modelTypeExists(final String modelType) {
        return !modelType.equals("") && SpringUtility.modelExists(modelType);
    }

    private String setModelName(final String name, final String modelType) {
        if (modelTypeExists(modelType)) {
            return modelType;
        } else {
            final String modelName = StringUtil.getRepositoryName(name);
            if (SpringUtility.modelExists(modelName)) {
               return modelName;
            }
        }
        return "";
    }

    private String setModelImport(final String name, final String modelType) {
        if (modelTypeExists(modelType)) {
            return SpringUtility.getModelImport(modelType);
        } else {
            final String modelName = StringUtil.getRepositoryName(name);
            if (SpringUtility.modelExists(modelName)) {
                return SpringUtility.getModelImport(modelName);
            }
        }
        return "";
    }
}
