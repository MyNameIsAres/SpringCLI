package org.ares.spring.springcli;

import org.ares.spring.springcli.commands.CreateControllerCommand;
import org.ares.spring.springcli.commands.CreateModelCommand;
import org.ares.spring.springcli.commands.CreateRepositoryCommand;
import picocli.CommandLine;
import picocli.CommandLine.*;

@Command(description = "Fancy a help message?", mixinStandardHelpOptions = true)
public class SpringCLI implements Runnable {

    public static void main(String[] args) {
        disableAnsi();
        createCommands(args);
    }

    @Override
    public void run() {
        welcomeMessage();
    }

    private static void welcomeMessage() {
        System.out.println("==============================");
        System.out.println("Welcome to SpringCLI!");
        System.out.println("==============================");
    }


    private static void disableAnsi() {
        System.setProperty("picocli.ansi", String.valueOf(false));
    }

    private static void createCommands(String[] args) {
        new CommandLine(new SpringCLI())
                .addSubcommand(new CreateControllerCommand())
                .addSubcommand(new CreateRepositoryCommand())
                .addSubcommand(new CreateModelCommand())
                .execute(args);
    }

}
