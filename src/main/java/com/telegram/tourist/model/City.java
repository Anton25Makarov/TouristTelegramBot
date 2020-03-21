package com.telegram.tourist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(schema = "TOURIST", name = "CITY")
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @NotBlank
  @Size(max = 30)
  @Column(name = "NAME")
  private String name;

  @Size(max = 255)
  @Column(name = "DESCRIPTION")
  private String description;

}
