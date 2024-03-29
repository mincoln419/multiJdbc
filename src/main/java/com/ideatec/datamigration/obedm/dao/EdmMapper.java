package com.ideatec.datamigration.obedm.dao;

import java.util.List;
import java.util.Map;

import com.ideatec.datamigration.obedm.entity.AccountVo;
import com.ideatec.datamigration.obedm.entity.ItemVo;

/**
 * <pre>
 * com.ideatec.datamigration.obedm.dao
 * EdmMapper.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 1:17:27
 * @desc    :
 * @version : x.x
 */
public interface EdmMapper {

	public List<Map<String, Object>> test();

	public Map<String, Object> getOrderData(Map<String, Object> param);

	public Map<String, Object> getInvoiceData(Map<String, Object> param);

	public List<Map<String, Object>> getInvoiceSendData(Map<String, Object> param);

	public ItemVo getItemByWsAndSku(Map<String, Object> param);

	public AccountVo getAccountByWsAndId(Map<String, Object> param);
}
