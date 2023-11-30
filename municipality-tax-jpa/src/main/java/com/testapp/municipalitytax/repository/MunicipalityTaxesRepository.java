package com.testapp.municipalitytax.repository;

import com.testapp.municipalitytax.domain.MunicipalityTax;
import com.testapp.municipalitytax.domain.TaxesRepository;
import com.testapp.municipalitytax.entity.TaxEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MunicipalityTaxesRepository implements TaxesRepository {

  private final TaxesJpaRepository taxesJpaRepository;
  private final ConversionService conversionService;

  @Override
  public MunicipalityTax save(MunicipalityTax municipalityTax) {
    TaxEntity taxEntity = conversionService.convert(municipalityTax, TaxEntity.class);
    taxEntity.setId(UUID.randomUUID()); // TODO исправить чтобы не повторялись
    taxesJpaRepository.save(taxEntity);
    return conversionService.convert(taxEntity, MunicipalityTax.class);
  }

  @Override
  public int update(MunicipalityTax municipalityTax) {
    Optional<TaxEntity> optionalTax = taxesJpaRepository.findById(municipalityTax.id());
    if (optionalTax.isPresent()){
      TaxEntity tax = conversionService.convert(municipalityTax, TaxEntity.class);
      tax.setMunicipality(optionalTax.get().getMunicipality());
      taxesJpaRepository.save(tax);
      return 0;
    }
    throw new RuntimeException("Tax with this UUID not found!");
  }


  @Override
  public List<MunicipalityTax> findByMunicipalityAndDate(String municipality, LocalDate date) {
    return taxesJpaRepository.findAllByMunicipalityAndStartDate(municipality, date)
            .stream()
            .map(el -> conversionService.convert(el, MunicipalityTax.class))
            .toList();
  }

  @Override
  public List<MunicipalityTax> getAllMunicipalityTaxes() {
    return taxesJpaRepository.findAll().stream().map(el -> conversionService.convert(el, MunicipalityTax.class)).toList();
  }

}
