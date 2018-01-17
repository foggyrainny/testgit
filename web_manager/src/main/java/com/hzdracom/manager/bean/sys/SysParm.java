/**
*
* 项目名称：gf_manager
* 类名称：SysParm
* 类描述：
* 创建人：姓名
* 创建时间：2017年4月24日 上午10:53:42
* 修改人：
* 修改时间：
* 修改备注：
* @version V1.0
* 
*/

package com.hzdracom.manager.bean.sys;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 系统参数信息
 *
 */
public class SysParm implements Serializable {
	
	 private static final long serialVersionUID = 7648161620215166710L;
	  
	  private String id;
	  private String name;
	  private String value; 			//参数值
	  private String value2;
	  private String classify;		//参数分类， 大分类，  例如事件码等  事假码： event_code   电站分析： station_analysis',
	  private String subType;		//二级分类， 小分类，例如事件码中的微逆，SMU等		
	  private String remark;
	  private Date createTime;
	  private Date updateTime;
	  private Integer ord;			//参数排序号  最小最靠前
	  private Integer isRange;   //是否是范围值  0 否  1  是
	  
	  
	/**
	 * @return the isRange
	 */
	public Integer getIsRange() {
		return isRange;
	}
	/**
	 * @param isRange the isRange to set
	 */
	public void setIsRange(Integer isRange) {
		this.isRange = isRange;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the classify
	 */
	public String getClassify() {
		return classify;
	}
	/**
	 * @param classify the classify to set
	 */
	public void setClassify(String classify) {
		this.classify = classify;
	}
	/**
	 * @return the subType
	 */
	public String getSubType() {
		return subType;
	}
	/**
	 * @param subType the subType to set
	 */
	public void setSubType(String subType) {
		this.subType = subType;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @return the ord
	 */
	public Integer getOrd() {
		return ord;
	}
	/**
	 * @param ord the ord to set
	 */
	public void setOrd(Integer ord) {
		this.ord = ord;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	
	  
	  
	  
}
