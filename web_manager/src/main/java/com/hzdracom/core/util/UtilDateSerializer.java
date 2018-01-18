package com.hzdracom.core.util;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class UtilDateSerializer implements JsonSerializer<Date> {  
  
    public JsonElement serialize(Date src, Type typeOfSrc,  
            JsonSerializationContext context) {  
        return new JsonPrimitive(src.getTime()/1000);  
    }  
  
} 