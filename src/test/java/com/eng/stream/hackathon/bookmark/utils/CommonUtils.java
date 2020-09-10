package com.eng.stream.hackathon.bookmark.utils;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonUtils {
	
	public  static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (IOException e) {
        	return "{'Error': 'error in converting to JSON object'}";
        }
    }
	
}
