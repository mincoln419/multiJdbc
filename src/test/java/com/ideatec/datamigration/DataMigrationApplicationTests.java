package com.ideatec.datamigration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideatec.datamigration.bwmonitor.dao.TibcoMapper;
import com.ideatec.datamigration.bwmonitor.dto.OrderDto;
import com.ideatec.datamigration.obedm.dao.EdmMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DataMigrationApplicationTests {

	@Resource
	TibcoMapper tibcoMapper;

	@Resource
	EdmMapper edmMapper;


	@Test
	void contextLoads() {

		log.info("test: {}",edmMapper.test());

		log.info("test2: {}",tibcoMapper.test2());


	}

	@Test
	void orderData() throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> param = new HashMap<>();
		param.put("wsId", "6078118197");
		param.put("deliveryDate", "2023-11-13");
		List<Map<String, Object>> result = tibcoMapper.getTibcoOrder(param);
		String datas = result.stream()
			.findFirst().get().get("datas").toString();
		OrderDto order = mapper.readValue(datas, OrderDto.class);

		log.info("order:{}", order);
	}

}
