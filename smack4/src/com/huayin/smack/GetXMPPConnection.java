package com.huayin.smack;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration.Builder;

/**
 * @ClassName:  GetXMPPConnection
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:     wangjinyong
 * @date:       2017年4月18日 下午9:23:33
 * Copyright (c) 2015, Huayin. All rights reserved
 */
public class GetXMPPConnection {
	//public static XMPPTCPConnection connection = null;
	public static String serverName = "localhost";
	/**
	 * 连接指定服务器
	 * @Title:        getConnection 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @return    
	 * @return:       AbstractXMPPConnection    
	 * @throws 
	 * @author        wangjinyong
	 * @Date          2017年6月1日 上午11:47:20
	 */
	public static AbstractXMPPConnection  getConnection(){
		AbstractXMPPConnection connection = null;
		Builder builder = XMPPTCPConnectionConfiguration.builder();
		builder.setSecurityMode(SecurityMode.disabled);
		//builder.setDebuggerEnabled(true);
		XMPPTCPConnectionConfiguration config = builder.setServiceName("localhost").setHost("localhost").setPort(5222)
				.build();
		connection = new XMPPTCPConnection(config);
		return connection;
	}
	public static void closeConnection(AbstractXMPPConnection connection){
		if (connection != null) {
			try {
				connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}
