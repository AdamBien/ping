package com.airhacks.ping.control;

import java.util.Map;
import java.util.stream.Collector;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author airhacks.com
 */
public interface JsonCollectors {

    public static Collector<Map.Entry<String, String>, ?, JsonObjectBuilder> toJsonBuilder() {
        return Collector.of(Json::createObjectBuilder, (t, u) -> {
            t.add(u.getKey(), u.getValue());
        }, JsonCollectors::merge);
    }

    static JsonObjectBuilder merge(JsonObjectBuilder left, JsonObjectBuilder right) {
        JsonObjectBuilder retVal = Json.createObjectBuilder();
        JsonObject leftObject = left.build();
        JsonObject rightObject = right.build();
        leftObject.keySet().stream().forEach((key) -> retVal.add(key, leftObject.get(key)));
        rightObject.keySet().stream().forEach((key) -> retVal.add(key, rightObject.get(key)));
        return retVal;
    }
}
