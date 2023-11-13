package com.ideatec.datamigration.bwmonitor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * com.ideatec.datamigration.bwmonitor.dto
 * Interest.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 2:40:29
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Interest {

	private Integer loanDeduction;

	private Integer total;
}
