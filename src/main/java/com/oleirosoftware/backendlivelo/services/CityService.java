package com.oleirosoftware.backendlivelo.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.oleirosoftware.backendlivelo.dto.CityDto;
import com.oleirosoftware.backendlivelo.exceptions.ExistingCityException;
import com.oleirosoftware.backendlivelo.exceptions.InvalidUrlException;
import com.oleirosoftware.backendlivelo.model.City;
import com.oleirosoftware.backendlivelo.repository.CityRepository;

@Service
public class CityService {

  @Autowired
  private CityRepository rep;

  public ResponseEntity<CityDto> addCity(CityDto dto, UriComponentsBuilder uriBuilder) throws ExistingCityException {

    dto.setName(WordUtils.capitalize(dto.getName().toLowerCase()));
    dto.setState(dto.getState().toUpperCase());
    Optional<City> op = rep.findByNameAndState(dto.getName(), dto.getState());

    if (op.isPresent()) {
      throw new ExistingCityException();
    }

    City city = new City();
    BeanUtils.copyProperties(dto, city);
    City savedCity = rep.save(city);
    URI uri = uriBuilder.path("/api/city/" + savedCity.getId()).buildAndExpand(savedCity.getId()).toUri();

    CityDto dtoResponse = new CityDto();
    BeanUtils.copyProperties(savedCity, dtoResponse);

    return ResponseEntity.created(uri).body(dtoResponse);
  }


  public ResponseEntity<List<CityDto>> findByName(String name) {

    if (StringUtils.isBlank(name)) {
      throw new InvalidUrlException("O nome da cidade deve ser informado.");
    }

    name = WordUtils.capitalize(name.toLowerCase());
    List<City> cities = rep.findByName(name);
    List<CityDto> citiesDto = new ArrayList<>();

    cities.forEach(city -> {
      CityDto dto = new CityDto();
      BeanUtils.copyProperties(city, dto);
      citiesDto.add(dto);
    });

    return ResponseEntity.ok(citiesDto);
  }

  public ResponseEntity<List<CityDto>> findByState(String state) {

    if (StringUtils.isBlank(state)) {
      throw new InvalidUrlException("O nome do estado deve ser informado.");
    }
    state = state.toUpperCase();
    List<City> cities = rep.findByState(state);
    List<CityDto> citiesDto = new ArrayList<>();

    cities.forEach(city -> {
      CityDto dto = new CityDto();
      BeanUtils.copyProperties(city, dto);
      citiesDto.add(dto);
    });

    return ResponseEntity.ok(citiesDto);
  }
}
