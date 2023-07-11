package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.TwilioRequestDto;

public interface ITwilioHandler {
    void sendMessage(TwilioRequestDto twilioRequestDto);
}
