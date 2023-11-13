package com.ideatec.datamigration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideatec.datamigration.bwmonitor.dao.TibcoMapper;
import com.ideatec.datamigration.obedm.dao.EdmMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DataMigrationApplicationTests {

	@Resource
	TibcoMapper tibcoMapper;

	@Resource
	EdmMapper edmMapper;

	ObjectMapper mapper = new ObjectMapper();

	@Test
	void contextLoads() {

		log.info("test: {}",edmMapper.test());

		log.info("test2: {}",tibcoMapper.test2());


	}

	@Test
	void orderData() throws JsonMappingException, JsonProcessingException {

		Map<String, Object> param = new HashMap<>();
		param.put("wsId", "6078118197");
		param.put("deliveryDate", "2023-11-13");
		List<Map<String, Object>> result = tibcoMapper.getTibcoOrder(param);
		String datas = result.stream()
			.findFirst().get().get("datas").toString();

		mapper.readValue(datas, OrderDto.class);

		log.info(datas);
	}

}
