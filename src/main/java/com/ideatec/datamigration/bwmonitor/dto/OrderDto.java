package com.ideatec.datamigration.bwmonitor.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	private List<ItemDto> items;



}
