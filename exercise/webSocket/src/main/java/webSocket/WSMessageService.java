package webSocket;

import org.springframework.stereotype.Service;

@Service("webSocketMessageService")
public class WSMessageService {
	//声明websocket连接类
	private WebSocketDemo webSocketDemo = new WebSocketDemo();
	
	/**
     * @Title: sendToAllTerminal
     * @Description: 调用websocket类给用户下的所有终端发送消息
     * @param @param userId 用户id
     * @param @param message 消息
     * @param @return 发送成功返回true，否则返回false
     */
	public Boolean sendToAllTerminal(Long userId,String message){
		System.out.println("向用户" + userId + "发送的消息：" + message);
		if(webSocketDemo.sendMessageToUser(userId, message)) {
			return true;
		} else {
			return false;
		}
	}
}
