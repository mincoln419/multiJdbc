package com.ideatec.datamigration.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Component
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

		Map<String, Object> testMap = new LinkedHashMap<>();

		testMap.put("ID", 1);
		testMap.put("NAME", "cookie");
		testMap.put("PHONE_NUMBER", "010-1111-2222");

		Map<String, Object> testMap2 = new LinkedHashMap<>();

		testMap2.put("ID", 2);
		testMap2.put("NAME", "bread");
		testMap2.put("PHONE_NUMBER", "010-3333-4444");

		Map<String, Object> testMap3 = new LinkedHashMap<>();

		testMap3.put("ID", 3);
		testMap3.put("NAME", "tanghuroo");
		testMap3.put("PHONE_NUMBER", "010-5555-6666");

		PoiConfig config = new PoiConfig();

		//list1 결과
		config.generateExcl(List.of(data), "list1");

		//list2 결과
		config.generateExclFromQuery(List.of(testMap, testMap2, testMap3), "list2");
	}

	public void generateExclFromQuery(List<Map<String, Object>> list, String fileNameAdd) {

		if(list.size() == 0) {
			log.info("처리할 데이터가 없습니다.");
			return;
		}
		fileName = fileName + fileNameAdd + ".xlsx";
		System.out.println(fileName);

		try (XSSFWorkbook workbook = new XSSFWorkbook()) {
			XSSFSheet sheet = workbook.createSheet("invoice" + getNowTime());
			Set<String> keyset = list.get(0).keySet();
			log.info("keyset: {}", keyset);
			int rowNum = 0;
			Row headRow =  sheet.createRow(rowNum++);
			int cellNum = 0;
			for (String key : keyset) {
				drawCellByMap(key, headRow, cellNum++);
			}

			for(Map<String, Object> paramMap : list) {
				Row row =  sheet.createRow(rowNum++);
				cellNum = 0;
				for (String key : keyset) {
					drawCellByMap(paramMap.get(key), row, cellNum++);
				}
			}


			try (FileOutputStream out = new FileOutputStream(new File(filePath, fileName))) {
				workbook.write(out);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
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
	 * @Method Name : drawCellByMap
	 * @date : 2023. 11. 14.
	 * @author : minco
	 * @history :
	 * ----------------------------------------------------------------------------------
	 * 변경일                        작성자                              변경내역
	 * -------------- -------------- ----------------------------------------------------
	 * 2023. 11. 14.  minco       최초작성
	 * ----------------------------------------------------------------------------------
	 */
	private void drawCellByMap(Object obj, Row headRow, int cellNum) {
		Cell cell = headRow.createCell(cellNum);
		if (obj instanceof String) {
			cell.setCellValue((String) obj);
		} else if (obj instanceof Integer) {
			cell.setCellValue((Integer) (obj));
		}
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
