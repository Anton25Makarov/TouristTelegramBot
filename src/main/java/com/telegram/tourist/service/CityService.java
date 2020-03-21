package com.telegram.tourist.service;

import com.telegram.tourist.exception.AlreadyExistsException;
import com.telegram.tourist.exception.NotFoundException;
import com.telegram.tourist.model.City;
import com.telegram.tourist.repository.CityRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

  private final CityRepository cityRepository;

  @Autowired
  public CityService(CityRepository cityRepository) {
    this.cityRepository = cityRepository;
  }

  public List<City> getCities(String name) {
    return name == null ? getCities() : getCitiesByName(name);
  }

  public List<City> getCities() {
    return cityRepository.findAll();
  }

  public City getCityById(int id) {
    return cityRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("City with id='" + id + "' not found"));
  }

  public City saveCity(City city) {
    validateUniqFieldsExistence(city);
    return cityRepository.save(city);
  }

  public City updateCity(City city, int id) {
    City existingCity = getCityById(id);
    validateUniqFieldsExistence(city);
    return cityRepository.save(copyValues(existingCity, city));
  }

  public void deleteCity(int id) {
    City cityToDelete = getCityById(id);
    cityRepository.delete(cityToDelete);
  }

  private City copyValues(City target, City source) {
    target.setName(source.getName());
    target.setDescription(source.getDescription());
    return target;
  }

  private void validateUniqFieldsExistence(City city) {
    if (city.getId() == null) {
      validateUniqFieldsForSave(city);
    } else {
      validateUniqFieldsForUpdate(city);
    }
  }

  private List<City> getCitiesByName(String name) {
    return cityRepository.findByName(name);
  }

  private void validateUniqFieldsForSave(City city) {
    if (cityRepository.existsCityByName(city.getName())) {
      throw new AlreadyExistsException("City with name=" + city.getName() + " already exists");
    }
  }

  private void validateUniqFieldsForUpdate(City city) {
    if (cityRepository.existsCityByNameAndIdIsNot(city.getName(), city.getId())) {
      throw new AlreadyExistsException("City with name=" + city.getName() + " already exists");
    }
  }

}
