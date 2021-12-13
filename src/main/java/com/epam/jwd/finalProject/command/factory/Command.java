package com.epam.jwd.finalProject.command.factory;

/**
 * The interface command
 *
 * @author Daniil Serov
 */
public interface Command {
    CommandResponse execute(CommandRequest request);

    static Command of(String name) {
        return CommandRegistry.of(name);
    }

}
