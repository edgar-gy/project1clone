package com.example.demo.openAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.annotation.Configuration;


@Configuration
public class WeatherAPI {
	
	public List<Map<String, Object>> weatherapi() throws  Exception{
		List<Map<String, Object>> listSender = new ArrayList<Map<String, Object>>();
    	
    	StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/SfcMtlyInfoService/getMmSumry"); /*URL*/
    	urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "T4JUE2qkZe37g1SxiR07OVJuhoqbOSw%2FbmvlyFq5ixpp6dMB1EUnuPS9CgnlVuM%2Fk7Ktnlxj%2FjdxQKZ5tOubKw%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML/JSON 여부*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        String result = sb.toString();
        
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(result);
        JSONObject parse_response = (JSONObject) object.get("response");
        JSONObject parse_body = (JSONObject) parse_response.get("body");
        JSONArray parse_items = (JSONArray) parse_body.get("items");
//        
//        String trrsrtNm ="";
//        String rdnmadr = "";
//        String lnmadr = "";
//        String latitude = "";
//        String longitude = "";
//        String trrsrtIntrcn = "";
//        String prkplceCo = "";
//        String phoneNumber = "";
        
        for(int i = 0 ; i<parse_items.size();i++){
    		Map<String, Object> map = new HashMap<>();
        			object = (JSONObject) parse_items.get(i);
        			Object stnid = (String) object.get("stnid"); 
        			Object stnko = (String) object.get("stnko"); 
        			Object pa = (String) object.get("pa"); 
        			Object ps = (String) object.get("ps"); 
        			Object avgtamax = (String) object.get("avgtamax"); 
        			Object avgtamin = (String) object.get("avgtamin"); 
        			Object taavg  = (String) object.get("taavg"); 
        			Object tamin   = (String) object.get("tamin"); 
                    Object avgtgmin = (String) object.get("avgtgmin"); 
                    		
                    		
                    		
                    map.put("stnid", stnid);
                    map.put("stnko", stnko);
                    map.put("pa", pa);
                    map.put("ps", ps);
                    map.put("avgtamax", avgtamax);
                    map.put("avgtamin", avgtamin);
                    map.put("taavg", taavg);
                    map.put("tamin", tamin);
                    map.put("avgtgmin", avgtgmin);
                    listSender.add(map);
                   // System.out.println(listSender); 
                    
        }
     
        	
        for(int i=0;i<listSender.size();i++) {
        	listSender.get(i);
        	
        }  
        return listSender;
}
}
