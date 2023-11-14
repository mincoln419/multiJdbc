package com.ideatec.datamigration.bwmonitor.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideatec.datamigration.obedm.dto.InvoiceDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.ideatec.datamigration.bwmonitor.dto
 * OrderDto.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 2:37:24
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class OrderDto {
	private String accountId;
	private String channel;
	private Integer chargeAmount;
	private DeliveryDto delivery;
	private String deliveryCenterId;
	private Integer deposit;
	private Integer discount;
	private Empties empties;
	private Integer extraEmptiesDiscountAmount;
	private Interest interest;
	private Integer interestAmount;
	private List<ItemDto> items = new ArrayList<>();
	private Integer itemsQuantity;
	private Integer loanDeduction;
	private String note;
	private String orderNumber;
	private Date placementDate;
	private String previousStatus;

	private String status;

	private Integer subtotal;

	private Integer tax;

	private Integer total;

	private Date updatedAt;

	private Vendor vendor;


	static public Optional<OrderDto> getOrderDtoByJson(String datas) {
		ObjectMapper mapper = new ObjectMapper();
		Optional<OrderDto> order = Optional.empty();
		try {
			order = Optional.of(mapper.readValue(datas, OrderDto.class));
		} catch (JsonProcessingException e) {
			log.error(e.getLocalizedMessage());
		}
		return order;
	}
}
