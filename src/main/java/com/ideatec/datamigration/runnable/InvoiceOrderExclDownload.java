package com.ideatec.datamigration.runnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ideatec.datamigration.obedm.dao.EdmMapper;
import com.ideatec.datamigration.service.InvoiceService;
import com.ideatec.datamigration.util.PoiConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.ideatec.datamigration.runnable
 * InvoiceOrderExclDownload.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 16. 오후 4:52:55
 * @desc    :
 * @version : x.x
 */
@Component
@Slf4j
public class InvoiceOrderExclDownload implements CommandLineRunner{


	@Autowired
	private EdmMapper edmMapper;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private PoiConfig poi;

	@Override
	public void run(String... args) throws Exception {

		if(args.length == 0) {
			log.error("no args - insert ws id and delivery date");
			return;
		}

		Map<String, Object> param = new HashMap<>();
		param.put("wsId", args[0]);
		param.put("deliveryDate", args[1]);

		Map<String, Object> result = edmMapper.getInvoiceData(param);

		String datas = ((String)result.get("datas"));

		List<Map<String, Object>> parseList = invoiceService.orderInvoiceCompare(param, datas);

		log.info("parseList: {}", parseList);
		poi.generateExclFromQuery(parseList, "order_invoice");
	}

}
