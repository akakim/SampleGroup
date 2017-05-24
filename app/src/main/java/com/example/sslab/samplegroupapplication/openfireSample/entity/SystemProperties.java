package com.example.sslab.samplegroupapplication.openfireSample.entity;

import android.util.Property;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * XMPPAdmin, All rights Reserved
 * Created by Sanjeet on 13-Aug-16.
 */
public class SystemProperties {

    /**
     * The properties.
     *
     */
    //TODO :  serialize를 꼭 이용해야하는가? 안드로이드에 적합한 Parcelable도 있음. bundle객체를 이용하려면
    // 종종 사용하게되는데 ..
    @JsonProperty("property")
    List<Property> properties;

    /**
     * Instantiates a new system properties.
     */
    public SystemProperties() {

    }

    /**
     * Gets the properties.
     *
     * @return the properties
     */
    public List<Property> getProperties() {
        return properties;
    }


    /**
     * Sets the properties.
     *
     * @param properties the new properties
     */
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
