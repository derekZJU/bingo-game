package dw35_wz23.client.chatroom.model;

import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacketAlgo;
/**
 * Adapter from member to model
 * @author dw35,wz23
 *
 */
public interface IMember2ModelAdapter {
	/**
	 * getter of the visitor algo
	 * @return	the visitor
	 */
	public DataPacketAlgo<ADataPacket, Void> getVisitor();
}
