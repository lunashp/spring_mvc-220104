package kr.co.lunasoft.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

//getter&setter, toString, 매개변수가 없는 생성자를 만들어주는 annotation
//제대로 동작을 하려면 현재 IDE에 lombok이 설치 되어야 합니다 
//설치가 안되어 있으면 그냥 getter&setter, toString을 만들어주면 됩니다 

//DTO 클래스를 하나의 XML 태그로 변환하기 위한 작업
//propOrder 에 포함된 항목만 순서대로 출력 됨
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder= {"itemid", "itemname", "price", "description", "pictureurl"})
@Data
public class Item {
	private int itemid;
	private String itemname;
	private int price;
	private String description;
	private String pictureurl;
}

