package com.ideatec.datamigration.obedm.dto;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.dto
 * ErpInvoiceDto.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 15. 오전 8:58:08
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ErpInvoiceDto {

	private String entity;

	private String version;

	private List<ErpInvoicePayload> payload;



	static public Optional<ErpInvoiceDto> getInvoiceDtoByJson(String datas) {

		ObjectMapper mapper = new ObjectMapper();
		Optional<ErpInvoiceDto> invoice = Optional.empty();
		try {
			invoice = Optional.of(mapper.readValue(datas, ErpInvoiceDto.class));
		} catch (JsonProcessingException e) {
			log.error(e.getLocalizedMessage());
		}
		return invoice;
	}
}
