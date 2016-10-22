package com.google.cloud.pubsub.client.demos.appengine.emulator;

import com.travellazy.google.pubsub.util.GCloudClientPubSub;

/**
 * Author: wge
 * Date: 19/09/2016
 * Time: 11:04
 */
public interface GCloudPubSubEmulator extends GCloudClientPubSub
{
    String showRegisteredCallbacks(String fullTopicName);
}
