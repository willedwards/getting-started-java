package com.google.cloud.pubsub.client.demos.appengine.emulator;/**
 * Author: wge
 * Date: 19/09/2016
 * Time: 11:14
 */

public class SubscriptionAndEndpoint
{
    private GloudPubSubClientEmulator gloudPubSubClientEmulator;
    private String fullCallbackUrlEndpoint;
    private String fullSubscriptionName;

    public String getFullCallbackUrlEndpoint()
    {
        return fullCallbackUrlEndpoint;
    }

    public String getFullSubscriptionName()
    {
        return fullSubscriptionName;
    }

    public SubscriptionAndEndpoint(GloudPubSubClientEmulator gloudPubSubClientEmulator, String fullCallbackUrlEndpoint, String fullSubscriptionName)
    {
        this.gloudPubSubClientEmulator = gloudPubSubClientEmulator;
        this.fullCallbackUrlEndpoint = fullCallbackUrlEndpoint;
        this.fullSubscriptionName = fullSubscriptionName;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof SubscriptionAndEndpoint))
            return false;

        SubscriptionAndEndpoint that = (SubscriptionAndEndpoint) o;

        if (fullCallbackUrlEndpoint != null ? !fullCallbackUrlEndpoint.equals(that.fullCallbackUrlEndpoint) : that.fullCallbackUrlEndpoint != null)
            return false;
        return fullSubscriptionName != null ? fullSubscriptionName.equals(that.fullSubscriptionName) : that.fullSubscriptionName == null;

    }

    @Override
    public int hashCode()
    {
        int result = fullCallbackUrlEndpoint != null ? fullCallbackUrlEndpoint.hashCode() : 0;
        result = 31 * result + (fullSubscriptionName != null ? fullSubscriptionName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString()
    {
        return "SubscriptionAndEndpoint{" + "fullCallbackUrlEndpoint='" + fullCallbackUrlEndpoint + '\'' + ", fullSubscriptionName='" + fullSubscriptionName + '\'' + '}';
    }
}
