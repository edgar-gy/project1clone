package com.example.demo.test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.json.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class ApiExplorer {
    public static void main(String[] args) throws IOException, ParseException, org.json.simple.parser.ParseException {
    	

		List<Map<String, Object>> listSender = new ArrayList<Map<String, Object>>();
    	
    	StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_trrsrt_api"); /*URL*/
    	urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=aasHED3bst0g88b83CZtSxBMy4E9Hs8SolO7YN6%2FuZrdVtkMin4ESISde9XX6CC2vlfhyExKSVG7%2FgKzwnhxgA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
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
        
        String trrsrtNm ="";
        String rdnmadr = "";
        String lnmadr = "";
        String latitude = "";
        String longitude = "";
        String trrsrtIntrcn = "";
        
        for(int i = 0 ; i<parse_items.size();i++){
    		Map<String, Object> map = new HashMap<>();
        			object = (JSONObject) parse_items.get(i);
                    trrsrtNm = (String) object.get("trrsrtNm"); 
                    rdnmadr = (String) object.get("rdnmadr"); 
                    lnmadr = (String) object.get("lnmadr"); 
                    latitude = (String) object.get("latitude"); 
                    longitude = (String) object.get("longitude"); 
                    trrsrtIntrcn = (String) object.get("trrsrtIntrcn"); 
                    map.put("trrsrtNm", trrsrtNm);
                    map.put("rdnmadr", rdnmadr);
                    map.put("lnmadr", lnmadr);
                    map.put("latitude", latitude);
                    map.put("longitude", longitude);
                    map.put("trrsrtIntrcn", trrsrtIntrcn);
                    listSender.add(map);
                   // System.out.println(listSender); 
                    
        }
     
        	
        for(int i=0;i<listSender.size();i++) {
        	listSender.get(i);
        	
        }  
    }   

}
