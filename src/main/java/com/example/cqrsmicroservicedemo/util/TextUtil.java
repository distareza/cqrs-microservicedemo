package com.example.cqrsmicroservicedemo.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TextUtil {

	public static void printJson(Object jsonObject) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		System.out.println( mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject) );		
	}
	
}
