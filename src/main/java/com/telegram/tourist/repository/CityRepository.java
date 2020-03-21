package com.telegram.tourist.repository;

import com.telegram.tourist.model.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

  List<City> findByName(String name);

  boolean existsCityByNameAndIdIsNot(String name, Integer id);

  boolean existsCityByName(String name);

}
