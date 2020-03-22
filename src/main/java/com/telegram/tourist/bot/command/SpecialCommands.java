package com.telegram.tourist.bot.command;

import static com.telegram.tourist.bot.command.BotCommandConstant.BYE_COMMAND;
import static com.telegram.tourist.bot.command.BotCommandConstant.HELLO_COMMAND;
import static com.telegram.tourist.bot.command.BotCommandConstant.HELP_COMMAND;
import static com.telegram.tourist.bot.command.BotCommandConstant.START_COMMAND;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class SpecialCommands {

  public static BotResponseCommand startCommand(Message message) {
    return () -> new SendMessage()
        .setChatId(message.getChatId())
        .setText(
            "Hello!"
                + "\nSend a city."
                + "\nProbably we know something interesting about it :)"
                + "\nYou also can enter " + HELP_COMMAND + " to know more about bot features");
  }

  public static BotResponseCommand helpCommand(Message message) {
    return () -> new SendMessage()
        .setChatId(message.getChatId())
        .setText("There is a list of available commands:\n"
            + START_COMMAND + "\n"
            + HELP_COMMAND + "\n"
            + HELLO_COMMAND + "\n"
            + BYE_COMMAND);
  }

  public static BotResponseCommand helloCommand(Message message) {
    return () -> new SendMessage()
        .setChatId(message.getChatId())
        .setText("Hi!");
  }

  public static BotResponseCommand byeCommand(Message message) {
    return () -> new SendMessage()
        .setChatId(message.getChatId())
        .setText("Bye...");
  }

}
