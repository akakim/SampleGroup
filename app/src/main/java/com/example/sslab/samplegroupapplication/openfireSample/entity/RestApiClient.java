package com.example.sslab.samplegroupapplication.openfireSample.entity;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Created by SSLAB on 2017-05-24.
 */

public class RestApiClient {

    Context context;
    private ObjectMapper getConfiguredMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED, true);
        return mapper;
    }

    public RestApiClient(Context context) {
        this.context = context;
    }
}
