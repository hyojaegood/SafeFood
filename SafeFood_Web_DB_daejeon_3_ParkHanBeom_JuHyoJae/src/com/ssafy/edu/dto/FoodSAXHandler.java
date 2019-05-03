package com.ssafy.edu.dto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *  FoodInfo.xml 파일에서 식품 정보를 읽어 파싱하는 핸들러 클래스 
 */
public class FoodSAXHandler extends DefaultHandler {
	/**파싱한 식품 정보를 담는 맵, 식품 이름으로 식품정보를 검색하기 위해 맵에 담는다 */
	private Map<Integer, FoodDto> foods;
	/**파상힌 식품 영양 정보*/
	private FoodDto food;
	/**태그 바디 정보를 임시로 저장*/
	private String temp;
	public FoodSAXHandler(){
		foods = new HashMap<Integer, FoodDto>();
	}
	public void startElement(String uri, String localName, String qName, Attributes att ){
		temp = qName;
		int attrCnt = att.getLength();
		String attrName = att.getQName(0);
		String attrValue = att.getValue(attrName);
		if(qName.equals("food")){
			food = new FoodDto();
		}

	}
	public void endElement(String uri, String localName, String qName){
		if(qName.equals("code")) { 
			food.setFoodNum(Integer.parseInt(temp));
		}else if(qName.equals("name")) { 
			food.setName(temp);
		}else if(qName.equals("maker")) { 
			food.setManufacturer(temp);
		}else if(qName.equals("material")) { 
			food.setMaterials(temp);
		}else if(qName.equals("image")) { 
			food.setImgSrc(temp);
		}else if(qName.equals("food")) { 
			foods.put(food.getFoodNum(), food);
		}
	}
	public void characters(char[]ch, int start, int length){
		temp = new String(ch, start, length);
	}
	public Map<Integer, FoodDto>  getFoods() {
		return foods;
	}
	public void setFoods(Map<Integer, FoodDto>  foods) {
		this.foods = foods;
	}
}





