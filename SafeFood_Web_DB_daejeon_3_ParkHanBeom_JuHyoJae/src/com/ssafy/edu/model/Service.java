package com.ssafy.edu.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ssafy.edu.dto.Allergy;
import com.ssafy.edu.dto.FoodDto;
import com.ssafy.edu.dto.Ingredient;
import com.ssafy.edu.dto.MemberDto;

public class Service{
	private IMemberDao memberDao;
	private IFoodDao foodDao;
	private static Service boardService;
	private Service() throws ParserConfigurationException, SAXException, IOException {
		memberDao=new MemberDaoImpl();
		foodDao = new FoodDaoImpl();
	}
	public static Service getInstance() throws ParserConfigurationException, SAXException, IOException {
		if(boardService==null) {
			boardService=new Service();
		}
		return boardService;
	}
	

	public void insertMember(MemberDto dto) throws Exception {
		memberDao.insertMember(dto);
	}
	public void updateMember(MemberDto dto) throws Exception {
		memberDao.updateMember(dto);
	}
	public void deleteMember(String id) throws Exception {
		memberDao.deleteMember(id);
	}
	
	public MemberDto findMemberById(String id) throws Exception {
		return memberDao.findMemberById(id);
			
	}
	public ArrayList<MemberDto> findAllMembers() throws Exception{
		return memberDao.findAllMembers();
	}
	public ArrayList<MemberDto> searchMembers(String kind , String text) throws Exception{
		return memberDao.searchMembers(kind, text);
	}
	
	
	public boolean insertFood(FoodDto dto) {
		// TODO Auto-generated method stub
		return foodDao.insertFood(dto);
	}

	public boolean deleteFood(String name) {
		// TODO Auto-generated method stub
		return foodDao.deleteFood(name);
	}
	
	public boolean update(FoodDto dto) {
		// TODO Auto-generated method stub
		return foodDao.update(dto);
	}
	
	public FoodDto findByName(String name) {
		// TODO Auto-generated method stub
		return foodDao.findByName(name);
	}
	
	public List<FoodDto> findByMaterial(String material) {
		// TODO Auto-generated method stub
		return foodDao.findByMaterial(material);
	}
	
	public List<FoodDto> findAllFoods() {
		// TODO Auto-generated method stub
		return foodDao.findAllFoods();
	}
	public void addAllergy(Allergy al) throws Exception {
		memberDao.addAllergy(al);
	}
	public void deleteAllergy(String id) throws Exception {
		memberDao.deleteAllergy(id);
	}
	public ArrayList<Allergy> findAllAllergy() throws Exception {
		return memberDao.findAllAllergy();
	}
	public ArrayList<Allergy> findAllergyById(String id) throws Exception {
		return memberDao.findAllergyById(id);
	}
	public ArrayList<Ingredient> findAllIngredient() throws Exception {
		return memberDao.findAllIngredient();
	}
	public Ingredient findIngredientbyName(String name) throws Exception {
		return memberDao.findIngredientbyName(name);
	}
	public ArrayList<String> getAllergy(String id) throws Exception {
		return memberDao.getAllergy(id);
	}
	
}
