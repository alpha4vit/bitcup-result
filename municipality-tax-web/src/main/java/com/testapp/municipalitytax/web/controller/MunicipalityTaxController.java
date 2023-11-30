package com.testapp.municipalitytax.web.controller;

import com.testapp.municipalitytax.web.validator.AddTaxRequestValidator;
import com.testapp.municipalitytax.web.TaxesService;
import com.testapp.municipalitytax.web.payload.*;
import java.util.UUID;

import com.testapp.municipalitytax.web.validator.UpdateTaxRequestValidator;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taxes")
@Validated
public class MunicipalityTaxController {

  private final TaxesService taxesService;
  private final AddTaxRequestValidator addTaxRequestValidator;
  private final UpdateTaxRequestValidator updateTaxRequestValidator;

  public MunicipalityTaxController(TaxesService taxesService, AddTaxRequestValidator addTaxRequestValidator, UpdateTaxRequestValidator updateTaxRequestValidator) {
    this.taxesService = taxesService;
    this.addTaxRequestValidator = addTaxRequestValidator;
    this.updateTaxRequestValidator = updateTaxRequestValidator;
  }

  /**
   * Adds new municipality tax record
   *
   * @param addTaxRequest body municipality is case-sensitive schedule is case-insensitive date is
   *     accepted in format yyyy.mm.dd tax is between 0 and 1
   * @return UUID of created record
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @ResponseBody
  UUIDResponse addMunicipalityTax(@RequestBody AddTaxRequest addTaxRequest, BindingResult bindingResult) {
    addTaxRequestValidator.validate(addTaxRequest, bindingResult);
    return taxesService.addTax(addTaxRequest);
  }

  /**
   * Edit municipality tax values by id
   *
   * @param taxId UUID
   * @param updateTaxRequest body schedule is case-insensitive date is accepted in format yyyy.mm.dd
   *     tax is between 0 and 1
   * @return UUID of created record
   */
  @PutMapping(value = "/{taxId}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  void updateMunicipalityTax(
      @PathVariable("taxId") UUID taxId, @RequestBody UpdateTaxRequest updateTaxRequest, BindingResult bindingResult) {
    updateTaxRequestValidator.validate(updateTaxRequest, bindingResult);
    taxesService.updateTax(taxId, updateTaxRequest);
  }

  /**
   * Find municipality tax record by municipality and date
   *
   * @param municipality case-sensitive
   * @param date accepted in format yyyy.mm.dd
   * @return TaxResponse list of taxes applied with chosen municipality and date
   */
  @GetMapping(value = "/{municipality}/{date}")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  TaxResponse findMunicipalityTax(
      @PathVariable("municipality") String municipality, @PathVariable("date") String date) {
    return taxesService.findTax(municipality, date);
  }

  /**
   * Find all municipality taxes
   *
   * @return TaxResponse list of all taxes in all municipalities
   */
  @GetMapping(value = "/all")
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  TaxListResponse getAllMunicipalityTaxes() {
    return taxesService.getAllMunicipalityTaxes();
  }
}
