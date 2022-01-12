package com.example.demo.Util;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Util {
	public static boolean emptyChk(Object obj) {
		
		if(obj != null) {
			
			if(obj.getClass().getSimpleName().equals("String")) {
				return obj == null || ((String)obj).trim().equals("");
			}
			
			return false;
		}
		
		return true; 
	}
	
	//객체 null field의 Name을 ArrayList로 반환
	public static ArrayList<String> fieldChk(Object obj) {
		
		Field[] cardField = obj.getClass().getDeclaredFields();
		ArrayList<String> nullField = new ArrayList<>();
		
		try {
			for (Field f : cardField) {
				f.setAccessible(true);
				if(Util.emptyChk(f.get(obj)) && !f.getName().equals("tagStatus")) {
					nullField.add(f.getName());
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return nullField;
	}
	
	public static String jsReplace(String msg, String uri) {
		
		if(msg == null) {
			msg = "";
		}
		
		String script = """
				<script>
					const msg = '%s'.trim();
					
					if(msg.length > 0){
						alert(msg);
					}
					
					location.replace('%s');
				</script>
				""";
		
		return String.format(script, msg, uri);
	}
}
