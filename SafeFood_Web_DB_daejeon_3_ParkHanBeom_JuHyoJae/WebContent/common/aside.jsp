<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="utf-8"/>
<c:if test="${empty sessionScope.id}">

 <li class="last"><p class="trigger-custom"
			data-izimodal-open="#modal-custom">로그인</p>


		<div id="modal-custom" data-iziModal-group="grupo1">
			<button data-iziModal-close class="icon-close">x</button>
			<header>
				<a href="" id="signin">Sign in</a> <a href="" class="active">New
					Account</a>
			</header>
			<section class="hide">
<form action="<%=request.getContextPath()%>/main.do?action=login" method="post" id='_fo3'>


					<input type="text" name='id' placeholder="Username" id='lid'> 
					<input type="password"  name='pwd' placeholder="Password" id='lpwd'>
						</form>
				<footer>
					<button data-iziModal-close>Cancel</button>
					<button id='_btnlog' class="submit">Log in</button>
				</footer>
			</section>
			<section>
			<form action="<%=request.getContextPath()%>/main.do?action=signin" method="post" id='_fo4'>
				<input type="text" placeholder="User ID" name='id' id='sid'> <input
					type="password" placeholder="Password" id='spwd' name='password'> <input type="text"
					placeholder="User Name" id='sname' name='name'> <input type="text"
					placeholder="Address" id='sad' name='address'> <input type="text"
					placeholder="Email" id='semail' name='email'> <input type="text"
					placeholder="Phone number" id='sphone' name='phoneNumber'> <label for="inputPassword"
					class="col-md-3 control-label text-md-right col-form-label">알레르기
					<span class="text-danger small">*</span>
				</label>
				<div class="col-md-8">
					<fieldset class="form-group" >
						<legend class="col-form-legend col-sm-3"> Check</legend>
						<div class="row">
							<div class="col-sm-10">
															
								<c:forEach items="${ingredients}" var="Ingredient" varStatus="vs">
								<div class="form-check form-check-inline">
									<input name="allergy" class="form-check-input"
										id="inlineCheckbox2" type="checkbox" value="${Ingredient.name}" > <label
										class="form-check-label">${Ingredient.name}</label>
								</div>

								</c:forEach>
							</div>
						</div>
					</fieldset>
				</div>

				</form>
				<footer>
				
					<button data-iziModal-close>Cancel</button>
					<button class="submit" id='_btnsign'>Create Account</button>
				</footer>
			</section>
		</div></li>
	<div class="clear"></div>
 
  </c:if>
  <%
  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  %>
  <c:if test="${not empty sessionScope.id}">
   <li><p>[${id}]님 반갑습니다.<br/><%=sdf.format(new Date()) %>
		<a href="#" data-izimodal-open="#modal-custom">회원 정보 </a> <a href='./main.do?action=logout'>로그아웃 </a>
		<div id="modal-custom" data-iziModal-group="grupo1">
			<button data-iziModal-close class="icon-close">x</button>
			<header>
			<p>Member Info</p>
			</header>

			<section>
			<form action="<%=request.getContextPath()%>/main.do?action=change" method="post" id='_fo5'>
				<input type="text" placeholder="User ID" name='id' id='sid' value='${id}' readonly="readonly"> <input
					type="password" placeholder="Password" id='spwd' name='password' value='${password}'> <input type="text"
					placeholder="User Name" id='sname' name='name' value='${name}'> <input type="text"
					placeholder="Address" id='sad' name='address' value='${address}'> <input type="text"
					placeholder="Email" id='semail' name='email' value='${email}'> <input type="text"
					placeholder="Phone number" id='sphone' name='phoneNumber' value='${phoneNumber}'> <label for="inputPassword"
					class="col-md-3 control-label text-md-right col-form-label">저장된 알레르기
					<span class="text-danger small">*</span>
				</label>
				<input type="text" value='${allergys}' readonly="readonly">
				<div class="col-md-8">
				
					<fieldset class="form-group" >
						<legend class="col-form-legend col-sm-3"> Check</legend>
						<div class="row">
							<div class="col-sm-10">
							
								<c:forEach items="${ingredients}" var="Ingredient" varStatus="vs">
								<div class="form-check form-check-inline">
									<input name="allergy" class="form-check-input"
										id="inlineCheckbox2" type="checkbox" value="${Ingredient.name}" > <label
										class="form-check-label">${Ingredient.name}</label>
								</div>

								</c:forEach>
							</div>
						</div>
					</fieldset>
				</div>

				</form>
				<footer>
				
					<a id='' href='<%=request.getContextPath()%>/main.do?action=delete&id=${id}'>Delete Member</a>
					
					<button class="submit" id='_btnchange'>Change Info</button>
				</footer>
			</section>
		</div></li>
<!-- 	<div class="clear"></div><a href='./main.do?action=memberinfo'>회원 정보</a> -->
   
   
   
   
   <br/></p>
  	
  </li>
	
  </c:if>