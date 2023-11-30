package com.testapp.municipalitytax.domain;

import java.time.LocalDate;
import java.util.List;

public interface TaxesRepository  {

  MunicipalityTax save(MunicipalityTax municipalityTax);

  int update(MunicipalityTax municipalityTax);

  List<MunicipalityTax> findByMunicipalityAndDate(String municipality, LocalDate date);

  List<MunicipalityTax> getAllMunicipalityTaxes();

}
