package com.testapp.municipalitytax.domain;

import java.time.LocalDate;
import java.util.UUID;

public record MunicipalityTax(
    UUID id, String municipality, Double tax, LocalDate startDate, Schedule taxSchedule) {

  public MunicipalityTax withId(UUID id) {
    return new MunicipalityTax(id, this.municipality, this.tax, this.startDate, this.taxSchedule);
  }
}
