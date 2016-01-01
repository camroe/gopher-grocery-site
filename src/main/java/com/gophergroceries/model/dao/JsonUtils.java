package com.gophergroceries.model.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringEscapeUtils;

public class JsonUtils {

	private static final Logger logger = LoggerFactory.getLogger(JsonUtils.class);

	public static String jsonStringFromObjectPretty(Object obj) {
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

	public static String javascriptEscapedJsonStringFromObject(Object obj) {
		return StringEscapeUtils.escapeEcmaScript(jsonStringFromObjectPretty(obj));
	}

	public static String javascriptEscapedJsonString(String jsonString) {
		return StringEscapeUtils.escapeEcmaScript(jsonString);
	}

}
