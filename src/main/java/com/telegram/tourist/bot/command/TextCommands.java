package com.telegram.tourist.bot.command;

import com.telegram.tourist.model.City;
import com.telegram.tourist.service.CityService;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class TextCommands {

  private static CityService cityService;

  @Autowired
  public TextCommands(CityService cityService) {
    TextCommands.cityService = cityService;
  }

  public static BotResponseCommand findCityCommand(Message message) {
    cityService.getCities(message.getText());
    return () -> new SendMessage()
        .setChatId(message.getChatId())
        .setText(getCityDescription(message.getText()));
  }

  private static String getCityDescription(String cityName) {
    Optional<City> city = cityService.getCities(cityName)
        .stream()
        .findFirst();

    if (city.isPresent()) {
      if (StringUtils.isNotBlank(city.get().getDescription())) {
        return city.get().getDescription();
      } else {
        return "Description is not provided for city '" + cityName + "'.";
      }
    } else {
      return "City '" + cityName + "' not found.";
    }
  }

}
