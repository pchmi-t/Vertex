package com.fmi.javaee.vertex.criteria;

import java.io.Serializable;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class BaseCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Class<?> bean;
	
	private Criterion criterion;
	
	public BaseCriteria(Class<?> bean) {
		this.bean = bean;
	}
	
	/**
	 * And expression.
	 * 
	 * @param criteria the criteria
	 */
	public void and(Criterion criterion) {
		if ( criterion != null ) {
			this.criterion = Restrictions.and(this.criterion, criterion);
		} else {
			this.criterion = criterion;
		}
	}
	
	/**
	 * Or expression.
	 * 
	 * @param criterion the criteria
	 */
	public void or(Criterion criterion) {
		if (criterion != null) {
			this.criterion = Restrictions.or(this.criterion, criterion);
		} else {
			this.criterion = criterion;
		}
	}

	/**
	 * Get the criterion.
	 * 
	 * @return the criterion
	 */
	protected Criterion getCriterion() {
		return this.criterion;
	}
	
	public Criteria getCriteria(Session session, Class<?> bean) {
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(bean);
		return criteria;
	}
}
