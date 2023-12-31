package lk.krishan_mobile.util.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

@Service
public class TwilioMessageService {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACc8eca0f9151131c6d06ea0e71c4e261d";
    public static final String AUTH_TOKEN = "2e8bc5d195a32405203282984f5cbc25";

    // public void sendSMS(String number, String messageBody) {
    // Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    // Message message = Message
    // .creator(new PhoneNumber(number), new PhoneNumber("+15016536639"),
    // messageBody)
    // .create();

    /*
     * Old technology
     * String ACCOUNT_SID = "AC5eb2291fc32feefffaee1e72124744c8";
     * String AUTH_TOKEN ="3ad8aa83519e4a57147810b324f4fca2";
     * String myTwilioNumber = "+15067006522";
     * Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
     * Message.creator(new PhoneNumber(senderNumber), new
     * PhoneNumber(myTwilioNumber), message).create();
     *
     */

    // System.out.println("Message "+message.getSid());
}
