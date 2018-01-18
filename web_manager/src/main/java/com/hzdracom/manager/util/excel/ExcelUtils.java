/**
 * 
 */
package com.hzdracom.manager.util.excel;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.biff.DisplayFormat;
import jxl.format.Alignment;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
/*import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/*import com.google.common.collect.Table.Cell;*/
import com.hzdracom.manager.util.ClassCache;
import com.hzdracom.manager.util.ReportUtil;


/**
 * @author panke
 * @date  2016年11月1日
 */
public class ExcelUtils {

	
	public  static  int  MAX_SZIE = 50000;

	private static  Logger  log  = LoggerFactory.getLogger(ExcelUtils.class);
	
	private static String DEFAULT_DATE_FROMATTER = "DEFAULT_DATE_FROMATTER";;
	
	private static String CENTER_NOBOLD_FONT12 = "CENTER_NOBOLD_FONT12";
	
	private static String CENTER_BOLD_FONT12 = "CENTER_BOLD_FONT12";
	
	private static String LEFT_NOBOLD_FONT12 = "LEFT_NOBOLD_FONT12"; 
	
	private static String CENTER_NOBOLD_PERCENT_FONT12 = "CENTER_NOBOLD_PERCENT_FONT12"; 

	private static java.util.concurrent.ConcurrentMap<String, WritableCellFormat>  data = new ConcurrentHashMap<String, WritableCellFormat>();
	
	
	public static  WritableCellFormat  getCenterNoBoldFont12(String tag) throws WriteException{
		
		if(data.get( tag+ CENTER_NOBOLD_FONT12) == null) {
			// 12号宋体
			WritableFont noBoldFont12 = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false);
			// 12号宋体,居中
			WritableCellFormat	font12 = new WritableCellFormat(noBoldFont12);
				font12.setWrap(false);// 自动换行
				font12.setAlignment(jxl.format.Alignment.CENTRE);
				font12.setVerticalAlignment(VerticalAlignment.CENTRE);
				font12.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
				data.put(tag+CENTER_NOBOLD_FONT12,font12);
				return font12;
		}
		return data.get(tag+CENTER_NOBOLD_FONT12);
		
	}
	
	public static  WritableCellFormat  getCenterBoldFont12(String tag) throws WriteException{
		// 12号宋体 // 12号宋体,居中,粗体
		if(data.get( tag+ CENTER_BOLD_FONT12) == null) {
			WritableFont boldFont12 = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false);
			WritableCellFormat	font12 = new WritableCellFormat(boldFont12);
			font12.setWrap(false);// 自动换行
			font12.setAlignment(jxl.format.Alignment.CENTRE);
			font12.setVerticalAlignment(VerticalAlignment.CENTRE);
			font12.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			data.put(tag+CENTER_BOLD_FONT12,font12);	
			return font12;
		}
		return data.get(tag+CENTER_BOLD_FONT12);
	}
	
	public static  WritableCellFormat  getLeftNoBoldFont12(String tag) throws WriteException{
		// 12号宋体 // 12号宋体,居中,粗体
		if(data.get( tag+ LEFT_NOBOLD_FONT12) == null) {	
			WritableFont boldFont12 = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false);
			WritableCellFormat	font12 = new WritableCellFormat(boldFont12);
			font12.setWrap(false);// 自动换行
			font12.setVerticalAlignment(VerticalAlignment.CENTRE);
			font12.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			font12.setAlignment(jxl.format.Alignment.LEFT);
			data.put(tag+LEFT_NOBOLD_FONT12,font12);
			return font12;
		}
		return data.get(tag+LEFT_NOBOLD_FONT12);
	}
	
	
	public static  WritableCellFormat  getCenterNoBoldPercentFont12(String tag) throws WriteException{
		//数字显示格式为浮点数百分比  
		if(data.get( tag+ CENTER_NOBOLD_PERCENT_FONT12) == null) {	
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);  
		    DisplayFormat displayFormat = NumberFormats.PERCENT_FLOAT;           
		    WritableCellFormat	font12  = new WritableCellFormat(wf,displayFormat);  
		    font12.setAlignment(Alignment.CENTRE);  
		    font12.setVerticalAlignment(VerticalAlignment.CENTRE);  
		    font12.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);  
		    data.put(tag+CENTER_NOBOLD_PERCENT_FONT12,font12);	
		    return font12;
		}
		return data.get(tag+CENTER_NOBOLD_PERCENT_FONT12);
	}
	
	public static  WritableCellFormat getCellDateFormat(String tag){
		if(data.get( tag+ DEFAULT_DATE_FROMATTER) == null) {	
			DateFormat df = new jxl.write.DateFormat("yyyy-MM-dd hh:mm:ss");
			 WritableCellFormat	font12  = new WritableCellFormat(df);
			 data.put(tag+DEFAULT_DATE_FROMATTER,font12);
			 return font12;
		}
		return data.get(tag+DEFAULT_DATE_FROMATTER);
	}
	
	
	static{
		try{
		/*	// 12号宋体
			WritableFont noBoldFont12 = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.NO_BOLD, false);
			// 12号宋体,居中
			centerNoBoldFont12 = new WritableCellFormat(noBoldFont12);
			centerNoBoldFont12.setWrap(false);// 自动换行
			centerNoBoldFont12.setAlignment(jxl.format.Alignment.CENTRE);
			centerNoBoldFont12.setVerticalAlignment(VerticalAlignment.CENTRE);
			centerNoBoldFont12.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			
			// 12号宋体 // 12号宋体,居中,粗体
			WritableFont boldFont12 = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false);
			
			centerBoldFont12 = new WritableCellFormat(boldFont12);
			centerBoldFont12.setWrap(false);// 自动换行
			centerBoldFont12.setAlignment(jxl.format.Alignment.CENTRE);
			centerBoldFont12.setVerticalAlignment(VerticalAlignment.CENTRE);
			centerBoldFont12.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);
			*/
		/*	
			leftNoBoldFont12 = new WritableCellFormat(centerNoBoldFont12);
			leftNoBoldFont12.setAlignment(jxl.format.Alignment.LEFT);
			leftNoBoldFont12.setWrap(false);*/
/*
			//数字显示格式为浮点数百分比  
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD, false);  
            DisplayFormat displayFormat = NumberFormats.PERCENT_FLOAT;           
            centerNoBoldPercentFont12 = new WritableCellFormat(wf,displayFormat);  
            centerNoBoldPercentFont12.setAlignment(Alignment.CENTRE);  
            centerNoBoldPercentFont12.setVerticalAlignment(VerticalAlignment.CENTRE);  
            centerNoBoldPercentFont12.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN);        */
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/**
	 * @param param   
	 * 
	 * 
	 */
	public static void createExcel(HttpServletRequest request,HttpServletResponse response,String fileName,String fieldName[],
			String tableName[],List< ? extends Object> list, String param,Class<?>[] clazz) {
		OutputStream out = null;
		
		
		log.info(" 开始生成Excel文件 {}  共 {} 条数据",fileName,list.size());
		long startTime = System.currentTimeMillis();
		try {
			out = response.getOutputStream();
			response.reset();
			ReportUtil.getCharCode(response, request, fileName);//解决文件名中文乱码
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-disposition", "attachment;filename=" +  new String(fileName.getBytes(), "ISO8859-1") + ".xls");
			WritableWorkbook wwb = Workbook.createWorkbook(out);
			createExcel(wwb, fileName.replaceAll("[\\(（].*[\\)）]", ""), fieldName, tableName,  list,param,clazz);
			wwb.write();
			wwb.close();
			out.flush();
			out.close();
			data.clear();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (null != out) {
				try{
					out.close();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		

		log.info(" 开始生成Excel文件完成 {}  共 {} 毫秒", fileName,(System.currentTimeMillis() - startTime) );
	}

	
	
	
	
	private static void createExcel(WritableWorkbook wwb,String fileName,String fieldName[], String tableName[],List< ? extends Object> list, String param,Class<?>[] clazz) throws Exception{
		int sheetNum = 1;
		
		if (list.size() % MAX_SZIE == 0) {
			sheetNum = list.size() / MAX_SZIE;
		} else {
			sheetNum = list.size() / MAX_SZIE + 1;
		}
		
		if (sheetNum == 0) {
			sheetNum = 1;
		}
		
		for (int m = 0; m < sheetNum; m++){

			int rowIndex = 0;
			log.info(" 生成  Excel文件工作表     {}", (fileName + m));
			WritableSheet sheet = wwb.createSheet(fileName + m, m);
			String tag  = sheet.toString();
			// 写参数
			if(StringUtils.hasText(param)) {
				String[] array = param.split("\r\n");
				for (int i = 0; i < array.length; i++) {
					sheet.mergeCells(0, rowIndex, tableName.length - 1, rowIndex);
					sheet.addCell(new Label(0, rowIndex, array[i],  getLeftNoBoldFont12(tag) ));
					rowIndex++;
				}
			}
			
			// 创建标题
			for (int j = 0; j < tableName.length; j++) {
				sheet.setColumnView(j, 20); //title 标题
			}
			
			for (int i = 0; i < tableName.length; i++) {
				sheet.addCell(new Label(i, rowIndex, tableName[i], getCenterBoldFont12(tag)));
			}
			rowIndex++;
			
			
			for (int k = m * MAX_SZIE; k < (m + 1) * MAX_SZIE; k++) {
				if(list.size() <= k ) {
					break;
				}
				Object an = list.get(k);
				for (int i = 0; i < fieldName.length; i++) {
					Object value = "";
					if(an instanceof Map){
						value = ((Map)an).get(fieldName[i]);
					} else {
						value = ClassCache.getValue(an, fieldName[i]);
					}
					setCellValue(sheet, i, rowIndex, value, clazz[i]);
				}
				rowIndex++;
			}
		}
	}

	public static void createExcel(File file,Integer startRow,String fieldName[],List< ? extends Object> list,Class<?>[] clazz) {
		OutputStream out = null;
		
		
		log.info(" 开始生成Excel文件 {}  共 {} 条数据",file.getAbsoluteFile(),list.size());
		long startTime = System.currentTimeMillis();
		try {
			
			WritableWorkbook wwb = Workbook.createWorkbook(file,Workbook.getWorkbook(file));
			createExcel(wwb,file.getName(),startRow, fieldName, list, clazz);
			wwb.write();
			wwb.close();
			data.clear();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			if (null != out) {
				try{
					out.close();
				}
				catch (IOException e){
					e.printStackTrace();
				}
			}
		}
		log.info(" 开始生成Excel文件完成 {}  共 {} 毫秒", file.getAbsoluteFile(),(System.currentTimeMillis() - startTime) );
	}
	
	
	private static void createExcel(WritableWorkbook wwb,String fileName,Integer startRow,String fieldName[],List< ? extends Object> list,Class<?>[] clazz) throws Exception{
	
		

			int rowIndex = startRow;
			log.info(" 生成  Excel文件工作表     {}", (fileName));
			WritableSheet sheet = wwb.getSheet(0);
			for (int k = 0; k < list.size() ; k++) {
				Object an = list.get(k);
				for (int i = 0; i < fieldName.length; i++) {
					Object value = "";
					if(an instanceof Map){
						value = ((Map)an).get(fieldName[i]);
					} else {
						value = ClassCache.getValue(an, fieldName[i]);
					}
					setCellValue(sheet, i, rowIndex, value, clazz[i]);
				}
				rowIndex++;
			}
	}
	
	
	
	public static void setCellValue(WritableSheet sheet,int colunm,int row,Object value,Class<?> clazz) throws RowsExceededException, WriteException{
		
		String tag = sheet.toString();
		try{
		//	log.info("EXCEL生成数据   --> {} {} {} {} ",colunm,row,value,clazz);
			
			if(clazz !=null ) {
				if(clazz == String.class) {
					if(value == null) value = "";
					 Label label2 = new  Label( colunm, row, (value +"").toString(), getCenterNoBoldFont12(tag));
			         sheet.addCell(label2);
			         return;
				} else if(clazz == Integer.class) {
					if(value == null) value = 0;
					jxl.write.Number label2 = new jxl.write.Number(colunm, row, Integer.parseInt(value+""),getCenterNoBoldFont12(tag));
		             sheet.addCell(label2);
		             return;
				} else if(clazz == Float.class) {
					if(value == null) value = 0;
		            jxl.write.Number label2 = new jxl.write.Number(colunm, row, Double.parseDouble(value+""),getCenterNoBoldPercentFont12(tag));
					sheet.addCell(label2);
		            return;
				}  else if(clazz == Double.class) {
					if(value == null) value = 0;
					jxl.write.Number label2 = new jxl.write.Number(colunm, row, Double.parseDouble(value+""),getCenterNoBoldFont12(tag));
		             sheet.addCell(label2);
		            return;
				}  else if(clazz == Date.class) {
					if(null == value) {
						 Label label2 = new  Label( colunm, row, "", getCenterNoBoldFont12(tag));
				         sheet.addCell(label2);
				         return;
					}
					DateTime label2 = new jxl.write.DateTime(colunm, row,  (Date)value, getCellDateFormat(tag));
		             sheet.addCell(label2);
		            return;
				}
			} else {
				 Label label2 = new  Label( colunm, row, (value +"").toString(), getCenterNoBoldFont12(tag));
		         sheet.addCell(label2);
		         return;
			}
		}  catch (Exception e){
			e.printStackTrace();
		}
		 Label label2 = new  Label( colunm, row, (value +"").toString(), getCenterNoBoldFont12(tag));
         sheet.addCell(label2);
	}
	
	
	

	public static String  getValue(org.apache.poi.ss.usermodel.Cell cell){
		
		if (cell ==null) {
			return null;
		}
		
		int type = cell.getCellType();
		
		if(type == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		}
		if(type == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC) {
			return new Double( cell.getNumericCellValue()).longValue()+"";
			

		}
		
		if(type == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK) {
			return null;
		}
		
		if(type == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue() +"";
		}
		
		return "";
	}
	
	
	   /**    
     * 获取合并单元格的值    
     * @param sheet    
     * @param row    
     * @param column    
     * @return    
     */     
     public static String getMergedRegionValue(Sheet sheet ,int row , int column){     
         int sheetMergeCount = sheet.getNumMergedRegions();     
              
         for(int i = 0 ; i < sheetMergeCount ; i++){     
             CellRangeAddress ca = sheet.getMergedRegion(i);     
             int firstColumn = ca.getFirstColumn();     
             int lastColumn = ca.getLastColumn();     
             int firstRow = ca.getFirstRow();     
             int lastRow = ca.getLastRow();     
             if(row >= firstRow && row <= lastRow){     
                  if(column >= firstColumn && column <= lastColumn){     
                     Row fRow = sheet.getRow(firstRow); 
                     Cell fCell = fRow.getCell(firstColumn); 
                     return getCellValue(fCell) ; 
                 }     
             }     
         }     
              
         return null ;     
     }   
     
     
     
     /**   
      * 判断指定的单元格是否是合并单元格   
      * @param sheet    
      * @param row 行下标   
      * @param column 列下标   
      * @return   
      */   
      public static boolean isMergedRegion(Sheet sheet,int row ,int column) {   
        int sheetMergeCount = sheet.getNumMergedRegions();   
        for (int i = 0; i < sheetMergeCount; i++) {   
              CellRangeAddress range = sheet.getMergedRegion(i);   
              int firstColumn = range.getFirstColumn(); 
              int lastColumn = range.getLastColumn();   
              int firstRow = range.getFirstRow();   
              int lastRow = range.getLastRow();   
              if(row >= firstRow && row <= lastRow){ 
                      if(column >= firstColumn && column <= lastColumn){ 
                              return true;   
                          } 
              }   
        } 
        return false;   
      } 
      
      
      
      /**    
      * 获取单元格的值    
      * @param cell    
      * @return    
      */     
      public static String getCellValue(Cell cell){     
               
          if(cell == null) return " ";     
               
        //  System.out.println(cell.getCellType());
          if(cell.getCellType() == Cell.CELL_TYPE_STRING){     
                   
              return cell.getStringCellValue();     
                   
          }else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){     
                   
              return String.valueOf(cell.getBooleanCellValue());     
                   
          }else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){     
        	  try {
        		 return  String.valueOf(cell.getNumericCellValue());
        	  } catch (IllegalStateException e) {
        		 return  String.valueOf(cell.getRichStringCellValue());
        	 }           
          }else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){   
        	  java.text.NumberFormat nf = java.text.NumberFormat.getInstance();   
        	  nf.setGroupingUsed(false);  
        	  return nf.format(cell.getNumericCellValue());
        	 // System.out.println(cell.getNumericCellValue());
             // return new Double(cell.getNumericCellValue()).toString();     
                   
          } 
          
          return "";     
      } 
     
   
	/*public static String  getValue(jxl.Cell cell){
		
		CellType type = cell.getType();
		if(type == CellType.LABEL) {
			return cell.getContents();
		}
		if(type == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC) {
			return cell.getNumericCellValue()+"";
		}
		
		if(type == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK) {
			return null;
		}
		
		if(type == org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue() +"";
		}
		
		return "";
	}*/
	
	public static void main(String[] args) throws BiffException, IOException, RowsExceededException, WriteException {
	//	System.out.println("我是测试（testaass)".replaceAll("[\\(（].*[\\)）]", ""));
		
		
		URL url = ClassLoader.getSystemResource("model/CustomerTemplate.xls");;
		
		File file = File.createTempFile("hzdracom", ".xls");
		System.out.println(url.getPath());
		System.out.println(file.getPath());
		FileUtils.copyFile(new File(url.getPath()),file);
		String[] fieldName = new String[]{"name","password"};
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", "value");
		map.put("password", "123456");
		list.add(map);
		Class<?>[] clazz = new Class[]{String.class,String.class};
		createExcel(file, 2,fieldName, list, clazz );
		
	}
}
