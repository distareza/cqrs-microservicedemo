package com.example.cqrsmicroservicedemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TextUtil {

	public static void printJson(Object jsonObject) throws JsonProcessingException {
		System.out.println( toJsonText(jsonObject) );		
	}
	
	public static String toJsonText(Object jsonObject) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
	}
}
