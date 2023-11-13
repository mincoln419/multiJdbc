package com.ideatec.datamigration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ideatec.datamigration.bwmonitor.dao.TibcoMapper;
import com.ideatec.datamigration.obedm.dao.EdmMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class DataMigrationApplicationTests {

	@Autowired
	TibcoMapper tibcoMapper;

	@Autowired
	EdmMapper edmMapper;

	@Test
	void contextLoads() {

		log.info("test: {}",edmMapper.test());

		log.info("test2: {}",tibcoMapper.test2());


	}

}
