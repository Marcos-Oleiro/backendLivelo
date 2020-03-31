package com.oleirosoftware.backendlivelo.resource;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.oleirosoftware.backendlivelo.dto.CityDto;
import com.oleirosoftware.backendlivelo.exceptions.ExistingCityException;
import com.oleirosoftware.backendlivelo.services.CityService;

@RestController
@RequestMapping("/api/city")
public class CityResource {

  @Autowired
  private CityService service;

  @PostMapping
  public ResponseEntity<CityDto> add(@Valid @RequestBody CityDto dto, UriComponentsBuilder uriBuilder) throws ExistingCityException {

    return service.addCity(dto, uriBuilder);
  }

  @GetMapping("/findbycity/{name}")
  public ResponseEntity<List<CityDto>> getCitiesByName(@PathVariable("name") String name) {
    return service.findByName(name);
  }

  @GetMapping("/findbystate/{state}")
  public ResponseEntity<List<CityDto>> getCitiesByState(@PathVariable("state") String state) {
    return service.findByState(state);
  }

}
