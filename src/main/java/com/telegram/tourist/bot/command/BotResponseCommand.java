package com.telegram.tourist.bot.command;

import java.io.Serializable;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface BotResponseCommand {

  BotApiMethod<? extends Serializable> createResponse();

}
