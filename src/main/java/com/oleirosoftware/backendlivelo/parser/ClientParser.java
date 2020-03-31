package com.oleirosoftware.backendlivelo.parser;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.beans.BeanUtils;
import com.oleirosoftware.backendlivelo.dto.ClientDto;
import com.oleirosoftware.backendlivelo.enums.SexRepresentationEnum;
import com.oleirosoftware.backendlivelo.model.City;
import com.oleirosoftware.backendlivelo.model.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientParser {


  public static Client paraEntity(ClientDto dto, City city) {

    Client client = new Client();
    BeanUtils.copyProperties(dto, client);
    client.setSex(SexRepresentationEnum.fromValor(dto.getSex().toUpperCase()));
    client.setCity(city);
    return client;
  }

  public static ClientDto paraDto(Client client) {

    ClientDto dto = new ClientDto();
    BeanUtils.copyProperties(client, dto);
    dto.setSex(client.getSex().getDesc());
    Period diff = Period.between(client.getBirthday(), LocalDate.now());
    dto.setIdCity(Integer.parseInt(client.getCity().getId().toString()));
    dto.setAge(diff.getYears());
    return dto;
  }
}
