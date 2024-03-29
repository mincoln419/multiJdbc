package com.ideatec.datamigration.bwmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.bwmonitor.dto
 * PackageDto.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 2:44:20
 * @desc    :
 * @version : x.x
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PackageDto {

	private String packageId;

	private Integer itemCount;

	private String name;

	private String pack;

	private Integer unitCount;
}
