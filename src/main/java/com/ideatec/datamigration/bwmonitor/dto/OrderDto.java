package com.ideatec.datamigration.bwmonitor.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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



}
