package com.ideatec.datamigration.bwmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.bwmonitor.dto
 * ContainerDto.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 2:41:52
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerDto {

	private Integer itemSize;

	private String name;

	private boolean returnable;

	private Integer size;

	private String unitOfMeasurement;
}
