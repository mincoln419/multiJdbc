package com.ideatec.datamigration.obedm.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideatec.datamigration.bwmonitor.dao.TibcoMapper;
import com.ideatec.datamigration.bwmonitor.dto.ItemDto;
import com.ideatec.datamigration.bwmonitor.dto.OrderDto;
import com.ideatec.datamigration.obedm.dao.EdmMapper;
import com.ideatec.datamigration.service.InvoiceService;
import com.ideatec.datamigration.util.PoiConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.dto
 * InvoiceDtoTest.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 14. 오후 4:25:13
 * @desc    :
 * @version : x.x
 */
@SpringBootTest
@Slf4j
class InvoiceDtoTest {

	@Autowired
	private EdmMapper edmMapper;

	@Autowired
	private TibcoMapper tibcoMapper;

	@Autowired
	private InvoiceService invoiceService;


	@Autowired
	private PoiConfig poi;

	@Test
	void invoice_interface_payload_test() throws JsonMappingException, JsonProcessingException {

		Map<String, Object> param = new HashMap<>();

		Map<String, Object> result = edmMapper.getInvoiceData(param);

		String datas = ((String)result.get("datas"));

		List<Map<String, Object>> parseList = invoiceService.orderInvoiceCompare(param, datas);

		log.info("parseList: {}", parseList);
		poi.generateExclFromQuery(parseList, "order_invoice");

	}



}
