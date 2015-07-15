package com.inlog.commons;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.inlog.entities.BaseDataObject;
import com.inlog.entities.SearchKeyValuePair;

/**
 * 
 * @author Mayur
 *
 */
public class CommonUtilities {
	private static final String SPLIT_DELIMITER = ":";
	private static final String LT_DELIMITER = "lt";
	private static final String GT_DELIMITER = "gt";
	private static final String REGEX_DELIMITER = "regex";
	private static final String SORT_ASC_DELIMITER = "ASC";
	private static final String SORT_DESC_DELIMITER = "DESC";
	private static final String LTE_DELIMITER = "lte";
	private static final String GTE_DELIMITER = "gte";
	private static final String IN_DELIMITER = "in";

	/**
	 * This Method uses reflection to get all non null properties from the
	 * object
	 * 
	 * @param entity
	 *            extends {@link BaseDataObject}
	 * @return {@link Map<String,String>}
	 */
	public static <T extends BaseDataObject> Map<String, Object> getNotNullProperties(
			T entity) {
		Map<String, Object> propertyMap = new HashMap<String, Object>();

		try {
			BeanInfo entityInfo = Introspector.getBeanInfo(entity.getClass(),
					Object.class);
			PropertyDescriptor[] propertyDescriptors = entityInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				Object propertyValue = propertyDescriptors[i].getReadMethod()
						.invoke(entity);
				String propertyKey = propertyDescriptors[i].getDisplayName();

				if (propertyValue != null) {
					if (propertyValue instanceof String) {
						String propertyVal = propertyValue.toString();
						if (StringUtils.isNotEmpty(propertyVal)) {
							propertyMap.put(propertyKey, propertyVal);
						}
					} else {
						propertyMap.put(propertyKey, propertyValue);
					}

				}

			}
		} catch (Exception e) {
		}
		return propertyMap;
	}

	/**
	 * Methods get the complex query from the enity. It call not null property
	 * to get the properties from the object then iterates through the map to
	 * create mongo query
	 * 
	 * @param entity
	 *            extends {@link BaseDataObject}
	 * @return {@link Query}
	 */
	public static <T extends BaseDataObject> Query getQuery(T entity) {
		Map<String, Object> notNullPropertyMap = getNotNullProperties(entity);
		return getQuery(notNullPropertyMap);
	}

	/**
	 * Methods get the complex query from the enity. It call not null property
	 * to get the properties from the object then iterates through the map to
	 * create mongo query
	 * 
	 * @param paramMap Map<String,Object>
	 * @return {@link Query}
	 */
	public static Query getQuery(Map<String, Object> paramMap) {
		Criteria criteria = new Criteria();
		Query query = new Query();
		Integer iterator = 0;

		for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (iterator == 0) {
				getCriteria(key, value, criteria, query);
			} else {
				getCriteriaWithAnd(key, value, criteria, query);
			}

			iterator++;
		}

		return query;
	}

	/**
	 * Methods get the complex query from the enity. It call not null property
	 * to get the properties from the object then iterates through the map to
	 * create mongo query
	 * 
	 * @param paramList List<{@link SearchKeyValuePair>}
	 * @return {@link Query}
	 */
	public static Query getQuery(List<SearchKeyValuePair> paramList) {
		Criteria criteria = new Criteria();
		Query query = new Query();
		Integer iterator = 0;

		for (SearchKeyValuePair searchKeyValuePair : paramList) {
			String key = searchKeyValuePair.getKey();
			Object value = searchKeyValuePair.getValue();
			if (iterator == 0) {
				getCriteria(key, value, criteria, query);
			} else {
				getCriteriaWithAnd(key, value, criteria, query);
			}

			iterator++;
		}

		return query;
	}

	/**
	 * Method to get criteria based on DELIMITER 
	 * {LT_DELIMITER -> lt}
	 * {GT_DELIMITER -> gt}
	 * {REGEX_DELIMITER -> regex expression}
	 * {SORT_ASC_DELIMITER -> sort asc}
	 * {SORT_DESC_DELIMITER -> sort desc}
	 * {LTE_DELIMITER -> lte}
	 * {GTE_DELIMITER -> gte}
	 * {IN_DELIMITER -> in clause}
	 * @param key
	 * @param value
	 * @param criteria
	 * @param query
	 */
	private static void getCriteria(String key, Object value,
			Criteria criteria, Query query) {
		String[] keyArr = key.split(SPLIT_DELIMITER);

		if (keyArr.length > 1) {
			String resolutionCriteria = keyArr[1];
			String actualKey = keyArr[0];

			switch (resolutionCriteria) {
			case LT_DELIMITER:
				criteria = Criteria.where(actualKey).lt(value);
				break;
			case GT_DELIMITER:
				criteria = Criteria.where(actualKey).gt(value);
				break;
			case REGEX_DELIMITER:
				criteria = Criteria.where(actualKey).regex(value.toString());
				break;
			case SORT_ASC_DELIMITER:
				query.with(new Sort(Sort.Direction.ASC, value.toString()));
				break;
			case SORT_DESC_DELIMITER:
				query.with(new Sort(Sort.Direction.DESC, value.toString()));
				break;
			case LTE_DELIMITER:
				criteria = Criteria.where(actualKey).lte(value);
				break;
			case GTE_DELIMITER:
				criteria = Criteria.where(actualKey).gte(value);
				break;
			case IN_DELIMITER:
				criteria = Criteria.where(actualKey).in(value);
				break;
			default:
				throw new IllegalArgumentException(
						"Arguments in the query parameter does not match");
			}
		} else {
			criteria = Criteria.where(key).is(value);
		}

		query.addCriteria(criteria);
	}

	/**
	 * Method to get criteria based on delimiter , appends to existing criteria
	 * {LT_DELIMITER -> lt}
	 * {GT_DELIMITER -> gt}
	 * {REGEX_DELIMITER -> regex expression}
	 * {SORT_ASC_DELIMITER -> sort asc}
	 * {SORT_DESC_DELIMITER -> sort desc}
	 * {LTE_DELIMITER -> lte}
	 * {GTE_DELIMITER -> gte}
	 * {IN_DELIMITER -> in clause}
	 * @param key
	 * @param value
	 * @param criteria
	 * @param query
	 */
	private static void getCriteriaWithAnd(String key, Object value,
			Criteria criteria, Query query) {
		String[] keyArr = key.split(SPLIT_DELIMITER);

		if (keyArr.length > 1) {
			String resolutionCriteria = keyArr[1];
			String actualKey = keyArr[0];

			switch (resolutionCriteria) {
			case LT_DELIMITER:
				criteria = criteria.and(actualKey).lt(value);
				break;
			case GT_DELIMITER:
				criteria = criteria.and(actualKey).gt(value);
				break;
			case REGEX_DELIMITER:
				criteria = criteria.and(actualKey).regex(value.toString());
				break;
			case SORT_ASC_DELIMITER:
				query.with(new Sort(Sort.Direction.ASC, value.toString()));
				break;
			case SORT_DESC_DELIMITER:
				query.with(new Sort(Sort.Direction.DESC, value.toString()));
				break;
			case LTE_DELIMITER:
				criteria = criteria.and(actualKey).lte(value);
				break;
			case GTE_DELIMITER:
				criteria = criteria.and(actualKey).gte(value);
				break;
			case IN_DELIMITER:
				criteria = criteria.and(actualKey).in(value);
				break;
			default:
				throw new IllegalArgumentException(
						"Arguments in the query parameter does not match");
			}

		}
	}

}