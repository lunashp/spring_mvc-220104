package kr.co.lunasoft;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//JUnit과 Spring-Test를 같이 사용하겠다는 설정
@RunWith(SpringJUnit4ClassRunner.class)

//Spring Bean Configuration 파일의 내용을 실행하겠다는 설정
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DBTest {
	@Autowired
	private DataSource ds; 
	
	@Test
	public void connectionTest() {
		//여기서 Exception이 발생하면
		//DataSource의 property 이름 문제나
		//driverClassName(MySQL 의존성 설정 문제 포함)문제
		System.out.println("데이터베이스:" + ds);
		
		//데이터베이스 연결 테스트
		//try() 안에서 생성한 자원은 close를 호출하지 않아도 자동 해제
		try(Connection con = ds.getConnection()){
			System.out.println(con);
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	@Autowired
	private SqlSession sqlSession;
	@Test
	public void mybatisTest() {
		System.out.println("MyBatis 객체:" + sqlSession);
		
	}
}
