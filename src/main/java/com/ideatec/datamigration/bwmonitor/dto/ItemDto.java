package com.ideatec.datamigration.bwmonitor.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.bwmonitor.dto
 * ItemDto.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 2:41:14
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

	private ContainerDto container;

	private Integer discount;

	private boolean freeGood;

	private String id;

	private String image;

	private String key;

	private String measureUnit;

	private String name;

	private Integer originalPrice;

	@JsonProperty(value = "package")
	private PackageDto package1;

	private Integer price;

	private Integer quantity;

	private String sku;

	private Integer subtotal;

	private Integer tax;

	private Integer total;

	private Integer totalExclCharges;

	private String vendorItemId;

}
