package kr.co.lunasoft.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lunasoft.dao.ItemDao;
import kr.co.lunasoft.dao.ItemHibernateDao;
import kr.co.lunasoft.dao.ItemMapper;

@Service
public class ViewServiceImpl implements ViewService {
	@Autowired
	private ItemMapper itemDao;

	@Autowired
	private ItemHibernateDao itemHibernateDao;

	@Override
	public List<String> fileview(HttpServletRequest request, HttpServletResponse response) {
		List<String> list = new ArrayList<>();
		// 프로젝트 내의 webapp 디렉토리 안의 img 디렉토리의 절대 경로 가져로기 
		String path = request.getServletContext().getRealPath("/img");
		// 파일 객체 생성 
		File f = new File(path);
		// 디렉토리 안의 모든 목록을 가져오기 
		String[] s = f.list();
		// 목록을 순회하면서 확장자가 있는 경우만 추가 
		for(String temp : s) {
			if(temp.indexOf(".") > 0 ) {
				list.add(temp);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> excelread(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String, Object>> list = new ArrayList<>();

		try {
			File file = new File(request.getServletContext().getRealPath("/") + "/excel.xls");
			//엑셀 파일 오픈
			HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
			//첫번째 시트의 행을 순회
			for(Row row : wb.getSheetAt(0)) {
				//Map을 생성
				Map<String, Object> map = new HashMap<>();

				map.put("itemname", row.getCell(0));
				map.put("description", row.getCell(1));

				try {
					String price = row.getCell(2).getStringCellValue();
					int p = Integer.parseInt(price);
					map.put("price", p);
				}catch(Exception e) {
					map.put("price", "가격");
				}


				list.add(map);
			}
			wb.close();

		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return list;
	}

}
