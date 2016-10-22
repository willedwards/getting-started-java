package com.google.cloud.pubsub.client.demos.appengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RegisterPubSubController
{

    public static final String ASYNC_ENDPOINT = "/register/async";

    @Autowired
    private MessagesService messagesService;

    @RequestMapping(value = "/register/{topic}/{subscriberId}/{callbackURL}", method = POST, produces = "application/json")
    public ResponseEntity registerCallback(@PathVariable String topic,
                                           @PathVariable String subscriberId,
                                           @PathVariable String callbackUrl ){

        try {
            messagesService.createAsyncCallbackURLForTopic(callbackUrl,topic,subscriberId);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/send/{topic}/{message}", method = GET, produces = "application/json")
    public ResponseEntity sendMessage(@PathVariable String topic, @PathVariable String message){

        try {
            messagesService.sendMessage(topic,message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity(HttpStatus.OK);
    }



}
