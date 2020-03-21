package com.telegram.tourist.controller;

import com.telegram.tourist.converter.CityDTOConverter;
import com.telegram.tourist.model.City;
import com.telegram.tourist.model.dto.CityDTO;
import com.telegram.tourist.model.validation.state.OnCreate;
import com.telegram.tourist.model.validation.state.OnUpdate;
import com.telegram.tourist.service.CityService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {

  private final CityService cityService;
  private final CityDTOConverter cityDTOConverter;

  @Autowired
  public CityController(
      CityService cityService,
      CityDTOConverter cityDTOConverter) {
    this.cityService = cityService;
    this.cityDTOConverter = cityDTOConverter;
  }

  @GetMapping
  public ResponseEntity<List<CityDTO>> getCities(@RequestParam(required = false) String name) {
    List<City> foundCities = cityService.getCities(name);
    return new ResponseEntity<>(cityDTOConverter.toDTO(foundCities), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CityDTO> getCityById(@PathVariable int id) {
    City foundCity = cityService.getCityById(id);
    return new ResponseEntity<>(cityDTOConverter.toDTO(foundCity), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CityDTO> createCity(@Validated({OnCreate.class}) CityDTO cityDTO) {
    City city = cityDTOConverter.fromDTO(cityDTO);
    City savedCity = cityService.saveCity(city);
    return new ResponseEntity<>(cityDTOConverter.toDTO(savedCity), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CityDTO> updateCity(
      @RequestParam int id, @Validated({OnUpdate.class}) CityDTO cityDTO) {
    City city = cityDTOConverter.fromDTO(cityDTO);
    City updatedCity = cityService.updateCity(city, id);
    return new ResponseEntity<>(cityDTOConverter.toDTO(updatedCity), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCity(@PathVariable int id) {
    cityService.deleteCity(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
