package com.pragma.powerup.domain.spi;


import com.pragma.powerup.domain.model.RolModel;

public interface IRolPersistencePort {
    RolModel getRol(Long rolId);
}
