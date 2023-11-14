package com.ideatec.datamigration.obedm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.dto
 * InvoicePayload.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 14. 오후 4:19:06
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoicePayload {
	private String date;

	private Integer deposit;

	private Integer discount;

	private List<InvoiceItemDto> items;

	private Integer itemsQuantity;

	private String orderDate;

	private String orderId;

	private String status;

	private Integer subtotal;

	private Integer total;

	private Integer tax;

	private InvoiceVendor vendor;

}
