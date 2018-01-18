package com.hzdracom.core.bean;



/**
 * @Title: RedisType.java
 * @Package com.hzdracom.bean
 * @Description: TODO(添加描述)
 * @author 刘章
 * @date 2014-3-21 上午11:33:13
 */
public enum RedisType {
	
	
	server_run("本地线程调度","server_run"),
	config("系统参数","config"),
	ip_port("服务器ip和端口","ip_port"),
	sigar("服务器运行信息","sigar"),
	
	//系统分表
	sysTableMap("系统分表集合","sysTableMap"),
	
	//smu指定连接采集地址
	routeIpAndPort("smu指定连接采集地址","routeIpAndPort"),
	
	//smu_session("SMU会话对应的smuID","smu_session"),
	//smu_online("SMU在线信息","smu_online"),
	smu_online_map("SMU在线信息集合","smu_online"),
	smu_update_map("SMU升级信息集合","smu_update"),
	
	smu_power("Smu实时功率","smu_power"),
	smu_five_power("Smu5分钟功率","smu_five_power"),
	smu_fifteen_power_generation("Smu15分钟发电量","smu_fifteen_power_generation"),
	smu_day_power_generation("Smu日发电量","smu_day_power_generation"),
	
	rc_power("逆变器实时状态","rc_power"),
	rc_fifteen_power_generation("逆变器15分钟发电量","rc_fifteen_power_generation"),
	rc_day_power_generation("逆变器日发电量","rc_day_power_generation"),
	
	
	//manager_user_socket("websocketSession","manager_user_socket"),
	manager_user_message("消息发送队列","manager_user_message"),
	
	serverMessage("系统信息队列","serverMessage"),
	
	smuWSerror("握手失败的SMU","smuWSerror"),
	
	smuRouteIp("smu分流IP记录","smuRouteIp"),
	smuLog("smu日志","smuLog"),
	
	tcpsmuWoshu("smuId最后一次握手hex","tcpsmuWoshu"),
	tcphexString("hex数据串","tcphexString"),
	tcpErrorhexString("无法处理的hex数据串","tcpErrorhexString"),
	tcpsessionIdsmuId("sessionId对应的smuId","tcpsessionIdsmuId"),
	tcpsmuSessionId("smuId对应的sessionId","tcpsmuSessionId"),
	
	imgUrlPrefix("app接口图片接口替换前缀","imgUrlPrefix"),

	weather_weather_value("天气信息","weather_weather_value"),
	weather_weather_code("天气现象编码","weather_weather_code"),//天气编号:we 风向：wd  风力：ws
	//weather_windDirect_code("风向编码","weather_windDirect_code"),
	//weather_windSpd_code("风力编码","weather_windSpd_code"),
	
	// 实时数据缓存
	real_data_smu_power_map(" SMU实时功率 以及 上报时间(不需要分 多个 key)","real_data_smu_power_map"),
	real_data_smu_inverter_map("SMU设备下微逆的实时数据(区分多个Key)","real_data_smu_inverter_map"),
	real_data_smu_inverter_status("实时数据处理状态","real_data_smu_inverter_status"),
	// 数据服务 缓存 key
	data_stationid_smuid_map("电站 id和 SMUID关系Map","data_stationid_smuid_map"),
	        // 此key 可能会废除 直接使用  real_data_smu_power 的数据
	data_smuid_power_map("SMU实时功率(包含上报时间)","data_smuid_power_map"),

	data_station_baseinfo("电站基础信息","data_station_baseinfo"),
	data_station_childsmuids("电站id 和  下级SMU设备Id 的缓存(主要用在读取虚拟电站功率上)","data_station_childsmuids"),
	
	
	data_station_energy_map("电站电量信息","data_station_energy_map"),
	
	data_inverter_vol_map("微逆基本信息(用于微逆状态计算)","data_inverter_vol_map"),
	data_client_info("业主相关信息","data_client_info"),
	
	data_client_station_relation("电站 - 业主  相关信息","data_client_station_relation"),
	
	data_station_energy_day("当天累计电量","data_station_energy_day"),
	data_station_energy_month("当月累计电量（不包含当天）","data_station_energy_month"),
	data_station_energy_all("全部 累计电量（不包含当月）","data_station_energy_all"),
	
	data_inverter_energy_status_map("微逆电量状态信息","data_inverter_energy_status_map"),
	
	data_smu_ver_list("smu版本号列表","data_smu_ver_list"),
	
	;
	
	private String name;
	private String value;
	
	RedisType(String name,String value) {
		this.name = name;
		this.value = value;
	}
	
	
	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
