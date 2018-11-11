package com.huayin.smack;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.NotConnectedException;
import org.jivesoftware.smack.StanzaListener;
import org.jivesoftware.smack.filter.StanzaFilter;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Stanza;
import org.jivesoftware.smack.roster.Roster;

public class SendMessage {
	public static void testSendMessage() throws SmackException{
		AbstractXMPPConnection connection = null;
		try {
			connection = GetXMPPConnection.getConnection();
			connection.connect();
			connection.setPacketReplyTimeout(30000);
			connection.login("test3", "123123");
			System.out.println("================");
			//登录后，监听服务器转发的消息
			connection.addAsyncStanzaListener(new StanzaListener() {
				
				@Override
				public void processPacket(Stanza packet) throws NotConnectedException {
					if(packet instanceof Presence){
						Presence presence = (Presence)packet;
						String from = presence.getFrom();//发送方    
	                    String to = presence.getTo();//接收方 
	                    if (presence.getType().equals(Presence.Type.subscribe)) {
	                         System.out.println("收到"+from+"添加请求！");
	                         //Presence presenceRes = new Presence(Presence.Type.subscribed);  
                             //presenceRes.setTo(from);
                             //GetXMPPConnection.connection.sendStanza(presenceRes);
	                         //Roster roster = Roster.getInstanceFor(GetXMPPConnection.connection);
	                         
                             //添加好友
                             //XMPPTest.addUser(roster, from, from);
	                     } else if (presence.getType().equals(Presence.Type.subscribed)) {
	                        //发送广播传递response字符串     恭喜，对方同意添加好友！
	                    	 System.out.println("恭喜，对方同意添加好友！");
	                     } else if (presence.getType().equals(Presence.Type.unsubscribe)) {
	                        //发送广播传递response字符串      抱歉，对方拒绝添加好友，将你从好友列表移除！
	                     } else if (presence.getType().equals(Presence.Type.unsubscribed)){
	                    	 //
	                     } else if (presence.getType().equals(Presence.Type.unavailable)) {
	                         System.out.println("好友下线！");  
	                     } else {    
	                         System.out.println("好友上线！");  
	                     }
					}
				}
			}, new StanzaFilter() {
				//消息全部接受
				@Override
				public boolean accept(Stanza stanza) {
					if(stanza.getFrom().contains("wjy")){
						
						return true;
					} else {
						return false;
					}
				}
			});

			// 发送消息
			/*ChatManager chatmanager = ChatManager.getInstanceFor(GetXMPPConnection.connection);
			Chat newChat = chatmanager.createChat("wjy@localhost");
			newChat.sendMessage("我是wjy");*/

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			GetXMPPConnection.closeConnection(connection);
			
		}
		while(true);
	}
	public static void main(String[] args) throws SmackException {
		testSendMessage();
		//GetXMPPConnection.closeConnection();
	}
}
