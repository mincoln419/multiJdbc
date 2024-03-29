package com.ideatec.datamigration.bwmonitor.dao;

import java.util.List;
import java.util.Map;

import com.ideatec.datamigration.bwmonitor.dto.OrderDto;

/**
 * <pre>
 * com.ideatec.datamigration.bwmonitor.dao
 * TibcoMapper.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 1:15:49
 * @desc    :
 * @version : x.x
 */
public interface TibcoMapper {

	public List<Map<String, Object>> test2();

	public List<Map<String, Object>> getTibcoOrder(Map<String, Object> param);

	public List<String> getTibcoOrderForDto(Map<String, Object> param);


}
