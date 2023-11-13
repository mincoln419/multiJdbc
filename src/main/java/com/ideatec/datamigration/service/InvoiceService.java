package com.ideatec.datamigration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ideatec.datamigration.bwmonitor.dao.TibcoMapper;
import com.ideatec.datamigration.obedm.dao.EdmMapper;

/**
 * <pre>
 * com.ideatec.datamigration.service
 * InvoiceService.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 2:11:05
 * @desc    :
 * @version : x.x
 */
@Service
public class InvoiceService {


	@Resource
	TibcoMapper tibcoMapper;

	@Resource
	EdmMapper edmMapper;

	public List<Map<String, Object>> tibcoOrderDataParser(String deliveryDate) {

		List<Map<String, Object>> resultList = new ArrayList<>();

		return resultList;
	}
}
