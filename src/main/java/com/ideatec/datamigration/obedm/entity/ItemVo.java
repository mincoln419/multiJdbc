package com.ideatec.datamigration.obedm.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.entity
 * ItemVo.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 16. 오후 12:19:55
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemVo {
	private Integer item_seq;
	private String wsId;
	private String vendorItemId;
	private LocalDateTime insertDtm;
	private LocalDateTime updateDtm;
	private String itemName;
	private String sku;
	private String brandId;
	private String brandName;
	private Integer minOrderQuantity;
	private String isAlcoholic;
	private String isNarcotic;
	private String defaultLanguage;
	private String manufacturerId;
	private String hasBeenUpdated;
	private String itemType;
	private String uncategorized;
	private String containerName;
	private Integer containerSize;
	private String containerUnitOfMeasurement;
	private String containerReturnable;
	private String containerMaterial;
	private String supplierShortName;
	private String countryOfOrigin;
	private String upc;
	private String upcCase;
	private String upcUnit;
	private Integer ozPerCase;
	private String abv;
	private String description;
	private String hidden;
	private String productClass;
	private String purpose;
	private String itemTaxType;
	private String enabled;
	private String packageId;
	private String packageName;
	private Integer packageCount;
	private String packageItemCount;
	private String packagePack;
	private String packageSize;
	private String packageUnitOfMeasurement;
	private String image;
	private String vendorCategoryId;
}
