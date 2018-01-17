package com.hzdracom.manager.util.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.util.CellRangeAddress;


public class ExcelFileGenerator {
	public static short  MY_COLOR_INDEX = 64;
	// 创建workbook
	public static HSSFWorkbook createWorkBook() {
		return new HSSFWorkbook();
	}

	// 创建sheet
	public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
		HSSFSheet sheet = wb.createSheet(sheetName);
		sheet.setDefaultColumnWidth(15);
		return sheet;
	}

	//自定义颜色
	public static short createColorFromHex(HSSFWorkbook wb, String hexStr) {
		//处理把它转换成十六进制并放入一个数
		int[] color=new int[3];
		  color[0]=Integer.parseInt(hexStr.substring(1, 3), 16);
		  color[1]=Integer.parseInt(hexStr.substring(3, 5), 16);
		  color[2]=Integer.parseInt(hexStr.substring(5, 7), 16);
		 //自定义颜色
		  HSSFPalette palette = wb.getCustomPalette();
		  palette.setColorAtIndex(MY_COLOR_INDEX,(byte)color[0], (byte)color[1], (byte)color[2]);
		  return MY_COLOR_INDEX;
	}
	
	// 设置表头样式
	public static Map createCellStyle(HSSFWorkbook wb) {
		HSSFFont fontinfo = wb.createFont();
		fontinfo.setFontHeightInPoints((short) 8); // 字体大小
		HSSFFont fonthead = wb.createFont();
		fonthead.setColor(HSSFColor.BLACK.index);
		fonthead.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		HSSFCellStyle cellStylename = wb.createCellStyle();// 表名样式
		cellStylename.setFont(fonthead);
		HSSFCellStyle cellStyleinfo = wb.createCellStyle();// 表信息样式
		cellStyleinfo.setAlignment(HSSFCellStyle.ALIGN_RIGHT);// 对齐
		cellStyleinfo.setFont(fontinfo);
		HSSFCellStyle cellStylehead = wb.createCellStyle();// 表头样式
		cellStylehead.setFont(fonthead);
		cellStylehead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStylehead.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStylehead.setBorderBottom(HSSFCellStyle.BORDER_THIN);// 边框
		cellStylehead.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStylehead.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStylehead.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStylehead.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStylehead.setRightBorderColor(HSSFColor.BLACK.index);
		cellStylehead.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStylehead.setTopBorderColor(HSSFColor.BLACK.index);
		cellStylehead.setFillForegroundColor(HSSFColor.WHITE.index);
		cellStylehead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		HSSFCellStyle cellStyle = wb.createCellStyle();// 数据单元样式
		cellStyle.setWrapText(false);// 自动换行
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBottomBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setLeftBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setRightBorderColor(HSSFColor.BLACK.index);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setTopBorderColor(HSSFColor.BLACK.index);
		Map map = new HashMap();
		map.put("cellStylename", cellStylename);
		map.put("cellStyleinfo", cellStyleinfo);
		map.put("cellStylehead", cellStylehead);
		map.put("cellStyle", cellStyle);
		return map;
	}

	// 生成表头
	// 1.需要返回表头占用的行数
	public static int generateMultiHeader(HSSFSheet sheet,
			Map<String, HSSFCellStyle> cellStyleMap, String[] header,
			String reportName, Map xlsInfoMap) {
		int rows_max = 0;
		HSSFCellStyle cellStylename = null;
		HSSFCellStyle cellStyleinfo = null;
		HSSFCellStyle cellStylehead = null;
		if (cellStyleMap != null && cellStyleMap.size() > 0) {
			cellStylename = (HSSFCellStyle) cellStyleMap.get("cellStylename");
			cellStyleinfo = (HSSFCellStyle) cellStyleMap.get("cellStyleinfo");
			cellStylehead = (HSSFCellStyle) cellStyleMap.get("cellStylehead");
		}
		try {
			int col = header.length;
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(cellStylename);
			cell.setCellValue(reportName);// 表名
			sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 1));
			
			cell = row.createCell((short) 2);
			cell.setCellStyle(cellStyleinfo);
			//获取excel导出人、导出时间等信息
			String exporter = (String) xlsInfoMap.get("EXPORTER");
			String export_date = (String) xlsInfoMap.get("EXPORT_DATE");
			cell.setCellValue("统计员: "+exporter+"        统计时间: "+export_date); // 信息
			sheet.addMergedRegion(new Region(0, (short) 2, 0, (short) (col < 2 ? 2 :col)));

			for (int i = 0; i < header.length; i++) {
				String h = header[i];
				if (h.split("_").length > rows_max) {
					rows_max = h.split("_").length;
				}
			}
			Map map = new HashMap();
			for (int k = 0; k < rows_max; k++) {
				row = sheet.createRow((short) k + 1);
				if (k == 0) {
					cell = row.createCell((short) (0));
					cell.setCellStyle(cellStylehead);
					cell.setCellValue("序号");
					sheet.addMergedRegion(new Region(k + 1, (short) 0, k
							+ rows_max, (short) 0));
				}
				for (int i = 0; i < header.length; i++) {
					String headerTemp = header[i];
					String[] s = headerTemp.split("_");
					String sk = "";
					int num = i + 1;
					if (s.length == 1) {
						cell = row.createCell((short) (num));
//						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(cellStylehead);
						sheet.addMergedRegion(new Region(1, (short) (num),
								rows_max, (short) (num)));
						sk = headerTemp;
						cell.setCellValue(sk);
					} else {
						System.out.println(sk);

						cell = row.createCell((short) (num));
//						cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						cell.setCellStyle(cellStylehead);
						int cols = 0;
						if (map.containsKey(headerTemp)) {
							continue;
						}
						for (int d = 0; d <= k; d++) {
							if (d != k) {
								sk += s[d] + "_";
							} else {
								sk += s[d];
							}
						}
						if (map.containsKey(sk)) {
							continue;
						}
						for (int j = 0; j < header.length; j++) {
							if (header[j].indexOf(sk) != -1) {
								cols++;
							}
						}
						cell.setCellValue(s[k]);
						sheet.addMergedRegion(new Region(k + 1, (short) num,
								k + 1, (short) (num + cols - 1)));
						if (sk.equals(headerTemp)) {
							sheet.addMergedRegion(new Region(k + 1,
									(short) num, k + 1 + rows_max - s.length,
									(short) num));
						}
					}
					if (s.length > k) {
						if (!map.containsKey(sk)) {
							String key = "";
							if (k > 0) {
								key = sk;
							} else {
								key = s[k];
							}
							map.put(key, null);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rows_max + 1;
	}

	// 计算需要的工作表数
	public static int computeSheetNum(List fieldData, int splitCount) {
		int rows = (fieldData == null ? 0 : fieldData.size());
		int sheetNum = 0;

		if (rows % splitCount == 0) {
			sheetNum = rows / splitCount;
		} else {
			sheetNum = rows / splitCount + 1;
		}
		return sheetNum;
	}

	// 写入数据
		public static void generateData(HSSFSheet sheet, int sheetNum, Map<String, HSSFCellStyle> cellStyleMap,
				int currRowNum, List fieldData, int splitCount) {
			
			HSSFCellStyle cellStyle = null;
			if (cellStyleMap != null && cellStyleMap.size() > 0) {
				cellStyle = (HSSFCellStyle) cellStyleMap.get("cellStyle");
			}
			int rows = fieldData.size();

			for (int k = 0; k < (rows < splitCount ? rows : splitCount); k++) {
				if (((sheetNum - 1) * splitCount + k) >= rows)
					break;
				HSSFRow row = sheet.createRow((short) (k + currRowNum));
				// 将数据内容放入excel单元格
				ArrayList rowList = (ArrayList) fieldData.get((sheetNum - 1)
						* splitCount + k);
				for (int n = 0; n < rowList.size()+1; n++) {
					HSSFCell cell = row.createCell((short) n);
					cell.setCellStyle(cellStyle);
					if (n == 0) {
						cell.setCellValue(Integer.parseInt((sheetNum - 1) * splitCount + k + 1 + ""));
					} else {
						String numStr = CommonExcelUtil.convertNum(rowList.get(n-1)+"");
						if (StringUtils.isNotBlank(rowList.get(n-1)+"") && !(rowList.get(n-1)+"").trim().equals("null")) {
							if(CommonExcelUtil.isDouble(numStr)) {
								cell.setCellValue(Double.parseDouble(numStr));
							} else if(CommonExcelUtil.isInteger(numStr)) {
								cell.setCellValue(Integer.parseInt(numStr));
							} else {
								cell.setCellValue(numStr);
							}
						} else {
							cell.setCellValue("");
						}
					}

				}
			}
		}

	// 生成excel文件
	public static OutputStream generateExcelFile(HSSFWorkbook wb) {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			wb.write(outStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return outStream;
	}

	/**
	 * //总调
	 * 
	 * @param fieldData
	 *            报表数据来源
	 * @param splitCount
	 *            工作簿记录条数限制
	 * @param header
	 *            表头
	 * @param tableName
	 *            报表名
	 * @param filePath
	 *            excel文件保存的目的路径
	 */
	public static InputStream over(List fieldData, int splitCount,
			String[] header, String reportName, Map xlsInfoMap) {
		HSSFWorkbook wb = createWorkBook();
		Map cellStyleMap = new HashMap();
		cellStyleMap = createCellStyle(wb);
		int sheetNum = computeSheetNum(fieldData, splitCount);
		for (int i = 1; i <= sheetNum; i++) {
			HSSFSheet sheet = createSheet(wb, "page "
					+ (wb.getNumberOfSheets() + 1));
			int n = generateMultiHeader2(sheet, cellStyleMap, header, xlsInfoMap, reportName);
			generateData(sheet, i, cellStyleMap, n, fieldData, splitCount);
		}
		
		OutputStream outStream = generateExcelFile(wb);
		byte[] ba = ((ByteArrayOutputStream) outStream).toByteArray();
		try {
			outStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
		
		// 取得输入流
		ByteArrayInputStream inputStream = new ByteArrayInputStream(ba);
		return inputStream;
	}
	
	public static void writeExcel(InputStream inputStream, String path){
		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(path));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 生成多表头
	 * @param sheet
	 * @param header
	 */
	public static int generateMultiHeader(HSSFSheet sheet, Map<String, HSSFCellStyle> cellStyleMap, String[] header, Map xlsInfoMap, String reportName) {
		//单元格样式
		HSSFCellStyle cellStylename = null;
		HSSFCellStyle cellStyleinfo = null;
		HSSFCellStyle cellStylehead = null;
		if (cellStyleMap != null && cellStyleMap.size() > 0) {
			cellStylename = (HSSFCellStyle) cellStyleMap.get("cellStylename");
			cellStyleinfo = (HSSFCellStyle) cellStyleMap.get("cellStyleinfo");
			cellStylehead = (HSSFCellStyle) cellStyleMap.get("cellStylehead");
		}
		//获取表头层数
		int layer = 0;
		for(String items : header) {
			String[] item = items.split("_");
			layer = item.length>layer?item.length:layer;
		}
		//表头长度
		int headLen = 0;
		if(header != null) {
			headLen = header.length;
		}
		//合并前创建所有需要的单元格(旨在设置边框)
		//需要额外增加一行,用于显示附加信息,如报表导出人、导出时间等。
		//需要额外增加一列,显示序号。
		for(int n=0; n<layer; n++) {
			HSSFRow row = sheet.createRow(n);
			for(int i=0; i<header.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(cellStylehead);
			}
		}
		//生成单元格
		for(int n=0; n<layer+1; n++) {
			
			//获取行
			HSSFRow row = null;
			row = sheet.getRow(n);
			
			//合并单元格列起始下标
			int sIdx = 0;
			//合并单元格列结束下标
			int eIdx = 0;
			String[] sArr = null;
			for(int i=0; i<headLen; i++) {
				//如果元素长度小于当前层数,意味着该元素已处理完毕,无需继续处理.
				String[] items = header[i].split("_");
				if(items.length<=n) {
					continue;
				}
				//合并过的单元格则跳过,但第一次必须进入循环体
				//sIdx=eIdx意味着没有需要合并的单元格
				if(i>1 && eIdx>sIdx && i>=sIdx && i<=eIdx) {
					continue;
				}
				//获得单元格
				HSSFCell cell = row.getCell(i);
				cell.setCellValue(items[n]);
//				cell.setCellStyle(cellStylehead);
				sIdx = i;
				eIdx = i;
				sArr = header[i].split("_");
				if(i == 0 || sArr.length == n+1) {
					//当前元素已是边缘元素
					//纵向合并
					sheet.addMergedRegion(new CellRangeAddress(n, layer-1, i, i));
					continue;
				} 
				for(int j=i+1; j<headLen; j++) {
					//如果i为最后一个元素下标,则结束循环
					String[] eArr = header[j].split("_");
					//如果元素值相同,意味着可以合并单元格
					if(sArr[n]!=null && eArr[n]!=null && sArr[n].trim().equals(eArr[n].trim())) {
						eIdx = j;
				    //一旦出现不相同的值,则跳出循环
					} else {
						break;
					}
				}
				//横向合并,相同父元素单元格
				sheet.addMergedRegion(new CellRangeAddress(n, n, sIdx, eIdx));
					
			}
		}
		return layer;
	}
	
	/**
	 * 生成多表头
	 * @param sheet
	 * @param header
	 */
	public static int generateMultiHeader2(HSSFSheet sheet, Map<String, HSSFCellStyle> cellStyleMap, String[] header, Map xlsInfoMap, String reportName) {
		//单元格样式
		HSSFCellStyle cellStylename = null;
		HSSFCellStyle cellStyleinfo = null;
		HSSFCellStyle cellStylehead = null;
		if (cellStyleMap != null && cellStyleMap.size() > 0) {
			cellStylename = (HSSFCellStyle) cellStyleMap.get("cellStylename");
			cellStyleinfo = (HSSFCellStyle) cellStyleMap.get("cellStyleinfo");
			cellStylehead = (HSSFCellStyle) cellStyleMap.get("cellStylehead");
		}
		//获取表头层数
		int layer = 0;
		for(String items : header) {
			String[] item = items.split("_");
			layer = item.length>layer?item.length:layer;
		}
		//表头长度
		int headLen = 0;
		if(header != null) {
			headLen = header.length;
		}
		//合并前创建所有需要的单元格(旨在设置边框)
		for(int n=1; n<layer+1; n++) {
			HSSFRow row = sheet.createRow(n);
			for(int i=0; i<header.length+1; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(cellStylehead);
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			}
		}
		//第1行设置附加信息,表名,查询条件
		HSSFRow rowx = sheet.createRow(0);
		//第1行第1列设置表名
		HSSFCell xcell0 = rowx.createCell(0);
		xcell0.setCellStyle(cellStylename);
		xcell0.setCellValue("【报表名称】: "+reportName);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
		//第1行第2列设置附加信息
		HSSFCell xcell1 = rowx.createCell(2);
		xcell1.setCellStyle(cellStyleinfo);
		//设置查询条件
		
		//设置导出人和导出时间
		
		//获取excel导出人及导出时间等信息
		String exporter = "";
		String export_date = "";
		//有序控件KEY列表
		List list = null;
		Map condMap = new HashMap();
		List<Map> queryList = new ArrayList<Map>();
		if(xlsInfoMap != null) {
			exporter = (String) xlsInfoMap.get("EXPORTER");
			export_date = (String) xlsInfoMap.get("EXPORT_DATE");
			condMap = (Map) xlsInfoMap.get("qryCondition");
			list = (List) xlsInfoMap.get("SEQLIST");
			queryList = (List)xlsInfoMap.get("qryConditionList");
		}
		//首行第2列显示字符
		StringBuilder xCellValue = new StringBuilder();
		if(list!=null && list.size()>0) {
			xCellValue.append("【查询条件】:");
			/*if(condMap.size()>0) {
				Iterator it = condMap.keySet().iterator();
				String key = "";
				String cnName = "";
				String value = "";
				while(it.hasNext()) {
					key = it.next()+"";
					Map valueMap = (Map) condMap.get(key);
					cnName = valueMap.get("cnName")+"";
					value = valueMap.get("value")+"";
					xCellValue.append(" "+cnName+": "+value+" ");
				}
			}*/
			for(int i=0; list != null && i<list.size(); i++) {
				String key = list.get(i)+"";
				Map map = (Map) condMap.get(key);
				String cnname = map.get("CNNAME")+"";
				String value = map.get("VALUE")+"";
				xCellValue.append("  "+cnname+": "+value+"  ");
			}
		}
		
		String queryListStr = "";
		if(null != queryList && queryList.size() > 0){
			xCellValue.append("【查询条件】:    ");
			for(int i=0; i<queryList.size(); i++){
				Map tmpMap = queryList.get(i);
				String cnName = (String)tmpMap.get("cnName");
				String value = (String)tmpMap.get("value");
				queryListStr += " "+ cnName + " : " + value + "    ";
			}
		}
		
		xCellValue.append(queryListStr + "      |      【统计人】: "+exporter+" 【统计时间】: "+export_date);
		xcell1.setCellValue(xCellValue.toString());
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, headLen<2?2:headLen));
		//第一列设置序号
		HSSFRow rowy = sheet.getRow(1);
		HSSFCell ycell0 = rowy.createCell(0);
		ycell0.setCellValue("序号");
		ycell0.setCellStyle(cellStylehead);
		sheet.addMergedRegion(new CellRangeAddress(1, layer, 0, 0));
		
		//生成单元格
		for(int n=1; n<layer+1; n++) {
			
			//获取行
			HSSFRow row = null;
			row = sheet.getRow(n);
			
			//合并单元格列起始下标
			int sIdx = 1;
			//合并单元格列结束下标
			int eIdx = 1;
			String[] sArr = null;
			for(int i=1; i<headLen+1; i++) {
				//如果元素长度小于当前层数,意味着该元素已处理完毕,无需继续处理.
				String[] items = header[i-1].split("_");
				if(items.length<=n-1) {
					continue;
				}
				//合并过的单元格则跳过,但第一次必须进入循环体
				//sIdx=eIdx意味着没有需要合并的单元格
				if(i>1 && eIdx>sIdx && i>=sIdx && i<=eIdx) {
					continue;
				}
				//获得单元格
				HSSFCell cell = row.getCell(i);
				cell.setCellValue(items[n-1]);
//				cell.setCellStyle(cellStylehead);
				sIdx = i;
				eIdx = i;
				sArr = header[i-1].split("_");
				if(i == 0 || sArr.length == n) {
					//当前元素已是边缘元素
					//纵向合并
					sheet.addMergedRegion(new CellRangeAddress(n, layer, i, i));
					continue;
				} 
				for(int j=i; j<headLen; j++) {
					//如果i为最后一个元素下标,则结束循环
					String[] eArr = header[j].split("_");
					//防止数组越界
					if(eArr.length<n)
						continue;
					//如果元素值相同,意味着可以合并单元格
					if(sArr[n-1]!=null && eArr[n-1]!=null && sArr[n-1].trim().equals(eArr[n-1].trim())) {
						eIdx = j+1;
				    //一旦出现不相同的值,则跳出循环
					} else {
						break;
					}
				}
				//横向合并,相同父元素单元格
				sheet.addMergedRegion(new CellRangeAddress(n, n, sIdx, eIdx));
					
			}
		}
		
		return layer+1;
	}
}