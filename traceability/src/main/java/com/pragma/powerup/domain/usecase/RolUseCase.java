package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.spi.IRolPersistencePort;

public class RolUseCase implements IRolServicePort {
    private final IRolPersistencePort rolPersistencePort;
    public RolUseCase(IRolPersistencePort rolPersistencePort){ this.rolPersistencePort = rolPersistencePort;}

    @Override
    public RolModel getRol(String rolId) {
        return rolPersistencePort.getRol(rolId);
    }
}
