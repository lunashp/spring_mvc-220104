package kr.co.lunasoft;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.co.lunasoft.domain.Item;
import kr.co.lunasoft.service.ItemService;

//RESTController를 만들어주는 어노테이션
//뷰를 리턴하지 않고 데이터를 리텅하는 Controller
//문자열을 리턴하면 csv 형식 처럼 일반 텍스트가 되고
//DTO나 Map, 배열, List를 리턴하면 JSON 형식으로 변환 해서 출력 합니다 
@RestController
public class JSONController {
	@Autowired
	private ItemService itemService;

	@RequestMapping(value="/item.csv", method=RequestMethod.GET)
	public String itemcsv() {
		return "csv, xml, json";
	}

	@RequestMapping(value="/itemrest.json", method=RequestMethod.GET)
	public List<Item> itemrest(HttpServletRequest request, HttpServletResponse response){ 
		/*
	Map<String, Object>map = new HashMap<>();
	map.put("name", "yujin");
	System.out.println(map);
	return map;
		 */

		/*
	Item item = new item();
	item.setItemid(1);
	System.out.println(item);
		 */
		List<Item> list = itemService.getList(request, response);
		System.out.println(list);
		return list;

	}
}
