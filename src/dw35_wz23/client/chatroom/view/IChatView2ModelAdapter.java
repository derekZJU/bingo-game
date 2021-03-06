package dw35_wz23.client.chatroom.view;

import common.IChatroom;
import common.IMember;

/**
 * chat view to model adapter
 * @author dw35,wz23
 *
 */
public interface IChatView2ModelAdapter {
	/**
	 * send a input text message
	 * @param str	 input message
	 */
	public void sendTextMessage(String str);
	/**
	 * broadcast message when leaving a chatroom
	 */
	public void sendLeaveMessage();
	public IChatroom getChatroom();
	public IMember getMember();
}
