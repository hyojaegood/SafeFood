package com.ssafy.edu.dto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * FoodNutritionSAXHandler와 FoodSAXHandler를 이용해서 식품 정보를 load하는 SAX Parser 프로 그램  
 *
 */
public class FoodSaxParser extends FoodDataBase {
	private String nutritionFilePath = "res/FoodNutritionInfo.xml";
	private String foodFilePath = "./res/foodInfo.xml";
	private StringBuilder xml ;
	private List<FoodDto> foods;
	private Map<String, FoodDto> foods2;
 	public FoodSaxParser() {
		loadData();
	}
 	
 	/**
 	 * FoodNutritionSAXHandler와 FoodSAXHandler를 이용 파싱한 식품 정보와 식품 영양 정보를  Food에 병합한다. 
 	 */
	private void loadData() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try{
			SAXParser parser = factory.newSAXParser();
			FoodSAXHandler handler = new FoodSAXHandler();
			FoodNutritionSAXHandler nHandler = new FoodNutritionSAXHandler();
			parser.parse(foodFilePath,handler);
			parser.parse(nutritionFilePath,nHandler);
			Map<Integer, FoodDto> foods = handler.getFoods();
			foods2 = nHandler.getList();
			System.out.println("load data");
			FoodDto find;
			for(int i=1;i<=foods.size();i++) {
				foods.get(i).setWeight(foods2.get(foods.get(i).getName()).getWeight());
				foods.get(i).setCalories(foods2.get(foods.get(i).getName()).getCalories());
				foods.get(i).setCarbohydrate(foods2.get(foods.get(i).getName()).getCarbohydrate());
				foods.get(i).setProtein(foods2.get(foods.get(i).getName()).getProtein());
				foods.get(i).setFat(foods2.get(foods.get(i).getName()).getFat());
				foods.get(i).setSugar(foods2.get(foods.get(i).getName()).getSugar());
				foods.get(i).setNatrium(foods2.get(foods.get(i).getName()).getNatrium());
				foods.get(i).setSaturatedFat(foods2.get(foods.get(i).getName()).getSaturatedFat());
				foods.get(i).setTransFat(foods2.get(foods.get(i).getName()).getTransFat());
			}
			for(int i=1;i<=foods.size();i++){
				System.out.println(foods.get(i));
			}
			//foodInfo db에 넣기
			String sql = "";
			Connection conn=null;
			PreparedStatement psmt=null;
			conn = getConnection();
			for(int i=1;i<=foods.size();i++) {
				sql = " INSERT INTO food (foodnum,name,manufacturer,weight,calories,carbohydrate, protein,fat,sugar,natrium,cholesterol,saturatedfat,transfat,materials,imgsrc) " + 
						" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
				psmt = conn.prepareStatement(sql);
				int k=1;
				System.out.println(foods.get(i).getFoodNum());
				psmt.setString(k++, ""+foods.get(i).getFoodNum());
				psmt.setString(k++, ""+foods.get(i).getName());
				psmt.setString(k++, ""+foods.get(i).getManufacturer());
				psmt.setString(k++, ""+foods.get(i).getWeight());
				psmt.setString(k++, ""+foods.get(i).getCalories());
				psmt.setString(k++, ""+foods.get(i).getCarbohydrate());
				psmt.setString(k++, ""+foods.get(i).getProtein());
				psmt.setString(k++, ""+foods.get(i).getFat());
				psmt.setString(k++, ""+foods.get(i).getSugar());
				psmt.setString(k++, ""+foods.get(i).getNatrium());
				psmt.setString(k++, ""+foods.get(i).getCholesterol());
				psmt.setString(k++, ""+foods.get(i).getSaturatedFat());
				psmt.setString(k++, ""+foods.get(i).getTransFat());
				psmt.setString(k++, ""+foods.get(i).getMaterials());
				psmt.setString(k++, ""+foods.get(i).getImgSrc());
				System.out.println(psmt);
				int count = psmt.executeUpdate();
				if(count<=0) {throw new SQLException("음식 삽입 실패");}
			}
			//foodNutrition 내용 업데이트
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<FoodDto> getFoods() {
		return foods;
	}
	public void setFoods(List<FoodDto> foods) {
		this.foods = foods;
	}
	
}
