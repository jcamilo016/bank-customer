package com.vobi.bank.repository;

import com.vobi.bank.entity.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {
}