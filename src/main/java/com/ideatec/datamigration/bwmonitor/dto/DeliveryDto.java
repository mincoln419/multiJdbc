package com.ideatec.datamigration.bwmonitor.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.bwmonitor.dto
 * DeliveryDto.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 2:38:49
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {

	private boolean alternative;

	private Date date;

	private String windowId;
}
