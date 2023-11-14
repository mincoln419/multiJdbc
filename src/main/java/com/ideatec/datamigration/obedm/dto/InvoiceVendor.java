package com.ideatec.datamigration.obedm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.dto
 * InvoiceVendor.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 14. 오후 4:24:01
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceVendor {

	private String accountId;

	private String invoiceId;
}
