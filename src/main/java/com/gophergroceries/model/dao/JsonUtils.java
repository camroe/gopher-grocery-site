package com.gophergroceries.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	public static String JsonStringFromObject(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String returnJsonString = "";
		try {
			returnJsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.debug("Could not process into a JSON Object");
			e.printStackTrace();
		}
		return returnJsonString;
	}
	
	
}
