package com.oleirosoftware.backendlivelo.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.oleirosoftware.backendlivelo.model.Client;

@Repository
@Component
public interface ClientRepository extends JpaRepository<Client, Long> {

  List<Client> findByName(String name);

  Optional<Client> findById(Long id);
}
