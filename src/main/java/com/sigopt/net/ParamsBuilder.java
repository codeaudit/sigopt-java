package com.sigopt.net;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ParamsBuilder {
    public static Map<String, Object> build(Map<String, Object> params) {
        Map<String, Object> ret = new HashMap<String, Object>();

        params = MapHelper.ensure(params);
        ret = MapHelper.merge(ret, params);
        for (Map.Entry<String, Object> entry: ret.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Map || value instanceof Collection) {
                entry.setValue(APIObject.GSON.toJson(value));
            } else if (value instanceof APIObject) {
                entry.setValue(((APIObject)value).toJson());
            }
        }
        return ret;
    }
}
