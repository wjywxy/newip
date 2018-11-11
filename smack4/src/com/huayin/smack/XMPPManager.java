package com.huayin.smack;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;
import org.jivesoftware.smackx.search.ReportedData;
import org.jivesoftware.smackx.search.ReportedData.Row;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.jivesoftware.smackx.xdata.Form;

/**
 * @ClassName:  XMPPManager
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:     wangjinyong
 * @date:       2017年6月1日 上午11:39:45
 * Copyright (c) 2015, Huayin. All rights reserved
 */
public class XMPPManager {
	/** 
     * 判断openfire用户的状态 
     *    strUrl : url格式 - http://my.openfire.com:9090/plugins/presence/status?jid=user1@my.openfire.com&type=xml 
     *    返回值 : 0 - 用户不存在; 1 - 用户在线; 2 - 用户离线 
     *  说明   ：必须要求 openfire加载 presence 插件，同时设置任何人都可以访问 
     */  
	public static String url = "http://localhost:9090/plugins/presence/status?";
    public int doInBackground(String baseUrl, String jid, String type) {
    	String url = baseUrl + "jid="+jid+ "&type="+type;
         int shOnLineState = 0; //-不存在-  
         try  
            {  
               URL oUrl = new URL(url);  
               URLConnection oConn = oUrl.openConnection();  
               InputStream inputStream = oConn.getInputStream();  
            if(oConn!=null)  
            {  
                BufferedReader oIn = new BufferedReader(new InputStreamReader(inputStream));  
                if(null!=oIn)  
                {  
                    String strFlag = oIn.readLine();  
                    oIn.close();  
                    if(strFlag.indexOf("type=\"unavailable\"")>=0)  
                    {  
                        shOnLineState = 2;  
                    }  
                    if(strFlag.indexOf("type=\"error\"")>=0)  
                    {  
                        shOnLineState = 0;  
                    }  
                    else if(strFlag.indexOf("priority")>=0 || strFlag.indexOf("id=\"")>=0)  
                    {  
                        shOnLineState = 1;  
                    }  
                }  
            }  
            }  
            catch(Exception e)  
            {  
                e.printStackTrace();
            }  
        return shOnLineState;  
    }  
    
    //搜索用户
    public List<String> searchUser(AbstractXMPPConnection conn,String username){
    	try{  
    		List<String> listUser = new ArrayList<String>();
            UserSearchManager search = new UserSearchManager(conn);
            Form searchForm = search.getSearchForm("search."+GetXMPPConnection.serverName);  
            Form answerForm = searchForm.createAnswerForm();
            answerForm.setAnswer("Username", true);
            answerForm.setAnswer("search", username.trim());  
            ReportedData data = search.getSearchResults(answerForm,"search."+GetXMPPConnection.serverName);  
              
            List<Row> rows = data.getRows();
            for(Row row : rows){
            	listUser.add(row.getValues("Username").get(0));
            	System.out.println(row.getValues("Username").get(0));
            }
            return listUser;
        }catch(Exception e){  
            e.printStackTrace();
        }
    	return null;
    }
    
    //发送添加好友申请
    public boolean sendAddUserMsg(AbstractXMPPConnection conn,String userName){
    	if (conn == null || userName == null){
    		return false;
    	}
    	String jid = userName + "@" + conn.getServiceName();
    	Presence p = new Presence(Type.subscribe);
    	p.setTo(jid);
    	try {
			conn.sendStanza(p);
		} catch (NotConnectedException e) {
			e.printStackTrace();
		}
    	return true;
    }
}
