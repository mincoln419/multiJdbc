package com.ideatec.datamigration.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * <pre>
 * com.ideatec.datamigration.service
 * InvoiceServiceTest.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 14. 오전 11:25:01
 * @desc    :
 * @version : x.x
 */
@SpringBootTest
class InvoiceServiceTest {

	@Autowired
	InvoiceService invoiceService;

	@Test
	void testTibcoOrderDataParser() {
		Map<String, Object> param = new HashMap<>();
		param.put("wsId", "6078118197");
		param.put("deliveryDate", "2023-11-13");
		invoiceService.tibcoOrderDataParser(param);
	}

}
