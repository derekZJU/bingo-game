package dw35_wz23.client.model;

import common.IJoinRoomMsg;
import common.IMember;
/**
 * massage for joining room
 * @author dw35, wz23
 *
 */
public class JoinRoomMsg implements IJoinRoomMsg{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6005960872436316607L;
	private IMember member;
	/**
	 * constructor of joinRoomMsg
	 * @param m
	 */
	public JoinRoomMsg(IMember m){
		member = m;
	}
	/**
	 * getter of the member in the msg
	 */
	@Override
	public IMember getMember() {
				return member;
	}

}
