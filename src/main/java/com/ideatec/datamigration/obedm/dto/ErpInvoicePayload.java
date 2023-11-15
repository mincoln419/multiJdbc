package com.ideatec.datamigration.obedm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.dto
 * ErpInvoicePayload.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 15. 오전 9:03:16
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErpInvoicePayload {
    private String orderId;
    private String orderDate;
    private String deliveryDate;
    private String type;
    private String invoiceId;
    private Integer sequence;
    private String accountId;
    private String salesRoute;
    private String sku;
    private Integer price;
    private Integer perUnit;
    private Integer boxQuantity;
    private Integer unitQuantity;
    private Integer supplyPrice;
    private Integer tax;
    private Integer deposit;
    private Integer bottleDeposit;
}
