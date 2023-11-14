package com.ideatec.datamigration.obedm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.dto
 * InvoiceItemDto.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 14. 오후 4:21:47
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItemDto {

	private String vendorItemId;

	private Integer price;

	private Integer quantity;

	private Integer tax;

	private Integer subtotal;

	private Integer total;

	private Integer discount;


}
