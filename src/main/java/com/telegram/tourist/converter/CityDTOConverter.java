package com.telegram.tourist.converter;

import com.telegram.tourist.model.City;
import com.telegram.tourist.model.dto.CityDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CityDTOConverter {

  public City fromDTO(CityDTO dto) {
    City city = new City();
    BeanUtils.copyProperties(dto, city);
    return city;
  }

  public CityDTO toDTO(City city) {
    CityDTO dto = new CityDTO();
    BeanUtils.copyProperties(city, dto);
    return dto;
  }

  public List<City> fromDTO(List<CityDTO> dtos) {
    return dtos.stream()
        .map(this::fromDTO)
        .collect(Collectors.toList());
  }

  public List<CityDTO> toDTO(List<City> cities) {
    return cities.stream()
        .map(this::toDTO)
        .collect(Collectors.toList());
  }

}
