package com.ideatec.datamigration.obedm.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.entity
 * AccountVo.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 16. 오후 3:32:20
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVo {

	private Integer accountSeq;
	private String wsId;
	private String accountId;
	private LocalDateTime insertDtm;
	private LocalDateTime updateDtm;
	private String customerAccountId;
	private String pocName;
	private String channel;
	private String phone;
	private LocalDateTime createdAt;
	private String zipCode;
	private String address;
	private String deliveryCenterId;
	private String deliveryScheduleId;
	private String displayName;
	private String hasEmptiesLoanYn;
	private String liquorLicenseNumber;
	private String liquorLicenseStatus;
	private String liquorLicenseExpirationDt;
	private String salesRepresentativeName;
	private String ownerName;
	private String ownerPhone;
	private String ownerEmail;
	private String itemPriceId;
	private String pocStatus;
	private String metadata;
	private String salesRoute;
}
