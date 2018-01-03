package raky.train.util;

import java.io.Serializable;

import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;

public class Pager implements Serializable {

	private static final Logger logger=Logger.getLogger(Pager.class);
	
	private static final long serialVersionUID = -4853649627933466931L;

	private int pageSize = 10;//姣忛〉鏄剧ず璁板綍鏁�

	private String requestPage;//璇锋眰椤�

	private int recordCount = -1;//鎬昏褰曟暟

	private int firstRow = 0;//鏁版嵁搴撳垎椤�   limit firstRow, rowCount

	private int rowCount = 10;//姣忛〉鏄剧ず璁板綍鏁�

	
	private int currentPage = 1;//褰撳墠椤�

	private int pageCount = 1;//椤垫暟

	private int firstPage = 1;//绗竴椤�

	private int priviousPage = 1;//涓婁竴椤�

	private int nextPage = 1;//涓嬩竴椤�

	private int lastPage = 1;//鏈�鍚庨〉

	public Pager() {
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public int getFirstPage() {
		return this.firstPage;
	}

	public Integer getFirstRow() {
		return this.firstRow;
	}

	public int getLastPage() {
		return this.lastPage;
	}

	public int getNextPage() {
		return this.nextPage;
	}

	public int getPageCount() {
		return this.pageCount;
	}

	public Integer getPageSize() {

		return this.pageSize;
	}

	public int getPriviousPage() {
		return this.priviousPage;
	}

	public int getRecordCount() {
		return this.recordCount;
	}

	public String getRequestPage() {
		return GenericValidator.isLong(this.requestPage) ? this.requestPage : "1";
	}

	public Integer getRowCount() {
		return this.rowCount;
	}
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public void setRequestPage(String requestPage) {
		this.requestPage = requestPage;
	}

	/**
	 * @param recordCount  鎬昏褰曟暟
	 * @param pageSize   姣忛〉鏄剧ず澶氬皯鏉℃暟鎹�,榛樿鏄�10 
	 * @param requestPage  椤甸潰璇锋眰椤�
	 */
	public void init(int recordCount, int pageSize, String requestPage) {
		logger.info(requestPage);
		if(requestPage == null){
			requestPage = "-1";
		}
		int iRequestPage = Integer.parseInt(requestPage);// 椤甸潰璇锋眰椤� String --> int		
		this.recordCount = recordCount; //鎬昏褰曟暟
		this.pageSize = pageSize; //姣忛〉鏄剧ず鏁�
		
		//鍒嗗灏戦〉锛� 99鎬昏褰�   姣忛〉鏄剧ず10 锛屽垎10椤�  pageCount = 10
		this.pageCount = recordCount % pageSize == 0 ? recordCount/pageSize : recordCount/pageSize + 1;
		
		//璇锋眰椤� 銆嬫�婚〉鏁�
		if (iRequestPage > this.pageCount) { 
			iRequestPage = this.pageCount; //璇锋眰椤� 澶т簬 鏈�澶у垎椤垫暟 锛屽氨鍙栨渶澶у垎椤垫暟
		} else if (iRequestPage < 1) {
			iRequestPage = 1; //璇锋眰椤� 銆� 1 锛屽氨鍙栫涓�椤�
		}

		this.currentPage = iRequestPage;
		this.priviousPage = this.currentPage - 1;
		this.nextPage = this.currentPage + 1;
		this.lastPage = this.pageCount;
		//limit firstRow, rowCount   firstRow 璧风偣
		// 姣忛〉鏄剧ず鏁� * 锛堝綋鍓嶉〉 -1锛�
		// 鏄剧ず绗簩椤�  firstRow = 10 * (2-1) =10
		this.firstRow = new Integer(pageSize * (this.currentPage - 1));
		this.rowCount = this.pageSize;//缁堢偣锛屾樉绀哄灏戞潯鏁版嵁锛屼竴鑸瓑浜巔ageSize
		logger.info("first"+this.firstRow);
		logger.info("row"+this.rowCount);
	}
}
