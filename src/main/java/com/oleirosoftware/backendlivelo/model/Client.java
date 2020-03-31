package com.oleirosoftware.backendlivelo.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.oleirosoftware.backendlivelo.enums.SexRepresentationEnum;
import com.oleirosoftware.backendlivelo.enums.converter.SexRepresentationEnumConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "client")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;


  @Column(name = "name")
  private String name;

  @Convert(converter = SexRepresentationEnumConverter.class)
  @Column(name = "sex")
  private SexRepresentationEnum sex;

  @Column(name = "birthday")
  private LocalDate birthday;

  @Transient
  private Integer age;

  @ManyToOne
  @JoinColumn(name = "id_city", referencedColumnName = "id")
  private City city;

}
