package com.ideatec.datamigration.obedm.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideatec.datamigration.bwmonitor.dto.OrderDto;
import com.ideatec.datamigration.obedm.dao.EdmMapper;

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

	@Test
	void invoice_interface_payload_test() throws JsonMappingException, JsonProcessingException {

		Map<String, Object> param = new HashMap<>();

		param.put("wsId", "6078118197");
		param.put("deliveryDate", "2023-11-14");

		Map<String, Object> result = edmMapper.getInvoiceData(param);

		String datas = ((String)result.get("datas"));

		log.info("invoice:{}", InvoiceDto.getInvoiceDtoByJson(datas));


	}

}
