package com.ssafy.edu.dto;

public class FoodDto {
	int foodNum;//PK
	String name;
	String manufacturer;
	double weight;
	double calories;
	double carbohydrate;
	double protein;
	double fat;
	double sugar;
	double natrium;
	double cholesterol;
	double saturatedFat;
	double transFat;
	String materials;
	String imgSrc;
	public FoodDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FoodDto(int foodNum, String name, String manufacturer, double weight, double calories, double carbohydrate,
			double protein, double fat, double sugar, double natrium, double cholesterol, double saturatedFat,
			double transFat, String materials, String imgSrc) {
		super();
		this.foodNum = foodNum;
		this.name = name;
		this.manufacturer = manufacturer;
		this.weight = weight;
		this.calories = calories;
		this.carbohydrate = carbohydrate;
		this.protein = protein;
		this.fat = fat;
		this.sugar = sugar;
		this.natrium = natrium;
		this.cholesterol = cholesterol;
		this.saturatedFat = saturatedFat;
		this.transFat = transFat;
		this.materials = materials;
		this.imgSrc = imgSrc;
	}
	@Override
	public String toString() {
		return "FoodDto [foodNum=" + foodNum + ", name=" + name + ", manufacturer=" + manufacturer + ", weight="
				+ weight + ", calories=" + calories + ", carbohydrate=" + carbohydrate + ", protein=" + protein
				+ ", fat=" + fat + ", sugar=" + sugar + ", natrium=" + natrium + ", cholesterol=" + cholesterol
				+ ", saturatedFat=" + saturatedFat + ", transFat=" + transFat + ", materials=" + materials + ", imgSrc="
				+ imgSrc + "]";
	}
	public int getFoodNum() {
		return foodNum;
	}
	public void setFoodNum(int foodNum) {
		this.foodNum = foodNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getCalories() {
		return calories;
	}
	public void setCalories(double calories) {
		this.calories = calories;
	}
	public double getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(double carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public double getProtein() {
		return protein;
	}
	public void setProtein(double protein) {
		this.protein = protein;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getSugar() {
		return sugar;
	}
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	public double getNatrium() {
		return natrium;
	}
	public void setNatrium(double natrium) {
		this.natrium = natrium;
	}
	public double getCholesterol() {
		return cholesterol;
	}
	public void setCholesterol(double cholesterol) {
		this.cholesterol = cholesterol;
	}
	public double getSaturatedFat() {
		return saturatedFat;
	}
	public void setSaturatedFat(double saturatedFat) {
		this.saturatedFat = saturatedFat;
	}
	public double getTransFat() {
		return transFat;
	}
	public void setTransFat(double transFat) {
		this.transFat = transFat;
	}
	public String getMaterials() {
		return materials;
	}
	public void setMaterials(String materials) {
		this.materials = materials;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	
}
