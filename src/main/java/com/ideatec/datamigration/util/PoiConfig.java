package com.ideatec.datamigration.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * <pre>
 * com.ideatec.datamigration.util
 * PoiConfig.java
 * </pre>
 *
 * @author  : minco
 * @date    : 2023. 11. 13. 오후 3:44:18
 * @desc    :
 * @version : x.x
 */
public class PoiConfig {

	public String filePath = "c:\\data-migration\\excl";
	public String fileName = String.format("invoice_order_%s", getNowTime());

	public static void main(String[] args) {

		Map<String, Object[]> data = new TreeMap<>();

		data.put("1",new Object[] {"ID", "NAME", "PHONE_NUMBER"});
		data.put("2",new Object[] {"1", "cookie", "010-1111-2222"});
		data.put("3",new Object[] {"2", "bread", "010-3333-4444"});
		data.put("4",new Object[] {"3", "tanghuroo", "010-5555-6666"});
		data.put("5",new Object[] {"4", "majenta", "010-7777-8888"});


		List<Map<String, Object>> list = new ArrayList<>();

		Map<String, Object> testMap = new HashMap<>();

		testMap.put("ID", 1);
		testMap.put("NAME", "cookie");
		testMap.put("PHONE_NUMBER", "010-1111-2222");

		Map<String, Object> testMap2 = new HashMap<>();

		testMap2.put("ID", 2);
		testMap2.put("NAME", "bread");
		testMap2.put("PHONE_NUMBER", "010-3333-4444");

		Map<String, Object> testMap3 = new HashMap<>();

		testMap3.put("ID", 3);
		testMap3.put("NAME", "tanghuroo");
		testMap3.put("PHONE_NUMBER", "010-5555-6666");

		PoiConfig config = new PoiConfig();

		//list1 결과
		config.generateExcl(List.of(data), "list1");

		//list2 결과
		List<Map<String, Object[]>> list2 = config.datMapping(List.of(testMap));

		config.generateExcl(list2, "list2");
	}

	/**
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : datMapping
	 * @date : 2023. 11. 13.
	 * @author : minco
	 * @history :
	 * ----------------------------------------------------------------------------------
	 * 변경일                        작성자                              변경내역
	 * -------------- -------------- ----------------------------------------------------
	 * 2023. 11. 13.  minco       최초작성
	 * ----------------------------------------------------------------------------------
	 */
	private List<Map<String, Object[]>> datMapping(List<Map<String, Object>> params) {

		return params.stream().map(map -> {
			Set<String> keyset = map.keySet();
			Map<String, Object[]> data = new TreeMap<>();



			return data;
		}).collect(Collectors.toList());
	}

	/**
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : generateExcl
	 * @date : 2023. 11. 13.
	 * @author : minco
	 * @history :
	 * ----------------------------------------------------------------------------------
	 * 변경일                        작성자                              변경내역
	 * -------------- -------------- ----------------------------------------------------
	 * 2023. 11. 13.  minco       최초작성
	 * ----------------------------------------------------------------------------------
	 */
	private void generateExcl(List<Map<String, Object[]>> list, String fileNameAdd) {

		fileName = fileName + fileNameAdd + ".xlsx";
		System.out.println(fileName);

		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet("invoice" + getNowTime());

			list.stream().forEach(data -> {

				Set<String> keyset = data.keySet();
				int rownum = 0;
				for (String key : keyset) {
					Row row = sheet.createRow(rownum++);
					Object[] objArr = data.get(key);
					int cellnum = 0;
					for (Object obj : objArr) {
						Cell cell = row.createCell(cellnum++);
						if (obj instanceof String) {
							cell.setCellValue((String) obj);
						} else if (obj instanceof Integer) {
							cell.setCellValue((Integer) (obj));
						}
					}
				}

				try (FileOutputStream out = new FileOutputStream(new File(filePath, fileName))) {
					workbook.write(out);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * <pre>
	 * 1. 개요 :
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getNowTime
	 * @date : 2023. 11. 13.
	 * @author : minco
	 * @history :
	 * ----------------------------------------------------------------------------------
	 * 변경일                        작성자                              변경내역
	 * -------------- -------------- ----------------------------------------------------
	 * 2023. 11. 13.  minco       최초작성
	 * ----------------------------------------------------------------------------------
	 */
	private String getNowTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss"));
	}
}
