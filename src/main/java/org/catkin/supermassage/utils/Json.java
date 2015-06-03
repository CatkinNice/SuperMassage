package org.catkin.supermassage.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Json {

	private static final ObjectMapper mapper= new ObjectMapper()
	.setSerializationInclusion(Include.NON_NULL);
	
	private Json() {
		
	}

	public static String toJson(Object obj) throws Exception {
		return mapper.writeValueAsString(obj);
	}

	public static <T> T parse(String json, Class<T> clazz) throws Exception {
		return mapper.readValue(json, clazz);
	}

	public static <T> T parse(String json, TypeReference<T> typeRef) throws Exception {
		return mapper.readValue(json, typeRef);
	}
	
	/**
	 * Use Gson ==> pom.xml:
	 * 
	 * <dependency>
	 *	<groupId>com.google.code.gson</groupId>
	 *	<artifactId>gson</artifactId>
	 * </dependency>
	 */
	 {
/*		private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		public static <T> T fromJson(String json, Class<T> clazz) {
			return gson.fromJson(json, clazz);
		}
		
		public static String toJson(Object obj) {
			if (obj instanceof String) {
				obj = new JsonParser().parse((String) obj);
			}
			return gson.toJson(obj);
		}*/
	}
}
