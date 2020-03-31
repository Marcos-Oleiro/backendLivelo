package com.oleirosoftware.backendlivelo.resource;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.oleirosoftware.backendlivelo.dto.ClientDto;
import com.oleirosoftware.backendlivelo.services.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientResource {

  @Autowired
  private ClientService service;

  @PostMapping
  public ResponseEntity<ClientDto> add(@RequestBody @Valid ClientDto clientdto, UriComponentsBuilder uriBuilder) {
    return service.addClient(clientdto, uriBuilder);
  }

  @GetMapping("/findbyname/{name}")
  public ResponseEntity<List<ClientDto>> getClientsByName(@PathVariable("name") String name) {
    return service.findByName(name);
  }

  @GetMapping("/findbyid/{id}")
  public ResponseEntity<ClientDto> getClientsByName(@PathVariable("id") Long id) {
    return service.findById(id);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ClientDto> editClient(@PathVariable("id") Long id, String newName) {
    return service.changeName(id, newName);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> removeClient(@PathVariable("id") Long id) {
    return service.deleteCliente(id);
  }
}
