package com.ideatec.datamigration.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideatec.datamigration.bwmonitor.dao.TibcoMapper;
import com.ideatec.datamigration.bwmonitor.dto.ItemDto;
import com.ideatec.datamigration.bwmonitor.dto.OrderDto;
import com.ideatec.datamigration.obedm.dao.EdmMapper;
import com.ideatec.datamigration.obedm.dto.ErpInvoiceDto;
import com.ideatec.datamigration.obedm.dto.InvoiceDto;
import com.ideatec.datamigration.obedm.entity.AccountVo;
import com.ideatec.datamigration.obedm.entity.ItemVo;
import com.ideatec.datamigration.util.PoiConfig;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
@Slf4j
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
		poiConfig.generateExclFromQuery(parseList, "order_get_item_count");

		return resultList;
	}

	public List<Map<String, Object>> orderInvoiceDataParser(Map<String, Object> param) {

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
					Map<String, Object> edmMap = edmMapper.getInvoiceData(newParam);
					copyMap.put("BRN", edmMap.get("brn"));
					copyMap.put("EDM_Unity Qty", edmMap.get("unitQuantity")==null?0: ((BigDecimal) edmMap.get("unitQuantity")).intValue());
					copyMap.put("EDM_Box Qty", edmMap.get("boxQuantity")==null?0: ((BigDecimal) edmMap.get("boxQuantity")).intValue());

					parseList.add(copyMap);
				});

			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		poiConfig.generateExclFromQuery(parseList, "order_get_item_count");

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

	/**
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : orderInvoiceCompare
	 * @date : 2023. 11. 15.
	 * @author : minco
	 * @history :
	 * ----------------------------------------------------------------------------------
	 * 변경일                        작성자                              변경내역
	 * -------------- -------------- ----------------------------------------------------
	 * 2023. 11. 15.  minco       최초작성
	 * ----------------------------------------------------------------------------------
	 */
	public List<Map<String, Object>> orderInvoiceCompare(Map<String, Object> param, String datas) {
		ErpInvoiceDto invoiceDto =   ErpInvoiceDto.getInvoiceDtoByJson(datas).get();

		log.info("invoice:{}", invoiceDto);

		param.put("wsId", "6078118197");
		param.put("deliveryDate", "2023-11-13");
		List<String> ordersList = tibcoMapper.getTibcoOrderForDto(param);
		ObjectMapper mapper = new ObjectMapper();


		List<OrderDto> orders = ordersList.stream().map(orderJson -> {
			try {
				return mapper.readValue(orderJson, OrderDto.class);
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}).collect(Collectors.toList());
		log.info("orders:{}", orders.get(0).getOrderNumber());

		List<InvoiceDto> invoices = edmMapper.getInvoiceSendData(param)
				.stream()
				.map(m -> {
					return InvoiceDto.getInvoiceDtoByJson((String)m.get("datas")).get();
				})
				.collect(Collectors.toList());

		List<Map<String, Object>> parseList = invoiceDto.getPayload()
				.stream()
				.map(invoice -> {
			Map<String, Object> map = new LinkedHashMap<>();

			orders.stream()
			.filter(o -> o.getOrderNumber().equals(invoice.getOrderId()))
			.forEach(o -> {

					param.put("accountId", invoice.getAccountId());
					AccountVo accountVo = edmMapper.getAccountByWsAndId(param);
					boolean flag = false;
					for(ItemDto i : o.getItems()) {
						if(i.getSku().equals(invoice.getSku())){
							flag = true;
							map.put("orderNumber", invoice.getOrderId());
							map.put("order account Id", o.getVendor().getAccountId());
							map.put("invoice account Id", invoice.getAccountId());
							map.put("Account BRN", accountVo.getCustomerAccountId());
							map.put("invoice sku", invoice.getSku());
							map.put("invoice Unit Qty", invoice.getUnitQuantity());
							map.put("invoice Box Qty", invoice.getBoxQuantity());
							map.put("order sku", i.getSku());
							map.put("order package Name", i.getPackage1().getName());
							map.put("order package item count", i.getPackage1().getItemCount());
							map.put("order item quantity" , i.getQuantity());
						}
					}
					if(!flag) {
						map.put("orderNumber", invoice.getOrderId());
						map.put("order account Id", o.getVendor().getAccountId());
						map.put("invoice account Id", invoice.getAccountId());
						map.put("Account BRN", accountVo.getCustomerAccountId());
						map.put("invoice sku", invoice.getSku());
						map.put("invoice Unit Qty", invoice.getUnitQuantity());
						map.put("invoice Box Qty", invoice.getBoxQuantity());
						map.put("order sku", "N/A");
						map.put("order package Name", "N/A");
						map.put("order package item count", "N/A");
						map.put("order item quantity" , 0);
					}
					map.put("invoice send Item Quantity", 0);
					map.put("EDM package Name", "");
					map.put("EDM package Item Count", 0);
					invoices.stream()
						.filter(send -> send.getPayload().getOrderId().equals(invoice.getOrderId()))
						.forEach(send -> {
								int itemQuantity = send.getPayload().getItems().stream()
								.filter(item -> item.getVendorItemId().equals(invoice.getSku()))
								.findFirst().get().getQuantity();
								map.put("invoice send Item Quantity", itemQuantity);

								param.put("sku", invoice.getSku());
								ItemVo itemVo = edmMapper.getItemByWsAndSku(param);
								map.put("EDM package Name", itemVo.getPackageName());
								map.put("EDM package Item Count", itemVo.getPackageItemCount());
							}
						);
			});
			log.info("map:{}", map);
			return map;

		})
		.filter(map -> map.size() > 0)
		.collect(Collectors.toList());
		return parseList;
	}
}
