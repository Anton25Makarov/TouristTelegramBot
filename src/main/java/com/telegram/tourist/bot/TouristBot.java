package com.telegram.tourist.bot;

import com.telegram.tourist.bot.command.BotResponseCommand;
import com.telegram.tourist.bot.command.factory.CommandsFactory;
import java.io.Serializable;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
public class TouristBot extends TelegramLongPollingBot {

  @Value("${telegram.bot.token}")
  private String token;
  @Value("${telegram.bot.username}")
  private String botUsername;

  @Override
  public void onUpdateReceived(Update update) {
    validateUpdates(update);
    BotResponseCommand command = CommandsFactory.createCommand(update.getMessage());
    sendResponse(command.createResponse());
  }

  private void validateUpdates(Update update) {
    if (!isValid(update.getMessage())) {
      handleUnsupportedMessage(update);
    }
  }

  private void handleUnsupportedMessage(Update update) {
    String errorMessage = "Message has unsupported format";
    log.warn(errorMessage, update);
    throw new UnsupportedOperationException(errorMessage);
  }

  private <T extends BotApiMethod<U>, U extends Serializable> void sendResponse(T response) {
    try {
      execute(response);
    } catch (TelegramApiException e) {
      log.error(e.getMessage());
    }
  }

  private boolean isValid(Message message) {
    return Optional.ofNullable(message)
        .map(Message::getText)
        .isPresent();
  }

  @Override
  public String getBotUsername() {
    return botUsername;
  }

  @Override
  public String getBotToken() {
    return token;
  }
}
