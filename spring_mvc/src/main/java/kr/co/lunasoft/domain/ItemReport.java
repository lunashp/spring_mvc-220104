package kr.co.lunasoft.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
//최상위 태그 이름 설정
@XmlRootElement(name="ITEMLIST")
public class ItemReport {
	@XmlElement(name = "ITEM")
	private List<Item> list;

	public List<Item> getList(){
		return list;
	}

	//생성자
	public ItemReport() {
	}
	public ItemReport(List<Item> list) {
		this.list = list;
	}
}
