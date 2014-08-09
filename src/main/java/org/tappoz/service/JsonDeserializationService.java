package org.tappoz.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tappoz.bean.AvatarPlayer;

import java.io.IOException;

@Service
public class JsonDeserializationService<T> {

    @Autowired
    ObjectMapper objectMapper;

    public T deSerializeThis(String json, Class pojoClass) throws IOException {
        return (T) objectMapper.readValue(json, pojoClass);
    }

    public static AvatarPlayer deSerializeAvatarPlayer(String json) throws IOException {
        ObjectMapper thisObjectMapper = new ObjectMapper();
        return thisObjectMapper.readValue(json, AvatarPlayer.class);
    }

    public static String serializeThis(Object obj) throws JsonProcessingException {
        ObjectMapper thisObjectMapper = new ObjectMapper();
        return thisObjectMapper.writeValueAsString(obj);
    }
}
