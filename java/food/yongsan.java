package net.webplate.food;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import net.webplate.vo.FoodVO;

import java.io.BufferedReader;
import java.io.IOException;

public class yongsan {

	public static void main(String[] args) throws IOException {
		
		FoodVO vo=new FoodVO();

		StringBuilder urlBuilder3 = new StringBuilder("https://api.odcloud.kr/api/15066516/v1/uddi:507e01f5-76ec-42ff-96a5-8b6ff9ce554e?page=1&perPage=33&returnType=json&serviceKey=WZBWWUz20zYgzl3HeuXW1dKiMRMeTTkH2Ak8ssfM%2BJew2%2FbOP5mr5qwRntUWbHnghtJfz6WMjW9%2BXTDGdW9qcA%3D%3D"); /*URL*/
        URL url3 = new URL(urlBuilder3.toString());
        HttpURLConnection conn3 = (HttpURLConnection) url3.openConnection();
        conn3.setRequestMethod("GET");
        conn3.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn3.getResponseCode());
        BufferedReader rd3;
        if(conn3.getResponseCode() >= 200 && conn3.getResponseCode() <= 300) {
            rd3 = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
        } else {
            rd3 = new BufferedReader(new InputStreamReader(conn3.getErrorStream()));
        }
        StringBuilder sb3 = new StringBuilder();
        String line3;
        while ((line3 = rd3.readLine()) != null) {
            sb3.append(line3);
        }
        rd3.close();
        conn3.disconnect();
        System.out.println(sb3.toString());
         
        
        try { 
            JSONParser parser3	= new JSONParser(); 
    		 JSONObject obj3 		= (JSONObject)parser3.parse(sb3.toString());
    		 JSONArray  item3 	= (JSONArray) obj3.get("data");
    		 
    		 System.out.println("JSON(obj3) : " + obj3);
    		 System.out.println("JSON(item3[]) : " + item3);
    		
    		 for(int i=0; i<item3.size();i++) {
    			 
    			 if(i==3 || i==4 || i==25) {
    				 continue;
    			 }else {
    			 JSONObject YongsanData=(JSONObject) item3.get(i);
    			

    			 long bsnsCond3=(long)YongsanData.get("??????(01??????,02??????,03??????,04??????,05??????????????????,06?????????/??????)");  //??????(01??????,02??????,03??????,04??????,05??????????????????,06?????????/??????)
    			 
    			 String bsnsCond="";
    			 
    			 if(bsnsCond3==1L) {
    				 bsnsCond="??????";
    			 }else if(bsnsCond3==2L) {
    				 bsnsCond="??????";
    			 }else if(bsnsCond3==3L) {
     				bsnsCond="??????";
    			 }else if(bsnsCond3==4L) {
    				 bsnsCond="??????"; 
    			 }else if(bsnsCond3==5L) {
    				 bsnsCond="??????????????????";
    			 
    			 }else if(bsnsCond3==6L){
    				 bsnsCond="?????????/??????";
    			 }
    			 
    			 
    			 String bsnsNm3 =(String)YongsanData.get("?????????");  //?????????
    			 String menu2 =(String)YongsanData.get("????????????");  //??????
    			 String menu=menu2.substring(7);  // ????????????: ???????????? ?????????
    			 String addr =(String)YongsanData.get("??????1");  // ??????1	
    			 String lat =(String)YongsanData.get("??????");  //??????
    			 
    			 String lng =(String)YongsanData.get("??????");  //??????
    			
    			 String tel =(String)YongsanData.get("????????????");  //??????	
    			 
    			 vo.setBsnscond(bsnsCond);
    			 vo.setBsnsnm(bsnsNm3);
    			 vo.setMenu(menu);
    			 vo.setAddr(addr);
    			 vo.setLat(lat);
    			 vo.setLng(lng);
    			 vo.setAddr(addr);
    			 vo.setLng(lng);
    			 vo.setTel(tel);
    			 
    			 Insert_food dao=new Insert_food();
    			 dao.save(vo);
    			 
    	
    			
    			
    			 System.out.println("?????? :"+bsnsCond+" "+"????????? : "+bsnsNm3+" "+"?????? :"+menu+" "+"??????  : "+addr+" "+"??????  : "+lat+" "+"?????? : "+lng+" "+" ?????? :"+ tel+" " );
    			 
    			 
    			 }
    			 
    		 }
    		 
    		
           } catch(Exception e) {
        	   e.printStackTrace();
           }
           
        
	}

}