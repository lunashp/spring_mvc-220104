package kr.co.lunasoft.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.co.lunasoft.domain.Item;

@Repository
public class ItemHibernateDao {
	//Hibernate 연동 객체 주입
	@Autowired
	private SessionFactory sessionFactory;

	//전체 데이터 가져오기
	public List<Item> getList(){
		CriteriaQuery <Item> criteriaQuery = sessionFactory.getCurrentSession().getCriteriaBuilder().createQuery(Item.class);
		criteriaQuery.from(Item.class);
		List<Item> list = sessionFactory.getCurrentSession().createQuery(criteriaQuery).getResultList();
		return list;
	}
	
	//기본키를 가지고 하나의 데이터 가져오기
	public Item getItem(Integer itemid) {
		Item item = (Item)sessionFactory.getCurrentSession().get(Item.class, itemid);
		return item;
	}
}
