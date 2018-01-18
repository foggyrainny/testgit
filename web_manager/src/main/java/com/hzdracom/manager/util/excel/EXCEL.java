package com.hzdracom.manager.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import jxl.read.biff.BiffException;
//import oracle.jdbc.OracleTypes;



import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.Region;
//import org.apache.struts2.ServletActionContext;



import com.hzdracom.manager.util.DateUtil;
import com.hzdracom.manager.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
//import com.zjdxsoft.exception.DataAccessException;

@SuppressWarnings( { "serial", "deprecation", "unchecked", "static-access", })
public class EXCEL extends ActionSupport {

	/**
	 * 读EXCEL文件
	 * 
	 * @param path
	 * @return
	 */
	public HashMap readExcel(File path) {
		HashMap map = null;
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(path));
			// 表单
			for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
				if (null != workbook.getSheetAt(numSheets)) {
					HSSFSheet aSheet = workbook.getSheetAt(numSheets);// 获得一个sheet
					// 行
					map = new HashMap();
					for (int rowNumOfSheet = 1; rowNumOfSheet <= aSheet
							.getLastRowNum(); rowNumOfSheet++) {
						if (null != aSheet.getRow(rowNumOfSheet)) {
							HSSFRow aRow = aSheet.getRow(rowNumOfSheet);
							LinkedList al = new LinkedList();
							// 列
							for (short cellNumOfRow = 0; cellNumOfRow <= aRow
									.getLastCellNum(); cellNumOfRow++) {
								String strCell = "";
								if (null != aRow.getCell(cellNumOfRow)) {
									HSSFCell aCell = aRow.getCell(cellNumOfRow);
									if (aCell.getCellType() == (aCell.CELL_TYPE_NUMERIC)) {
										strCell = String.valueOf(aCell
												.getNumericCellValue());
										int index = strCell.indexOf(".");
										if (index != -1) {
											strCell = strCell.substring(0,
													index);
										}
										if (HSSFDateUtil
												.isCellDateFormatted(aCell)) {
											strCell = DateUtil.formatDateTime(
													aCell.getDateCellValue(),
													"yyyy-MM-dd HH:mm:ss");
										}
									} else {
										strCell = aCell.getStringCellValue();
									}
									al.add(cellNumOfRow, strCell);
								}
							}
							map.put(new Integer(rowNumOfSheet), al);// 添加一行数据
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return map;
	}

	public static void setSheetColumnWidth(HSSFSheet sheet) throws Exception {
		// 根据你数据里面的记录有多少列，就设置多少列
		sheet.setColumnWidth((short) 0, (short) 3000);
		sheet.setColumnWidth((short) 1, (short) 3000);
		sheet.setColumnWidth((short) 2, (short) 3000);
		sheet.setColumnWidth((short) 3, (short) 3000);
		sheet.setColumnWidth((short) 4, (short) 5000);
		sheet.setColumnWidth((short) 5, (short) 5000);
		sheet.setColumnWidth((short) 6, (short) 5000);
		sheet.setColumnWidth((short) 7, (short) 5000);
		sheet.setColumnWidth((short) 8, (short) 5000);
		sheet.setColumnWidth((short) 9, (short) 5000);
	}

	// 设置excel的title样式
	public static HSSFCellStyle createTitleStyle(HSSFWorkbook wb)
			throws Exception {
		HSSFFont boldFont = wb.createFont();
		boldFont.setFontHeight((short) 200);
		HSSFCellStyle style = wb.createCellStyle();
		style.setFont(boldFont);
//		style.setWrapText(true);//自动换行
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
		return style;
	}

	// 创建Excel单元格
	public static void createCell(HSSFRow row, int column, HSSFCellStyle style,
			int cellType, Object value) throws Exception {
		HSSFCell cell = row.createCell((short) column);

		// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		if (style != null) {
			cell.setCellStyle(style);
		}
		switch (cellType) {
		case HSSFCell.CELL_TYPE_BLANK: {
		}
			break;
		case HSSFCell.CELL_TYPE_STRING: {
			cell.setCellValue(value.toString());
		}
			break;
		case HSSFCell.CELL_TYPE_NUMERIC: {
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cell.setCellValue(Double.parseDouble(value.toString()));
		}
			break;
		default:
			break;
		}
	}

	// 加载EXCEL
	/*public void printExcel(HSSFWorkbook workbook, String string)
			throws IOException {
		try {
			HttpServletResponse response2 = ServletActionContext.getResponse();
			ServletActionContext.getResponse();
			OutputStream out = null;
			out = response2.getOutputStream();
			response2.reset();
			string = new String(string.getBytes(), "iso-8859-1");
			response2.setContentType("application/msexcel;charset=GBK");
			response2.setHeader("Content-disposition", "attachment; filename="
					+ string + ".xls");
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception ex) {
		}
	}
*/
	/**
	 * 读取用户导入的EXCLE文件 返回值的列表 默认读取第一个SHEET 的第一列 支持excel 2003 2007导入
	 * 
	 * @param file
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public List readExcelList(File file) throws BiffException, IOException {
		List list = new ArrayList();
		Workbook rwb = null;

		// 创建输入流
		InputStream stream = new FileInputStream(file);

		try {
			rwb = WorkbookFactory.create(stream);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		Sheet sheet = rwb.getSheetAt(0);

		// 行数(表头的目录不需要，从1开始)
		int rowCount = sheet.getLastRowNum();

		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				Cell cell = row.getCell(0);
				String c = cell.toString();

				if (StringUtil.isNotEmpty(c)) {
					// 读取单元格的数据
					DecimalFormat df = new DecimalFormat("##");
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						list.add(String.valueOf(cell.getRichStringCellValue()));
						break;
					case Cell.CELL_TYPE_NUMERIC:
						list.add(df.format(cell.getNumericCellValue()));
						break;
					default:
						break;
					}
				}
			}
		}

		return list;
	}

	/**
	 * 读取用户导入的EXCLE文件 返回值的列表 默认读取第一个SHEET 的前两列 支持excel 2003 2007导入
	 * 
	 * @param file
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public List readExcelList(File file, String args) throws BiffException,
			IOException {
		List list = new ArrayList();
		Workbook rwb = null;

		// 创建输入流
		InputStream stream = new FileInputStream(file);

		try {
			rwb = WorkbookFactory.create(stream);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		Sheet sheet = rwb.getSheetAt(0);

		// 行数(表头的目录不需要，从1开始)
		int rowCount = sheet.getLastRowNum();

		for (int i = 0; i <= rowCount; i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				Cell cell = row.getCell(0);
				Cell cell2 = row.getCell(1);
				String c = cell.toString();
				String c2 = cell2.toString();
				if (StringUtil.isNotEmpty(c) && StringUtil.isNotEmpty(c2)) {
					// 读取单元格的数据
					DecimalFormat df = new DecimalFormat("##");
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						list.add(String.valueOf(cell.getRichStringCellValue()));
						break;
					case Cell.CELL_TYPE_NUMERIC:
						list.add(df.format(cell.getNumericCellValue()));
						break;
					default:
						break;
					}
					switch (cell2.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						list
								.add(String.valueOf(cell2
										.getRichStringCellValue()));
						break;
					case Cell.CELL_TYPE_NUMERIC:
						list.add(df.format(cell2.getNumericCellValue()));
						break;
					default:
						break;
					}
				}
			}
		}

		return list;
	}

	/**
	 * 将读取获得的LIST 的重复项去除
	 * 
	 * @param list
	 * @return
	 */

	public List convertExcelList(List list) {
		List list2 = new ArrayList();
		HashSet hashSet = new HashSet(list);
		list2.addAll(hashSet);
		return list2;
	}

	/**
	 * 
	 * @param list1
	 *            数据集合,该集合每个元素是对应的数组
	 * @param cols
	 *            字段集合
	 * @param cols2
	 *            列头集合
	 * @param title
	 *            标题
	 * @return
	 * @throws DataAccessException
	 * @throws Exception
	 */

	public HSSFWorkbook export(List<Map> list1, List cols, List cols2,
			String title) throws Exception {
		HSSFWorkbook workbook = null;

		try {
			if (list1 != null && list1.size() > 0) {
				// 创建工作簿实例
				workbook = new HSSFWorkbook();
				int maxRow = 30000;// 每个sheet的最大行数
				// 确定要创建的sheet个数
				int gt1 = 1;
				if (list1.size() > maxRow) {
					int gt2 = list1.size() / maxRow;// 取整
					int gt3 = list1.size() % maxRow;// 取模
					if (gt3 > 0)
						gt1 = gt2 + 1;
				}
				for (int t = 1; t <= gt1; t++) {
					// 创建工作表实例
					HSSFSheet sheet = workbook.createSheet("Sheet" + t);

					// 设置列宽
					EXCEL.setSheetColumnWidth(sheet);
					// 获取样式
					HSSFCellStyle style = EXCEL.createTitleStyle(workbook);
					// 创建第一行标题,标题名字的本地信息通过resources从资源文件中获取
					// HSSFRow roww = sheet.createRow((short) 0);// 建立新行
					// EXCEL.createCell(roww, 0, style,
					// HSSFCell.CELL_TYPE_STRING,title);
					//				
					HSSFRow row = sheet.createRow((short) 0);// 建立新行

					for (int i = 0; i < cols2.size(); i++) { // 组装列头
						EXCEL.createCell(row, i, style,
								HSSFCell.CELL_TYPE_STRING, cols2.get(i)
										.toString());
					}
					// 为新的sheet赋予开始值和结束值
					int start = 0;
					int end = 0;
					start = t * maxRow - maxRow;
					if (t != gt1)
						end = t * maxRow;
					if (t == gt1)
						end = list1.size();
					// 给excel填充数据
					for (int j = start; j < end; j++) {
						Map map = list1.get(j);// 接着上个sheet进行遍历
						HSSFRow row1 = sheet.createRow((short) (j + 1 - start));// 新的sheet从第二行开始建立新行
						for (int k = 0; k < cols.size(); k++) {// 遍历字段集合
							if (map.get(cols.get(k)) != null)
								EXCEL.createCell(row1, k, style,
										HSSFCell.CELL_TYPE_STRING, map.get(
												cols.get(k)).toString());
							else
								EXCEL.createCell(row1, k, style,
										HSSFCell.CELL_TYPE_STRING, "");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}

	/**
	 * @author LTY
	 * @param file
	 * @return Create Date 2011年1月20日21:08:35
	 */
	public List<String> getDataWithExcel(File file, int columnNum)
			throws Exception {
		List<String> string = new ArrayList<String>();
		Workbook rwb = null;
		try {
			rwb = WorkbookFactory.create(new FileInputStream(file));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		Sheet sheet = rwb.getSheetAt(0);
		// 获取到Excel文件中的所有行数

		int rows = sheet.getPhysicalNumberOfRows();

		// 遍历行
		for (int i = 1; i < rows; i++) {
			// 读取左上端单元格
			Row row = sheet.getRow(i);
			// 行不为空
			String value = "";
			if (row != null) {
				// 获取到Excel文件中的所有的列
				int trueCell = 0;
				for (Iterator<Cell> iter = row.iterator(); iter.hasNext();) {
					Cell cell1 = iter.next();
					try {
						if (StringUtil.isNotEmpty(cell1.getStringCellValue())) {
							trueCell++;

						}
					} catch (IllegalStateException e) {
						if (StringUtil.isNotEmpty(String.valueOf(cell1
								.getNumericCellValue()))) {
							trueCell++;

						}
					}
				}
				if (trueCell > columnNum && trueCell != 0) {

					throw new NullPointerException();
				}

				int cells = trueCell;
				// 遍历列
				for (int j = 0; j < cells; j++) {
					Cell cell = row.getCell(j);

					if (cell != null) {
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_NUMERIC:
							value += cell.getNumericCellValue() + ",";
							break;
						case HSSFCell.CELL_TYPE_STRING:
							value += cell.getStringCellValue() + ",";
							break;
						default:
							value += "0";
							break;
						}
					}
				}
				for (String s : value.split(",")) {
					if (StringUtil.isNotEmpty(s)) {
						string.add(value);
						break;
					}
				}
			}
		}
		return string;

	}
	
	/**
	 *从Excel文件中读取数据
	 * @param file  excel文件
	 * @param columnNum 字段列数
	 * @return
	 * @throws Exception
	 */
	public List<String> getDataWithKeepNullExcel(File file, int columnNum)
	throws Exception {
		List<String> string = new ArrayList<String>();
		Workbook rwb = null;
		String sep="|||";
		try {
			rwb = WorkbookFactory.create(new FileInputStream(file));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		Sheet sheet = rwb.getSheetAt(0);
		// 获取到Excel文件中的所有行数
		
		int rows = sheet.getPhysicalNumberOfRows();
		
		// 遍历行
		for (int i = 1; i < rows; i++) {
			// 读取左上端单元格
			Row row = sheet.getRow(i);
			// 行不为空
			String value = "";
			if (row != null) {
				// 获取到Excel文件中的所有的列
		/*	for(int k=0;k<row.getLastCellNum();k++){
				 Cell cell =row.getCell(k);
				 if(cell==null){
					 trueCell++;
				 }else{
					 try {
							if (StringUtil.isNotEmpty(cell.getStringCellValue())) {
								trueCell++;

							}
						} catch (IllegalStateException e) {
							if (StringUtil.isNotEmpty(String.valueOf(cell
									.getNumericCellValue()))) {
								trueCell++;

							}
						}
				 }
			}
				if (trueCell > columnNum && trueCell != 0) {
					
					throw new NullPointerException();
				}*/
				
//				int cells = trueCell;
				// 遍历列
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					if(cell==null){
						value +="NULL"+sep;
					}else {
						if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
							value +="NULL"+sep;
						}else if(cell != null) {
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_NUMERIC:
								value += cell.getNumericCellValue() + sep;
								break;
							case HSSFCell.CELL_TYPE_STRING:
								value += cell.getStringCellValue() + sep;
								break;
							default:
								value += "0"+sep;
								break;
							}
						}
					}
				}
				for (String s : value.split("\\|\\|\\|")) {
					if (StringUtil.isNotEmpty(s)) {
						string.add(value);
						break;
					}
				}
			}
		}
		return string;
		
	}
	public List<String> getDataWithKeepNullExcelEnd(File file, int columnNum)
	throws Exception {
		String sep="|||";
		List<String> string = new ArrayList<String>();
		Workbook rwb = null;
		try {
			rwb = WorkbookFactory.create(new FileInputStream(file));
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		Sheet sheet = rwb.getSheetAt(0);
		// 获取到Excel文件中的所有行数
		
		int rows = sheet.getPhysicalNumberOfRows();
		
		// 遍历行
		for (int i = 1; i < rows; i++) {
			// 读取左上端单元格
			Row row = sheet.getRow(i);
			// 行不为空
			String value = "";
			if (row != null) {
				// 获取到Excel文件中的所有的列
				// 遍历列
				for (int j = 0; j < columnNum; j++) {
					Cell cell = row.getCell(j);
					if(cell==null){
						value +="NULL"+sep;
					}else {
						if(cell.getCellType()==Cell.CELL_TYPE_BLANK){
							value +="NULL"+sep;
						}else if(cell != null) {
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_NUMERIC:
								value += cell.getNumericCellValue() + sep;
								break;
							case HSSFCell.CELL_TYPE_STRING:
								value += cell.getStringCellValue() + sep;
								break;
							default:
								value += "0"+sep;
								break;
							}
						}
					}
				}
				for (String s : value.split("\\|\\|\\|")) {
					if (StringUtil.isNotEmpty(s)) {
						string.add(value);
						break;
					}
				}
			}
		}
		return string;
		
	}
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					System.out.println(ex);
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}

	/***
	 * 有序号
	 * 
	 * @param sql
	 *            查询语句
	 * @param cols
	 *            Clos封装Execl标头和对应的数据字段信息 如： phoneCode,业务号码
	 * @param title
	 *            导出时候的标题
	 */
/*	public static void expEXECL(String cols, String fileName, Object obj,
			String month, String callsql) {
		DBEcpConnection ecpConnection;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			int t = 0;
			int k = 0;
			int x = 0;
			int y = 49999;
			HttpServletResponse response = ServletActionContext.getResponse();
			workbook = new HSSFWorkbook();
			String[] colsName;
			// 封装列头
			if (StringUtil.isEmpty(cols)) {
				return;
			} else {
				colsName = cols.split(",");
			}
			while (k == 0) {
				ecpConnection = new DBEcpConnection();
				conn = ecpConnection.getECPConnection();
				cs = conn.prepareCall(callsql);
				cs.registerOutParameter(1, OracleTypes.CURSOR);
				cs.setString(2, month);// 设置输入参数的值
				cs.setString(3, "105");// 设置输入参数的值
				cs.registerOutParameter(4, OracleTypes.NUMBER);
				cs.execute();
				rs = (ResultSet) cs.getObject(1);
				HSSFSheet sheet = workbook.createSheet("用友用户清单报表");
				HSSFCellStyle style = EXCEL.createTitleStyle(workbook);
				HSSFRichTextString hssfString = new HSSFRichTextString(month
						+ "用友用户清单报表");
				sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) 25));
				HSSFRow title = sheet.createRow((short) 0);
				title.setHeight((short) 500);
				HSSFCell titleCell = title.createCell((short) 0);// 创建一个单元格
				HSSFFont fontStyle = workbook.createFont();// 设置字体
				fontStyle.setFontHeight((short) 300);
				fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

				// 设置单元格类型
				HSSFCellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setFont(fontStyle);
				cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平布局：居中
				cellStyle.setWrapText(true); // 自动换行
				titleCell.setCellValue(hssfString);//给单元格设置内容
				titleCell.setCellStyle(cellStyle);
				HSSFRow row = sheet.createRow((short) 1);// 建立新行
				for (int i = 0; i < colsName.length; i++) { // 组装列头
					sheet.setColumnWidth(i, 4500);
					EXCEL.createCell(row, i, style, HSSFCell.CELL_TYPE_STRING,
							colsName[i].toString());
				}
				int b = 2;
				int soNum=1;
				if (b < 50001) {
					k = 1;
				} else {
					k = 0;
				}
				x = x + 50000;
				y = 49999;
				t++;
				if (cs.getFloat(4) != 1) {
					while (rs.next()) {
						HSSFRow row1 = sheet.createRow(b);
						for (int i = 1; i <= colsName.length; i++) {
							
							 * if(i==1){ EXCEL.createCell(row1, i-1, style,
							 * HSSFCell.CELL_TYPE_STRING,b); }else{
							 
							if(i==1){
								EXCEL.createCell(row1, i - 1, style,HSSFCell.CELL_TYPE_NUMERIC,soNum);
							}else if(i==12){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==13){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==14){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==15){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==16){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==17){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==18){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==19){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==20){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==21){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==22){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==23){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==24){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==25){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==26){
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else{
								EXCEL.createCell(row1, i-1, style,HSSFCell.CELL_TYPE_STRING,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}
							// }
						}
						b++;
						soNum++;
					}
				}
				// }
				System.gc();

			}
			// 输出标题
			out = response.getOutputStream();
			response.reset();
			fileName = new String(fileName.getBytes(), "iso-8859-1");
			response.setContentType("application/msexcel;charset=GBK");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			workbook.write(out);

			out.flush();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
*/
	/*public static void expVedioEXECL(String colsPrefix,String cols, String fileName, Object obj,
			String month, String callsql, int flag, String titleName) {
		DBEcpConnection ecpConnection;
		Connection conn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			int t = 0;
			int k = 0;
			int x = 0;
			int y = 49999;
			HttpServletResponse response = ServletActionContext.getResponse();
			workbook = new HSSFWorkbook();
			String[] colsName;
			String[] colsPrefixName;
			// 封装列头
			if (StringUtil.isEmpty(cols)) {
				return;
			} else {
				colsName = cols.split(",");
			}
			if (StringUtil.isEmpty(colsPrefix)) {
				return;
			} else {
				colsPrefixName = colsPrefix.split(",");
			}
			while (k == 0) {
				ecpConnection = new DBEcpConnection();
				conn = ecpConnection.getECPConnection();
				cs = conn.prepareCall(callsql);
				cs.registerOutParameter(1, OracleTypes.CURSOR);
				cs.setString(2, month);// 设置输入参数的值
				cs.setString(3, "1000");// 设置输入参数的值
				if (flag == 1) {
					cs.registerOutParameter(4, OracleTypes.NUMBER);
				}

				cs.execute();
				rs = (ResultSet) cs.getObject(1);
				//创建工作簿
				HSSFSheet sheet = workbook.createSheet(titleName);
				// 设置列宽
				EXCEL.setSheetColumnWidth(sheet);
				// 获取样式
				HSSFCellStyle style = EXCEL.createTitleStyle(workbook);		

				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				for (int i = 0; i < colsName.length; i++) { // 组装列头
					EXCEL.createCell(row, i, style, HSSFCell.CELL_TYPE_STRING,
							colsName[i].toString());
				}
				int b = 1;
				int seqNum=1;
				if (b < 50001) {
					k = 1;
				} else {
					k = 0;
				}
				x = x + 50000;
				y = 49999;
				t++;
				if (flag == 1) {
					if (cs.getFloat(4) != 1) {
						while (rs.next()) {
							HSSFRow row1 = sheet.createRow(b);
							for (int i = 1; i <= colsPrefixName.length; i++) {
								
								EXCEL.createCell(row1, i - 1, style,
										HSSFCell.CELL_TYPE_STRING, rs
												.getString(colsPrefixName[i-1]) != null ? rs
												.getString(colsPrefixName[i-1]).toString() : "");
								// }
							}
							b++;
						}
					}
				} else {
					while (rs.next()) {
						HSSFRow row1 = sheet.createRow(b);
						for (int i = 1; i <= colsPrefixName.length; i++) {
							if(i==1){
								EXCEL.createCell(row1, i - 1, style,HSSFCell.CELL_TYPE_STRING,seqNum);
							}else if(i==10){
								EXCEL.createCell(row1, i-1 , style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==11){
								EXCEL.createCell(row1, i-1 , style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==12){
								EXCEL.createCell(row1, i-1 , style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else if(i==13){
								EXCEL.createCell(row1, i-1 , style,HSSFCell.CELL_TYPE_NUMERIC,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}else{
								EXCEL.createCell(row1, i-1 , style,HSSFCell.CELL_TYPE_STRING,rs.getString(i-1) != null ? rs.getString(i-1).toString() : "");
							}
							// }
						}
						b++;
						seqNum++;
					}
				}
				// }
				System.gc();

			}
			// 输出标题
			out = response.getOutputStream();
			response.reset();
			fileName = new String(fileName.getBytes(), "iso-8859-1");
			response.setContentType("application/msexcel;charset=GBK");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			workbook.write(out);

			out.flush();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
*/

	/***
	 * 无序号
	 * 
	 * @param sql
	 *            查询语句
	 * @param cols
	 *            Clos封装Execl标头和对应的数据字段信息 如： phoneCode,业务号码
	 * @param title
	 *            导出时候的标题
	 */
	/*public static void expEXECL(String sql, String cols, String fileName) {
		DBEcpConnection ecpConnection;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		OutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			int t = 0;
			int k = 0;
			int x = 0;
			int y = 49999;
			HttpServletResponse response = ServletActionContext.getResponse();
			workbook = new HSSFWorkbook();
			String[] colsName;
			// 封装列头
			if (StringUtil.isEmpty(cols)) {
				return;
			} else {
				colsName = cols.split(",");
			}

			while (k == 0) {

				String sql1 = "";
				sql1 = SQLUtil.getPageSQL2(sql, x, y, SQLUtil.DBMS_ORACLE_TYPE);
				System.out.println(sql1);
				ecpConnection = new DBEcpConnection();
				conn = ecpConnection.getECPConnection();
				ps = conn.prepareStatement(sql1);
				rs = ps.executeQuery();
				HSSFSheet sheet = workbook.createSheet("Sheet" + "(" + t + ")");
				HSSFCellStyle style = EXCEL.createTitleStyle(workbook);
				HSSFRow row = sheet.createRow((short) 0);// 建立新行
				for (int i = 0; i < colsName.length; i++) { // 组装列头
					EXCEL.createCell(row, i, style, HSSFCell.CELL_TYPE_STRING,
							colsName[i].toString());
				}
				int b = 1;

				while (rs.next()) {
					HSSFRow row1 = sheet.createRow(b);
					for (int i = 1; i <= colsName.length; i++) {
						EXCEL.createCell(row1, i - 1, style,
								HSSFCell.CELL_TYPE_STRING,
								rs.getString(i) != null ? rs.getString(i)
										.toString() : "");
					}
					b++;
				}
				if (b < 50001) {
					k = 1;
				} else {
					k = 0;
				}

				x = x + 50000;
				y = 49999;
				t++;
				System.gc();

			}
			// 输出标题
			out = response.getOutputStream();
			response.reset();
			fileName = new String(fileName.getBytes(), "iso-8859-1");
			response.setContentType("application/msexcel;charset=GBK");
			response.setHeader("Content-disposition", "attachment; filename="
					+ fileName + ".xls");
			workbook.write(out);

			out.flush();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				if (out != null) {
					out.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	*/
	
	public static void main (String[] args) {
		File file=new File("D:/test.xlsx");
		try {
//			System.out.println(new EXCEL().getDataWithExcel(file, 5));
			List<String> liStr=new EXCEL().getDataWithKeepNullExcel(file, 5);
			for(int i=0;i<liStr.size();i++){
				String[] str=liStr.get(i).split("\\|\\|\\|");
				System.out.println(str);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
