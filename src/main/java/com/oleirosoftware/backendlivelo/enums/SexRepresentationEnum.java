package com.oleirosoftware.backendlivelo.enums;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SexRepresentationEnum {


  MALE("M"), FEMALE("F"), OTHER("O");

  private String description;

  private static final Map<String, SexRepresentationEnum> descToEnumType = new HashMap<>();


  static {
    for (SexRepresentationEnum tipo : SexRepresentationEnum.values()) {
      descToEnumType.put(tipo.getDesc(), tipo);
    }
  }

  @JsonCreator
  public static SexRepresentationEnum fromValor(String desc) {
    return descToEnumType.get(desc);
  }

  @JsonValue
  public String getDesc() {
    return this.description;
  }
}

