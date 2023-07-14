package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.RolModel;

public interface IRolServicePort {
    RolModel getRol(String rolId);
}
