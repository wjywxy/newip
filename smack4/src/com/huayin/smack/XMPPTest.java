package com.huayin.smack;

import java.io.IOException;
import java.util.List;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smackx.iqregister.AccountManager;

/**
 * @ClassName:  XMPPTest
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:     wangjinyong
 * @date:       2017年4月18日 下午10:00:32
 * Copyright (c) 2015, Huayin. All rights reserved
 */
public class XMPPTest {
	public static void fail(Object o, Object... args) {  
        if (o != null && args != null && args.length > 0) {  
            String s = o.toString();  
            for (int i = 0; i < args.length; i++) {  
                String item = args[i] == null ? "" : args[i].toString();  
                if (s.contains("{" + i + "}")) {  
                    s = s.replace("{" + i + "}", item);  
                } else {  
                    s += " " + item;  
                }  
            }  
            System.out.println(s);  
        }  
    }  
	
	public static boolean createAccount(AbstractXMPPConnection connection,String username, String password) {
		try {  
            if (connection == null)
            {
                return false;
            }
            AccountManager manager=AccountManager.getInstance(connection);  
            manager.createAccount(username, password);
            return true;
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return false;  
	}
	
	/**
	 * 添加一个组
	 */
	public static boolean addGroup(Roster roster,String groupName)
	{
		try {
			roster.createGroup(groupName);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除一个组
	 */
	public static boolean removeGroup(Roster roster,String groupName)
	{
		return false;
	}
	
	/**
	 * 添加一个好友  无分组
	 */
	public static boolean addUser(Roster roster,String userName,String name)
	{
		try {
			roster.createEntry(userName, name, null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 添加一个好友到分组
	 * @param roster
	 * @param userName
	 * @param name
	 * @return
	 */
	public static boolean addUser(Roster roster,String userName,String name,String groupName)
	{
		try {
			roster.createEntry(userName, name,new String[]{ groupName});
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除一个好友
	 * @param roster
	 * @param userName
	 * @return
	 */
	public static boolean removeUser(Roster roster,String userName)
	{
		try {
			
			if(userName.contains("@"))
			{
				userName = userName.split("@")[0];
			}
			RosterEntry entry = roster.getEntry(userName);
			System.out.println("删除好友:"+userName);
			System.out.println("User: "+(roster.getEntry(userName) == null));
			roster.removeEntry(entry);
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/** 
	 * @Title:        main 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param args    
	 * @return:       void    
	 * @throws IOException 
	 * @throws SmackException 
	 * @throws 
	 * @author        wangjinyong
	 * @Date          2017年4月18日 下午10:00:32 
	 */
	public static void main(String[] args) throws SmackException, IOException {
		try {
			AbstractXMPPConnection connection = null;
			connection = GetXMPPConnection.getConnection();
			connection.setPacketReplyTimeout(15000);
			//GetXMPPConnection.connection.setPacketReplyTimeout(20000);
			connection.connect();
//			SASLAuthentication.unBlacklistSASLMechanism("PLAIN");
//			SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
			
			/*AbstractConnectionListener acl = new AbstractConnectionListener();
			GetXMPPConnection.connection.addConnectionListener(acl);*/
			connection.login("admin", "123123");
			Roster roster = Roster.getInstanceFor(connection);
			fail("User:",connection.getUser());
			//createAccount(GetXMPPConnection.connection,"test4","123123");
			//addUser(roster,"test4","test4","group1");
			//fail("Roster:",roster.getEntryCount());
			
			//搜索用户
			XMPPManager xmppm = new XMPPManager();
			List<String> users = xmppm.searchUser(connection,"test");
			System.out.println(users.size());
			
			//boolean flag = xmppm.sendAddUserMsg(connection,"test3");
			//System.out.println(flag);
			
			//下面通过设置sleep(1000)使程序暂定1秒钟，来获取其好友列表及好友的在线状态；
			//如果不设置sleep(1000)，则程序获取在线好友会不稳定，有时可以获取到，有时获取不到
	       /* try {
	            Thread.sleep(1000);
			} catch (InterruptedException e) {
			    e.printStackTrace();
			}
	        Collection<RosterEntry> entries = roster.getEntries();
	        for(RosterEntry entry : entries){
	            //获取好友在线状态
	            Presence presence = roster.getPresence(entry.getUser()); 
	            System.out.println(entry.getUser() +" : "+presence.getType()+ " : "+entry.getGroups()+" : "+presence.isAvailable());
	            //fail("Groups: {0}, Name: {1}, Status: {2}, Type: {3}, User: {4}", entry.getGroups(), entry.getName(), entry.getStatus(), entry.getType(), entry);  
	        
		        XMPPManager xmppm = new XMPPManager();
		        int status = xmppm.doInBackground(XMPPManager.url, entry.getName()+"@localhost", "xml");
		        System.out.println("status = "+status);
	        }*/
			
	        /*ChatManager chatmanager = ChatManager.getInstanceFor(GetXMPPConnection.connection);
			System.out.println("等待接受消息...");

			chatmanager.addChatListener(new ChatManagerListener() {
				@Override
				public void chatCreated(Chat chat, boolean create) {
					chat.addMessageListener(new ChatMessageListener() {

						@Override
						public void processMessage(Chat chat, Message msg) {
							// TODO Auto-generated method stub
							if (null != msg.getBody()) {
								System.out.println("接收到新消息：" + msg.getBody());
							}
						}
					});
				}
			});*/
			
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		while (true);
	}

}
