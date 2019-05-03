package com.ssafy.edu.dto;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *  FoodNutritionInfo.xml 파일에서 식품 영양 정보를 읽어 파싱하는 핸들러 클래스 
 */
public class FoodNutritionSAXHandler extends DefaultHandler {
	/**파싱한 식품 영양 정보를 담는 리스트 */
	private  Map<String, FoodDto> list;
	/**파상힌 식품 영양 정보*/
	private FoodDto food;
	/**태그 바디 정보를 임시로 저장*/
	private String temp;
	public FoodNutritionSAXHandler(){
		list = new HashMap<String, FoodDto>();
	}
	public void startElement(String uri, String localName
			                           , String qName, Attributes att ){
		if(qName.equals("item")){
			food = new FoodDto();
		}
	}
	public void endElement(String uri, String localName, String qName){
		if(qName.equals("DESC_KOR")) { 
			food.setName(temp);
		}else if(qName.equals("SERVING_WT")) { 
			food.setWeight(changeData(temp));
		}else if(qName.equals("NUTR_CONT1")) { 
			food.setCalories(changeData(temp));
		}else if(qName.equals("NUTR_CONT2")) { 
			food.setCarbohydrate(changeData(temp));
		}else if(qName.equals("NUTR_CONT3")) { 
			food.setProtein(changeData(temp));
		}else if(qName.equals("NUTR_CONT4")) { 
			food.setFat(changeData(temp));
		}else if(qName.equals("NUTR_CONT5")) { 
			food.setSugar(changeData(temp));
		}else if(qName.equals("NUTR_CONT6")) { 
			food.setNatrium(changeData(temp));
		}else if(qName.equals("NUTR_CONT7")) { 
			food.setCholesterol(changeData(temp));
		}else if(qName.equals("NUTR_CONT8")) { 
			food.setSaturatedFat(changeData(temp));
		}else if(qName.equals("NUTR_CONT9")) { 
			food.setTransFat(changeData(temp));
		}else if(qName.equals("item")) { 
			list.put(food.getName(), food);
		}
	}
	public double changeData(String data) {
		if(data.equals("")||data.equalsIgnoreCase("N/A")) {
			return 0;
		}else{
			return Double.parseDouble(data);
		}
	}
	public void characters(char[]ch, int start, int length){
		temp =new String(ch, start, length).trim();
	}
	public Map<String, FoodDto> getList() {	return list;	}
	public void setList(Map<String, FoodDto> list) {	this.list = list;	}
}





