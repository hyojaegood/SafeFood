package com.ssafy.edu.model;

import java.util.ArrayList;

import com.ssafy.edu.dto.Allergy;
import com.ssafy.edu.dto.Ingredient;
import com.ssafy.edu.dto.MemberDto;


public interface IMemberDao {
	//회원 추가
	//회원 삭제
	//회원 정보 수정
	
	void insertMember(MemberDto dto) throws Exception;
	void updateMember(MemberDto dto) throws Exception;;
	void deleteMember(String id) throws Exception;;
	
	MemberDto findMemberById(String id) throws Exception;;
	ArrayList<MemberDto> findAllMembers() throws Exception;
	ArrayList<MemberDto> searchMembers(String kind , String text) throws Exception;

	void addAllergy(Allergy al) throws Exception;
	void deleteAllergy(String id) throws Exception;
	ArrayList<Allergy> findAllAllergy() throws Exception;
	ArrayList<Allergy> findAllergyById(String id) throws Exception;
	
	ArrayList<Ingredient> findAllIngredient() throws Exception;
	Ingredient findIngredientbyName(String name) throws Exception;
	ArrayList<String> getAllergy(String id) throws Exception;
}
