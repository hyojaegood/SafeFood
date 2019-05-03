package com.ssafy.edu.model;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.edu.dto.Allergy;
import com.ssafy.edu.dto.DataBase;
import com.ssafy.edu.dto.Ingredient;
import com.ssafy.edu.dto.MemberDto;

public class MemberDaoImpl extends DataBase implements IMemberDao {

	@Override
	public void insertMember(MemberDto dto) throws Exception {
		String sql=" INSERT INTO Member (id,password,name,address,email,phoneNumber) " + 
				" VALUES(?,?,?,?,?,?) ";
		Connection conn=null;
		PreparedStatement psmt=null;
		try {
			conn=getConnection();
			log("2/6 S");
			psmt=conn.prepareStatement(sql);//query injection protect or 1=1 
			int i=1;
			psmt.setString(i++, dto.getId());
			psmt.setString(i++, dto.getPassword());
			psmt.setString(i++, dto.getName());
			psmt.setString(i++, dto.getAddress());
			psmt.setString(i++, dto.getEmail());
			psmt.setString(i++, dto.getPhoneNumber());
			log("3/6 S");
			int count=psmt.executeUpdate();
			if(count<=0) {throw new SQLException("추가에 실패했습니다.");}
			log("4/6 S");
		} catch (Exception e) {
			log(" InsertMember F", e);
			throw e;
		}finally {
			close(null, psmt, conn);
			log("6/6 S");
		}
	}

	@Override
	public void updateMember(MemberDto dto) throws Exception {
		String sql=" UPDATE Member SET password=?,name=?,address=?,email=?,phoneNumber=? " + 
				" WHERE id=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		try {
			conn=getConnection();
			log(" updateBook 2/6 S");
			psmt=conn.prepareStatement(sql);
			int i=1;
			psmt.setString(i++, dto.getPassword());
			psmt.setString(i++, dto.getName());
			psmt.setString(i++, dto.getAddress());
			psmt.setString(i++, dto.getEmail());
			psmt.setString(i++, dto.getPhoneNumber());
			psmt.setString(i++, dto.getId());
			log(" updateBook 3/6 S");
			int count=psmt.executeUpdate();
			if(count<=0) {throw new SQLException("수정에 실패했습니다.");}
			log("4/6 S");
		} catch (Exception e) {
			log(" updateBook F", e);
			throw e;
		}finally {
			close(null, psmt, conn);
			log("6/6 S");
		}
	}

	@Override
	public void deleteMember(String id) throws Exception {
		String sql=" DELETE FROM Member  " + 
				" WHERE id=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		try {
			conn=getConnection();
			log(" deleteMember 2/6 S");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			log(" deleteMember 3/6 S");
			int count=psmt.executeUpdate();
			if(count<=0) {throw new SQLException("삭제에 실패했습니다.");}
			log("4/6 S");
		} catch (Exception e) {
			log(" deleteMember F", e);
			throw e;
		}finally {
			close(null, psmt, conn);
			log("6/6 S");
		}
		
	}

	@Override
	public MemberDto findMemberById(String id) throws Exception {
		MemberDto member=null;
		String sql=" SELECT id,password,name,address,email,phoneNumber  " + 
				" FROM Member WHERE id=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			log(" findMemberById 2/6 S");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			log(" findMemberById 3/6 S");
			rs=psmt.executeQuery();
			log(" findMemberById 4/6 S");
			while(rs.next()) {// 행이동가능? 행있니?
				int i=1;
				member=new MemberDto(
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++));
			}
			log(" findMemberById 5/6 S");
		} catch (Exception e) {
			log(" findMemberById F", e);
			throw e;
		}finally {
			close(rs, psmt, conn);
			log("findMemberById 6/6 S");
		}
		return member;
	}

	@Override
	public ArrayList<MemberDto> findAllMembers() throws Exception {
		ArrayList<MemberDto>  members=new ArrayList<>();
		String sql=" SELECT id,password,name,address,email,phoneNumber " + 
				" FROM Member ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			log("2/6 S");
			psmt=conn.prepareStatement(sql);
			log("3/6 S");
			rs=psmt.executeQuery();
			log("4/6 S");
			while(rs.next()) {// 행이동가능? 행있니?
				int i=1;
				MemberDto member=new MemberDto(
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++));
				members.add(member);
			}
			log("5/6 S");
		} catch (Exception e) {
			log(" findAllMembers F", e);
			throw e;
		}finally {
			close(rs, psmt, conn);
			log("findAllMembers 6/6 S");
		}
		return members;
	}

	@Override
	public ArrayList<MemberDto> searchMembers(String kind, String content) throws Exception {
		ArrayList<MemberDto>  members=new ArrayList<>();
		String sql=" SELECT ISBN,TITLE,AUTHOR,PUBLISHER,PUBDATE,PRICE " + 
				" FROM BOOKBOOK  WHERE 1=1 ";
		if(kind.equalsIgnoreCase("TITLE")) {
			sql+=" AND  TITLE LIKE  '%"+content+"%' ";
		}else if(kind.equalsIgnoreCase("AUTHOR")) {
			sql+=" AND AUTHOR LIKE  '%"+content+"%' ";
		}else if(kind.equalsIgnoreCase("PUBLISHER")) {
			sql+=" AND PUBLISHER LIKE  '%"+content+"%' ";
		}
		sql+=" ORDER BY ISBN ";
		System.out.println(sql);
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			log("searchBooks 2/6 S");
			psmt=conn.prepareStatement(sql);
			log("searchBooks 3/6 S");
			rs=psmt.executeQuery();
			log("searchBooks 4/6 S");
			while(rs.next()) {// 행이동가능? 행있니?
				int i=1;
				MemberDto member=new MemberDto(
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++),
						rs.getString(i++));
				members.add(member);
			}
			log("5/6 S");
		} catch (Exception e) {
			log(" searchBooks F", e);
			throw e;
		}finally {
			close(rs, psmt, conn);
			log("searchBooks 6/6 S");
		}
		return members;
	}

	@Override
	public void addAllergy(Allergy al) throws Exception {
		String sql=" INSERT INTO Allergy (id,Inum) " + 
				" VALUES(?,?) ";
		Connection conn=null;
		PreparedStatement psmt=null;
		try {
			conn=getConnection();
			log("2/6 S");
			psmt=conn.prepareStatement(sql);//query injection protect or 1=1 
			int i=1;
			psmt.setString(i++, al.getId());
			psmt.setInt(i++, al.getInum());
			log("3/6 S");
			int count=psmt.executeUpdate();
			if(count<=0) {throw new SQLException("추가에 실패했습니다.");}
			log("4/6 S");
		} catch (Exception e) {
			log(" InsertAllergy F", e);
			throw e;
		}finally {
			close(null, psmt, conn);
			log("6/6 S");
		}
	}

	@Override
	public void deleteAllergy(String id) throws Exception {
		String sql=" DELETE FROM Allergy " + 
				" WHERE id=?";
		Connection conn=null;
		PreparedStatement psmt=null;
		try {
			conn=getConnection();
			log(" deleteMember 2/6 S");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			log(" deleteMember 3/6 S");
			int count=psmt.executeUpdate();
			log("4/6 S");
		} catch (Exception e) {
			log(" deleteMember F", e);
			throw e;
		}finally {
			close(null, psmt, conn);
			log("6/6 S");
		}
		
		
	}

	@Override
	public ArrayList<Allergy> findAllAllergy() throws Exception {
		ArrayList<Allergy>  als=new ArrayList<>();
		String sql=" SELECT id,Inum " + 
				" FROM Allergy ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			log("2/6 S");
			psmt=conn.prepareStatement(sql);
			log("3/6 S");
			rs=psmt.executeQuery();
			log("4/6 S");
			while(rs.next()) {// 행이동가능? 행있니?
				int i=1;
				Allergy al=new Allergy(
						rs.getString(i++),
						rs.getInt(i++));
				als.add(al);
			}
			log("5/6 S");
		} catch (Exception e) {
			log(" findAllMembers F", e);
			throw e;
		}finally {
			close(rs, psmt, conn);
			log("findAllMembers 6/6 S");
		}
		return als;
	}

	@Override
	public ArrayList<Allergy> findAllergyById(String id) throws Exception {

		ArrayList<Allergy> als=new ArrayList<>();
		String sql=" SELECT id,Inum " + 
				" FROM Allergy WHERE id=? ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			log(" findAllergyById 2/6 S");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			log(" findAllergyById 3/6 S");
			rs=psmt.executeQuery();
			log(" findAllergyById 4/6 S");
			while(rs.next()) {// 행이동가능? 행있니?
				int i=1;
				Allergy al=new Allergy(
						rs.getString(i++),
						rs.getInt(i++));
				als.add(al);
			}
			log(" findAllergyById 5/6 S");
		} catch (Exception e) {
			log(" findAllergyById F", e);
			throw e;
		}finally {
			close(rs, psmt, conn);
			log("findAllergyById 6/6 S");
		}
		return als;
	}

	@Override
	public ArrayList<Ingredient> findAllIngredient() throws Exception {
		ArrayList<Ingredient> ings=new ArrayList<>();
		String sql=" SELECT Inum,name " + 
				" FROM Ingredient ";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			log("2/6 S");
			psmt=conn.prepareStatement(sql);
			log("3/6 S");
			rs=psmt.executeQuery();
			log("4/6 S");
			while(rs.next()) {// 행이동가능? 행있니?
				int i=1;
				Ingredient in=new Ingredient(
						rs.getInt(i++),
						rs.getString(i++));
				ings.add(in);
			}
			log("5/6 S");
		} catch (Exception e) {
			log(" findAllMembers F", e);
			throw e;
		}finally {
			close(rs, psmt, conn);
			log("findAllMembers 6/6 S");
		}
		return ings;
	}

	@Override
	public Ingredient findIngredientbyName(String name) throws Exception {
		ArrayList<Ingredient> ings=new ArrayList<>();
		String sql=" SELECT Inum,name " + 
				" FROM Ingredient where name=?";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		Ingredient in=null;
		try {
			conn=getConnection();
			log("2/6 S");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, name.trim());
			
			log("3/6 S");
			rs=psmt.executeQuery();
			log("4/6 S");
			while(rs.next()) {// 행이동가능? 행있니?
				int i=1;
				in=new Ingredient(
						rs.getInt(i++),
						rs.getString(i++));
			}
			log("5/6 S");
		} catch (Exception e) {
			log(" findAllMembers F", e);
			throw e;
		}finally {
			close(rs, psmt, conn);
			log("findAllMembers 6/6 S");
		}
		return in;
	}

	@Override
	public ArrayList<String> getAllergy(String id) throws Exception {
		ArrayList<String> allergys=new ArrayList<>();
		String sql=" select i.name from Ingredient i join Allergy a on i.Inum=a.Inum where a.id=?";
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			log("2/6 S");
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, id.trim());
			log("3/6 S");
			rs=psmt.executeQuery();
			log("4/6 S");
			while(rs.next()) {// 행이동가능? 행있니?
				int i=1;
				allergys.add(rs.getString(i++));
			}
			log("5/6 S");
		} catch (Exception e) {
			log(" findAllMembers F", e);
			throw e;
		}finally {
			close(rs, psmt, conn);
			log("findAllMembers 6/6 S");
		}
		return allergys;
	}


	
}
