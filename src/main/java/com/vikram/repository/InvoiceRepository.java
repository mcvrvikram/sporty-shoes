package com.vikram.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vikram.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	List<Invoice> findAllByDateBetween(LocalDate fromDate,LocalDate toDate );
}
