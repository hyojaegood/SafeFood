package com.ssafy.edu.model;

import java.util.List;

import com.ssafy.edu.dto.FoodDto;

public interface IFoodDao {
	//등록
	boolean insertFood(FoodDto dto);
	//삭제
	boolean deleteFood(String name);
	//수정
	boolean update(FoodDto dto);
	//상품이름으로 검색해서 반환
	FoodDto findByName(String name);
	//성분이름으로 검색해서 반환
	List<FoodDto> findByMaterial(String material);
	//모든 상품 정보 반환
	List<FoodDto> findAllFoods();
}
