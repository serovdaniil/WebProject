package com.epam.jwd.finalProject.command.common.function;

import com.epam.jwd.finalProject.command.factory.Command;
import com.epam.jwd.finalProject.command.factory.CommandRequest;
import com.epam.jwd.finalProject.command.factory.CommandResponse;
import com.epam.jwd.finalProject.controller.RequestFactory;

/**
 * This command switches the languageThis command is used to log in to your personal account
 *
 * @author Daniil Serov
 */
public class ChangeLanguageCommand implements Command {
    private static final String LANGUAGE_ATTRIBUTE_NAME = "lang";
    private static final String RUSSIAN_LANGUAGE_ATTRIBUTE = "ru_RU";
    private static final String BELORUSSIAN_LANGUAGE = "be_BE";
    private static final String FRANCE_LANGUAGE = "fr_FR";
    private static final String ENGLISH_LANGUAGE = "fr_FR";
    private final RequestFactory requestFactory = RequestFactory.getInstance();

    private ChangeLanguageCommand() {
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        final String currentUrl = request.getURL();
        switch (currentUrl) {
            case (RUSSIAN_LANGUAGE_ATTRIBUTE):
                request.addAttributeToJsp(LANGUAGE_ATTRIBUTE_NAME, RUSSIAN_LANGUAGE_ATTRIBUTE);
                break;
            case(BELORUSSIAN_LANGUAGE):
                request.addAttributeToJsp(LANGUAGE_ATTRIBUTE_NAME, BELORUSSIAN_LANGUAGE);
                break;
            case(FRANCE_LANGUAGE):
                request.addAttributeToJsp(LANGUAGE_ATTRIBUTE_NAME, FRANCE_LANGUAGE);
                break;
            default:request.addAttributeToJsp(LANGUAGE_ATTRIBUTE_NAME, ENGLISH_LANGUAGE);
        }
            return requestFactory.createRedirectResponse(currentUrl);
    }

    public static ChangeLanguageCommand getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ChangeLanguageCommand INSTANCE = new ChangeLanguageCommand();
    }
}