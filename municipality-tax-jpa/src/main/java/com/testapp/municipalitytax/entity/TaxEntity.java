package com.testapp.municipalitytax.entity;

import com.querydsl.core.annotations.QueryEntity;
import lombok.*;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "taxes")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TaxEntity {
  @Id
  private UUID id;
  private String municipality;
  private Double tax;
  private LocalDate startDate;
  private LocalDate endDate;


  public TaxEntity(TaxEntity taxEntity, String municipality) {}
}
