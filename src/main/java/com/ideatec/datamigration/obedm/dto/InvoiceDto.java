package com.ideatec.datamigration.obedm.dto;

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
 * InvoiceDto.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 14. 오후 4:16:40
 * @desc    :
 * @version : x.x
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class InvoiceDto {
	private String entity;

	private String version;

	private InvoicePayload payload;


	static public Optional<InvoiceDto> getInvoiceDtoByJson(String datas) {

		ObjectMapper mapper = new ObjectMapper();
		Optional<InvoiceDto> invoice = Optional.empty();
		try {
			invoice = Optional.of(mapper.readValue(datas, InvoiceDto.class));
		} catch (JsonProcessingException e) {
			log.error(e.getLocalizedMessage());
		}
		return invoice;
	}
}
