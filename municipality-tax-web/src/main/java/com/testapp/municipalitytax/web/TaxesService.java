package com.testapp.municipalitytax.web;

import com.testapp.municipalitytax.web.payload.*;
import java.util.UUID;

public interface TaxesService {
  UUIDResponse addTax(AddTaxRequest addTaxRequest);

  void updateTax(UUID taxId, UpdateTaxRequest addTaxRequest);

  TaxResponse findTax(String municipality, String date);

  TaxListResponse getAllMunicipalityTaxes();

}
