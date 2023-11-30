package com.testapp.municipalitytax.service;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.TaxesRepository;
import com.testapp.municipalitytax.web.TaxesService;
import com.testapp.municipalitytax.web.payload.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MunicipalityTaxService implements TaxesService {

  private final TaxesRepository taxesRepository;
  private final ConversionService conversionService;

  @Override
  public UUIDResponse addTax(AddTaxRequest addTaxRequest) {
    MunicipalityTax municipalityTax = conversionService.convert(addTaxRequest, MunicipalityTax.class);
    municipalityTax = taxesRepository.save(municipalityTax);
    return new UUIDResponse(municipalityTax.id());
  }

  @Override
  public void updateTax(UUID taxId, UpdateTaxRequest updateTaxRequest) {
    MunicipalityTax tax =  conversionService.convert(updateTaxRequest, MunicipalityTax.class);
    tax = tax.withId(taxId);
    taxesRepository.update(tax);
  }

  @Override
  public TaxResponse findTax(String municipality, String date) {
    List<MunicipalityTax> municipalityTaxes = taxesRepository.findByMunicipalityAndDate(municipality, LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd")));
    return new TaxResponse(
            municipalityTaxes.stream().map(MunicipalityTax::tax).toList());
  }

  @Override
  public TaxListResponse getAllMunicipalityTaxes() {
    List<FullTaxInfo> fullTaxInfo = taxesRepository.getAllMunicipalityTaxes()
            .stream().map(el -> conversionService.convert(el, FullTaxInfo.class))
            .toList();
    return new TaxListResponse(fullTaxInfo.size(), fullTaxInfo);
  }

}