package com.telegram.tourist.bot.command.factory;

import static com.telegram.tourist.bot.command.BotCommandConstant.BYE_COMMAND;
import static com.telegram.tourist.bot.command.BotCommandConstant.HELLO_COMMAND;
import static com.telegram.tourist.bot.command.BotCommandConstant.HELP_COMMAND;
import static com.telegram.tourist.bot.command.BotCommandConstant.START_COMMAND;

import com.telegram.tourist.bot.command.BotResponseCommand;
import com.telegram.tourist.bot.command.SpecialCommands;
import com.telegram.tourist.bot.command.TextCommands;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class CommandsFactory {

  public static BotResponseCommand createCommand(Message message) {
    switch (message.getText()) {
      case START_COMMAND:
        return SpecialCommands.startCommand(message);
      case HELP_COMMAND:
        return SpecialCommands.helpCommand(message);
      case HELLO_COMMAND:
        return SpecialCommands.helloCommand(message);
      case BYE_COMMAND:
        return SpecialCommands.byeCommand(message);
      default:
        return TextCommands.findCityCommand(message);
    }
  }

}
