package com.oleirosoftware.backendlivelo.enums.converter;

import javax.persistence.AttributeConverter;
import com.oleirosoftware.backendlivelo.enums.SexRepresentationEnum;

public class SexRepresentationEnumConverter implements AttributeConverter<SexRepresentationEnum, String> {

  @Override
  public String convertToDatabaseColumn(SexRepresentationEnum attribute) {
    return attribute != null ? attribute.getDesc() : null;
  }

  @Override
  public SexRepresentationEnum convertToEntityAttribute(String dbData) {

    return dbData != null ? SexRepresentationEnum.fromValor(dbData) : null;
  }

}
