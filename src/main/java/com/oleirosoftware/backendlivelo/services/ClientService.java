package com.oleirosoftware.backendlivelo.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;
import com.oleirosoftware.backendlivelo.dto.ClientDto;
import com.oleirosoftware.backendlivelo.exceptions.InvalidCityIdException;
import com.oleirosoftware.backendlivelo.exceptions.InvalidRequestBodyException;
import com.oleirosoftware.backendlivelo.exceptions.InvalidUrlException;
import com.oleirosoftware.backendlivelo.exceptions.NotExistingIdException;
import com.oleirosoftware.backendlivelo.model.City;
import com.oleirosoftware.backendlivelo.model.Client;
import com.oleirosoftware.backendlivelo.parser.ClientParser;
import com.oleirosoftware.backendlivelo.repository.CityRepository;
import com.oleirosoftware.backendlivelo.repository.ClientRepository;

@Service
public class ClientService {

  @Autowired
  private CityRepository cityRepo;

  @Autowired
  private ClientRepository clientRepo;



  public ResponseEntity<ClientDto> addClient(ClientDto dto, UriComponentsBuilder uriBuilder) {

    Optional<City> op = cityRepo.findById((long) dto.getIdCity());

    if (!op.isPresent()) {
      throw new InvalidCityIdException();
    }

    dto.setName(WordUtils.capitalize(dto.getName().toLowerCase()));
    City city = op.get();
    Client client = ClientParser.paraEntity(dto, city);
    Client savedClient = clientRepo.save(client);
    URI uri = uriBuilder.path("/api/client/" + savedClient.getId()).buildAndExpand(savedClient.getId()).toUri();
    ClientDto clientDto = ClientParser.paraDto(savedClient);
    return ResponseEntity.created(uri).body(clientDto);
  }

  public ResponseEntity<List<ClientDto>> findByName(String name) {

    if (StringUtils.isBlank(name)) {
      throw new InvalidUrlException("O nome do cliente  deve ser informado.");
    }

    name = WordUtils.capitalize(name.toLowerCase());
    List<Client> clients = clientRepo.findByName(name);
    List<ClientDto> dtos = new ArrayList<>();

    clients.forEach(client -> {
      ClientDto dto = new ClientDto();
      dto = ClientParser.paraDto(client);
      dtos.add(dto);
    });
    return ResponseEntity.ok(dtos);
  }

  public ResponseEntity<ClientDto> findById(Long id) {

    if (id == null) {
      throw new InvalidUrlException("A id do cliente deve ser informada.");
    }

    Optional<Client> op = clientRepo.findById(id);

    if (!op.isPresent())
      throw new NotExistingIdException();

    ClientDto dto = ClientParser.paraDto(op.get());

    return ResponseEntity.ok(dto);
  }

  public ResponseEntity<ClientDto> changeName(Long id, String newName) {

    if (id == null)
      throw new InvalidUrlException("A id do cliente deve ser informada.");

    if (StringUtils.isBlank(newName))
      throw new InvalidRequestBodyException("O novo nome do cliente deve ser informado.");


    newName = WordUtils.capitalize(newName.toLowerCase());
    Optional<Client> op = clientRepo.findById(id);

    if (!op.isPresent())
      throw new NotExistingIdException();

    Client client = op.get();

    client.setName(newName);

    Client savedClient = clientRepo.save(client);

    ClientDto dto = ClientParser.paraDto(savedClient);
    return ResponseEntity.ok(dto);
  }

  public ResponseEntity<Object> deleteCliente(Long id) {

    if (id == null)
      throw new InvalidUrlException("A id do cliente deve ser informada.");


    Optional<Client> op = clientRepo.findById(id);

    if (!op.isPresent())
      throw new NotExistingIdException();

    clientRepo.deleteById(id);

    return ResponseEntity.noContent().build();
  }

}
