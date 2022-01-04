package kr.co.lunasoft.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewService {
	//파일의 목록을 가져오는 메소드
	public List<String> fileview(HttpServletRequest request, HttpServletResponse response);
	
	//엑셀 파일의 내용을 읽어서 리턴하는 메소드
	public List<Map<String, Object>> excelread(HttpServletRequest request, HttpServletResponse response);
}
