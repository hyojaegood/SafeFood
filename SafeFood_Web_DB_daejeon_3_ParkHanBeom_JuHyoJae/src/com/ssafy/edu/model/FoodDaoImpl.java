package com.ssafy.edu.model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ssafy.edu.dto.FoodDataBase;
import com.ssafy.edu.dto.FoodDto;

public class FoodDaoImpl extends FoodDataBase implements IFoodDao{
	private ArrayList<FoodDto> foods=new ArrayList<>();
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		FoodDaoImpl test = new FoodDaoImpl();
	}
	public FoodDaoImpl() {
		String sql = "select * from food;";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn = getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				int i=1;
				foods.add(new FoodDto(
						rs.getInt(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getDouble(i++),
						rs.getString(i++),
						rs.getString(i++)
						));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean insertFood(FoodDto dto) {
		FoodDto fdto=findByName(dto.getName());
		if(fdto!=null) {
			return false;
		}else {
			foods.add(dto);
			return true;
		}
	}

	@Override
	public boolean deleteFood(String name) {
		// TODO Auto-generated method stub
		FoodDto fdto=findByName(name);
		if(fdto==null) {
			return false;
		}else {
			foods.remove(fdto);
			return true;
		}
	}

	@Override
	public boolean update(FoodDto dto) {
		// TODO Auto-generated method stub
		FoodDto fdto=findByName(dto.getName());
		if(fdto==null) {
			return false;
		}else {
			int index=foods.indexOf(fdto);
			foods.set(index, dto);
			return true;
		}
	}

	@Override
	public FoodDto findByName(String name) {
		// TODO Auto-generated method stub
		FoodDto dto=null;
		for (int i = 0; i < foods.size(); i++) {
			FoodDto temp=foods.get(i);
			if(temp.getName().equalsIgnoreCase(name)) {
				dto=temp; 
				break;
			}
		}
		return dto;
	}

	@Override
	public List<FoodDto> findByMaterial(String material) {
		List<FoodDto> searches=new ArrayList<>();
		int foodSize = foods.size();
		for(int i=0;i<foodSize;i++) {
			if(foods.get(i).getMaterials().contains(material)) {
				searches.add(foods.get(i));
			}else if(foods.get(i).getName().contains(material)) {
				searches.add(foods.get(i));
			}
		}
		return searches;
	}

	@Override
	public List<FoodDto> findAllFoods() {
		// TODO Auto-generated method stub
		return foods;
	}

}
