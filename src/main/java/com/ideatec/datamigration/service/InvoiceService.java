package com.ideatec.datamigration.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideatec.datamigration.bwmonitor.dao.TibcoMapper;
import com.ideatec.datamigration.bwmonitor.dto.ItemDto;
import com.ideatec.datamigration.bwmonitor.dto.OrderDto;
import com.ideatec.datamigration.obedm.dao.EdmMapper;
import com.ideatec.datamigration.util.PoiConfig;

import lombok.AllArgsConstructor;

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
@AllArgsConstructor
public class InvoiceService {


	private TibcoMapper tibcoMapper;

	private EdmMapper edmMapper;

	private PoiConfig poiConfig;

	public List<Map<String, Object>> tibcoOrderDataParser(Map<String, Object> param) {

		List<Map<String, Object>> resultList = tibcoMapper.getTibcoOrder(param);
		ObjectMapper objectMapper = new ObjectMapper();

		List<Map<String, Object>> parseList = new ArrayList<>();

		for(Map<String, Object> map : resultList) {
			try {
				OrderDto order = objectMapper.readValue((String)map.get("datas"), OrderDto.class);

				Map<String, Object> parseMap = getOrderMapping(order);
				order.getItems().stream().forEach(item -> {

					Map<String, Object> copyMap = getItemMapping(parseMap, item);
					Map<String, Object> newParam = new HashMap<>();
					newParam.putAll(param);
					newParam.put("sku",item.getSku());
					newParam.put("orderNumber",order.getOrderNumber());
					Map<String, Object> edmMap = edmMapper.getOrderData(newParam);
					copyMap.put("BRN", edmMap.get("brn"));
					copyMap.put("EDM_Unity Qty", edmMap.get("unitQuantity")==null?0: ((BigDecimal) edmMap.get("unitQuantity")).intValue());
					copyMap.put("EDM_Box Qty", edmMap.get("boxQuantity")==null?0: ((BigDecimal) edmMap.get("boxQuantity")).intValue());

					parseList.add(copyMap);
				});

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		poiConfig.generateExclFromQuery(parseList, "invoice_order");

		return resultList;
	}

	/**
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getOrderMapping
	 * @date : 2023. 11. 14.
	 * @author : minco
	 * @history :
	 * ----------------------------------------------------------------------------------
	 * 변경일                        작성자                              변경내역
	 * -------------- -------------- ----------------------------------------------------
	 * 2023. 11. 14.  minco       최초작성
	 * ----------------------------------------------------------------------------------
	 */
	private Map<String, Object> getOrderMapping(OrderDto order) {
		Map<String, Object> parseMap = new LinkedHashMap<>();

		parseMap.put("Order Number", order.getOrderNumber());
		parseMap.put("Account ID", order.getVendor().getAccountId());
		parseMap.put("Delivery Date", order.getDelivery().getDate());
		return parseMap;
	}

	/**
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getItemMapping
	 * @date : 2023. 11. 14.
	 * @author : minco
	 * @history :
	 * ----------------------------------------------------------------------------------
	 * 변경일                        작성자                              변경내역
	 * -------------- -------------- ----------------------------------------------------
	 * 2023. 11. 14.  minco       최초작성
	 * ----------------------------------------------------------------------------------
	 */
	private Map<String, Object> getItemMapping(Map<String, Object> parseMap, ItemDto item) {
		Map<String, Object> copyMap = new LinkedHashMap<>();
		copyMap.putAll(parseMap);
		copyMap.put("Sku", item.getSku());
		copyMap.put("Pakcage Name", item.getPackage1().getName());
		copyMap.put("Pakcage ItemCount", item.getPackage1().getItemCount());
		copyMap.put("Item Quantity", item.getQuantity());
		return copyMap;
	}
}
