package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.TwilioRequestDto;
import com.pragma.powerup.application.handler.ITwilioHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TwilioHandler implements ITwilioHandler {

    public static final String ACCOUNT_SID = "AC3d9d34adfb5e4d7252b604251e33745a";
    public static final String AUTH_TOKEN = "42dd28ca96c5f7b37301e28691130529";
    @Override
    public void sendMessage(TwilioRequestDto twilioRequestDto) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(twilioRequestDto.getNumber()),
                        new com.twilio.type.PhoneNumber("+19123725913"),
                        twilioRequestDto.getMessage())
                .create();
    }
}
