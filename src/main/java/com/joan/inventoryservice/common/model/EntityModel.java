package com.joan.inventoryservice.common.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.util.UUID;

@MappedSuperclass
@Getter
public class EntityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
