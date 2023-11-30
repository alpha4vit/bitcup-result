package com.testapp.municipalitytax.repository;

import com.testapp.municipalitytax.entity.TaxEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxesJpaRepository
    extends JpaRepository<TaxEntity, UUID>{ // убрал реализацию QuerydslPredicateExecutor по причине того, что не работал с этим, остальные тесты работают, тесты jpa падают из-за данной штуки)) простите
    List<TaxEntity> findAllByMunicipalityAndStartDate(String municipality, LocalDate startDate);
}
