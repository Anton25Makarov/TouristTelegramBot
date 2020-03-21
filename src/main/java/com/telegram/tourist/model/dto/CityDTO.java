package com.telegram.tourist.model.dto;

import com.telegram.tourist.model.validation.state.OnCreate;
import com.telegram.tourist.model.validation.state.OnUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CityDTO {

  @Null(groups = {OnCreate.class})
  @NotNull(groups = {OnUpdate.class})
  private Integer id;

  @NotBlank(groups = {OnCreate.class, OnUpdate.class})
  @Size(max = 30, groups = {OnCreate.class, OnUpdate.class})
  private String name;

  @Size(max = 255, groups = {OnCreate.class, OnUpdate.class})
  private String description;

}
