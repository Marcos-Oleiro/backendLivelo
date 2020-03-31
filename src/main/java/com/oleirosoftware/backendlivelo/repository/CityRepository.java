package com.oleirosoftware.backendlivelo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.oleirosoftware.backendlivelo.model.City;

@Repository
@Component
public interface CityRepository extends JpaRepository<City, Long> {


  Optional<City> findByNameAndState(String name, String state);

  List<City> findByName(String name);

  List<City> findByState(String state);
}
