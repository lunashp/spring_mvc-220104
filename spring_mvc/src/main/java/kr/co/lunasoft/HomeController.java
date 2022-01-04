package kr.co.lunasoft;

import java.io.File;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.lunasoft.domain.EmailReport;
import kr.co.lunasoft.domain.Item;
import kr.co.lunasoft.domain.Member;
import kr.co.lunasoft.domain.User;
import kr.co.lunasoft.service.ItemService;
import kr.co.lunasoft.validator.MemberValidator;


//빈이 자동 생성
@Controller
public class HomeController {
	@Autowired
	private ItemService itemService;

	// /로 요청이 GET 방식으로 오면 호출되는 메소드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//문자열을 리턴하면 이 이름이 뷰 이름이 되고 기본적으로 포워딩 됩니다
	// / 요청이 오면 home 으로 포워딩 합니다
	//home으로 포워딩할 때 ViewResolver 설정에 의해서
	//WEB-INT/views/home.jsp 파일로 포워딩 합니다 
	public String home(Model model, HttpServletRequest request, HttpServletResponse response) {
		//데이터를 저장해서 전달
		model.addAttribute("list", itemService.getList(request, response));
		return "home";
	}

	@RequestMapping(value="hello", method=RequestMethod.GET)
	public String hi() {
		return "hello";
	}
	//detail 요청의 가장 마지막 부분을 num에 대입
	@RequestMapping(value="/detail/{num}", method=RequestMethod.GET)
	//위의 num 부분을 @PathVariable의 num에 대입
	public void detail(@PathVariable int num) { 
		System.out.println(num);
	}
	@RequestMapping(value="/param", method=RequestMethod.GET)
	public String param() {
		return "param";
	}
	/*
	@RequestMapping(value="/param", method=RequestMethod.POST)
	public String param(HttpServletRequest request) {
		//HttpServletRequest 객체를 이용한 파라미터 읽기
		//없는 파라미터 이름을 이용하면 null
		//파라미터에 입력된 값이 없는 경우는 ""
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");

		System.out.println(name);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(job);

		return "hello";
	}
	 */
	//@RequestParam을 이용하는 방법
	/*
	@RequestMapping(value="/param", method=RequestMethod.POST)
	public String param(
			@RequestParam("name") String name,
			@RequestParam("age") String age,
			@RequestParam("gender") String gender,
			@RequestParam("job") String job) {

		System.out.println(name);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(job);

		return "hello";
	}
	 */
	//Command 객체를 이용하는 방법
	@RequestMapping(value="/param", method=RequestMethod.POST)
	public String param(User user) {
		System.out.println(user);

		return "hello";
	}

	//모든 요청 처리 결과에 전달하는 데이터를 생성해주는 메소드
	@ModelAttribute("map")
	public Map<String, Object> allPass(){
		Map<String, Object> map = new HashMap<>();
		map.put("parameter", "클라이언트가 서버에게 전달하는 데이터");
		map.put("attribute", "서버가 클라이언트에게 전달하는 데이터");
		return map;
	}

	@RequestMapping(value="/forward", method=RequestMethod.GET)
	public String forward(Model model) {
		//request.setAttribute("이름", 데이터) 와 동일한 효과
		model.addAttribute("data", "forwarding 할 때 데이터 전달");
		return "result";
	}
	@RequestMapping(value="/redirect", method=RequestMethod.GET)
	public String redirect(Model model, HttpSession session, RedirectAttributes rattr) {
		//redirect의 경우는 이 문장은 아무런 의미가 없습니다
		//이렇게 하면 request에 저장 되는데 redirect 하면 request는 다시 생성 됩니다
		//model.addAttribute("data", "forwarding 할 때 데이터 전달");

		//이 경우는 session을 초기화하거나 데이터를 삭제하지 않는 한 계속 유지
		session.setAttribute("data","value");

		//이 경우는 세션에 저장했다가 한 번 redirect 하고 나면 자동 소멸 됩니다 
		rattr.addFlashAttribute("attr", "value");

		//여기서 result는 Controller 에게 돌아오기 위한 URL 이어야 합니다
		//어딘가에 result를 처리해주는 메소드가 있어야 합니다 
		//데이터에 변화가 생기면 특별한 경우가 아니고는 redirect
		return "redirect:result";
	}
	/*
	//상세보기 처리를 위한 메소드 - 파라미터를 이용하는 방식
	@RequestMapping(value="/detail.html")
	public String getItem(@RequestParam("itemid") Integer itemid, Model model) {
		//서비스 메소드 호출
		Item item = itemService.getItem(itemid);
		//출력할 데이터 저장
		model.addAttribute("item", item);
		//출력할 뷰 이름 리턴
		return "detail";
	}
	 */
	@RequestMapping(value="/detail.html/{itemid}")
	public String getItem(@PathVariable Integer itemid, Model model) {
		//서비스 메소드 호출
		Item item = itemService.getItem(itemid);
		//출력할 데이터 저장
		model.addAttribute("item", item);
		//출력할 뷰 이름 리턴
		return "detail";
	}

	//exception 요청이 발생했을 때 input.jsp를 출력하도록 해주는 메소드
	/*
	@RequestMapping(value="/exception", method=RequestMethod.GET)
	public String exception() {
		return "input";
	}
	 */

	//input.jsp form 에서 submit을 눌렀을 때 처리하도록 해주는 메소드
	//dividend와 divisor 파라미터의 값을 읽어서 나눗셈을 해서 Model에 저장하고 
	//result.jsp 파일에 그 내용을 출력하도록 하기
	@GetMapping("/cal")
	public String cal(@RequestParam("dividend") int dividend,
			@RequestParam("divisor") int divisor, Model model) {
		model.addAttribute("result", dividend/divisor);
		return "result";
	}
	//ArithmeticException 이 발생하면 출력할 페이지를 설정하는 메소드
	@ExceptionHandler(ArithmeticException.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("content", e.getLocalizedMessage());
		return "error/exception";
	}

	//message 요청이 GET 방식으로 오면 처리하는 메소드 
	@GetMapping("/message")
	public String message(@ModelAttribute("member") Member member) {
		return "loginform";
	}

	//message 요청이 POST 방식으로 오면 처리하는 메소드 
	@PostMapping("/message")
	public String message(@Valid @ModelAttribute("member") Member member, BindingResult result) {
		//유효성 검사 수행
		//new MemberValidator().validate(member,result);
		//검사 결과 에러가 있으면
		if(result.hasErrors()) {
			return "loginform";
		}
		//검사 결과 에러가 없으면 시작 요청으로 리다이렉트
		return "redirect:/";
	}


	//모든 결과 페이지에 loginTypes 라는 이름의 메소드의 리턴값이 전달
	@ModelAttribute("loginTypes")
	public List<String> loginTypes(){
		List<String> list = new ArrayList<>();
		list.add("일반회원");
		list.add("VIP회원");
		list.add("관리자");
		return list;
	}

	//fileupload의 GET 요청을 처리하는 메소드
	@GetMapping("fileupload")
	public String fileupload() {
		return "fileupload";
	}

	@PostMapping("fileuploadrequest")
	//파일 업로드를 Request를 이용해서 처리
	public String fileUpload(MultipartHttpServletRequest request) {
		String email = request.getParameter("email");
		MultipartFile report = request.getFile("report");
		System.out.println(email);
		System.out.println(report);

		return "redirect:/";
	}

	@PostMapping("fileuploadparam")
	//파일 업로드를 Request를 이용해서 처리
	public String fileUpload(
			@RequestParam("email") String email,
			@RequestParam("report") MultipartFile report) {

		System.out.println(email);
		System.out.println(report);

		return "redirect:/";
	}

	@PostMapping("fileuploadcommand")
	//파일 업로드를 Request를 이용해서 처리
	public String fileUpload(
			@ModelAttribute("emailReport")
			EmailReport emailReport, HttpServletRequest request) {
		//파일을 업로드 할 경로 생성
		//프로젝트 내의 webapp 내의 upload 디렉토리의 절대 경로 생성
		String uploadPath = request.getServletContext().getRealPath("/upload");
		//업로드 된 파일의 원래 이름
		String filename = emailReport.getReport().getOriginalFilename();

		//원본 그대로 파일명 만들기
		//File filePath = new File(uploadPath + "/" + filename);
		
		//파일명에 다른 파라미터를 추가해서 파일명 만들기
		//File filePath = new File(uploadPath + "/" + emailReport.getEmail() + filename);
		
		//랜덤한 문자열을 추가
		File filePath = new File(uploadPath + "/" + UUID.randomUUID());
		
		try {
			emailReport.getReport().transferTo(filePath);
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return "redirect:/";
	}
}
