package com.google.cloud.pubsub.client.demos.appengine.emulator;/**
 * Author: wge
 * Date: 19/09/2016
 * Time: 10:44
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GloudPubSubClientEmulator implements GCloudPubSubEmulator {
    private static final Logger log = LoggerFactory.getLogger(GloudPubSubClientEmulator.class);
    private final String applicationName;
    private RestTemplate asyncRestTemplate = new RestTemplate();

    //topic to list of subscriptions.
    private Map<String, List<SubscriptionAndEndpoint>> topic2Callbacks = new ConcurrentHashMap<>();


    public GloudPubSubClientEmulator(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public void createAsyncCallbackURLForTopic(final String fullCallbackUrlEndpoint, final String fullTopicName, final String fullSubscriptionName) throws IOException {
        SubscriptionAndEndpoint subscriptionAndEndpoint = new SubscriptionAndEndpoint(this, fullCallbackUrlEndpoint, fullSubscriptionName);
        List<SubscriptionAndEndpoint> existing = topic2Callbacks.get(fullTopicName);

        if (existing == null) {
            topic2Callbacks.put(fullTopicName, Arrays.asList(subscriptionAndEndpoint));
        } else {
            existing.add(subscriptionAndEndpoint);
        }
    }

    @Override
    public String showRegisteredCallbacks(String fullTopicName) {
        StringBuilder sb = new StringBuilder();

        topic2Callbacks.get(fullTopicName).stream().forEach(k -> sb.append(k.toString()));

        return sb.toString();
    }

    @Override
    public void sendMessage(String relativeTopicName, String message) throws IOException {
        List<SubscriptionAndEndpoint> endpointsInterestedInTopic = topic2Callbacks.get(relativeTopicName);
        for (SubscriptionAndEndpoint callback : endpointsInterestedInTopic) {

            String urlEndpoint = callback.getFullCallbackUrlEndpoint();
            String s = callback.getFullSubscriptionName();
            HttpEntity<String> request = new HttpEntity<>(message);
            ResponseEntity<Map> map = asyncRestTemplate.postForEntity(urlEndpoint,request,Map.class);
            //needs a nack.

        }
    }

}
