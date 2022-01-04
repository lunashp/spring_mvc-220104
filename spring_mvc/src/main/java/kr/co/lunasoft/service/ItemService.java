package kr.co.lunasoft.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.lunasoft.domain.Item;

public interface ItemService {
	//전체보기를 위한 메소드
	public List<Item> getList(HttpServletRequest request, HttpServletResponse response);
	//상세보기를 위한 메소드
	//파라미터를 Controller 에서 읽도록 하기 
	//Service 에서 읽는 경우에는 Integer 대신에 HttpServletRequest	
	public Item getItem(Integer itemid);
}
