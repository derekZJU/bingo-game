package dw35_wz23.server.chatroom.model;

import java.io.Serializable;

import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import common.IAddCmd;
import common.IMember;

/**
 * Message for IAddMsg.
 * @author dw35, wz23
 *
 */
public class AddCmdMsg extends DataPacket<IAddCmd> implements Serializable {
	
	private static final long serialVersionUID = 335665726957759633L;
	/**
	 * msg for adding cmd to the visitor
	 * @param senderStub input a senderStub to identify the sender of the msg
	 * @param id msg class
	 * @param cmd cmd excuted in the msg
	 */
	public AddCmdMsg(IMember senderStub,
			Class<?> id, ADataPacketAlgoCmd<ADataPacket, ?, Void> cmd) {
		super(IAddCmd.class, senderStub, new IAddCmd(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 5766623323969041690L;
			@Override
			public Class<?> getID() {
				return id;
			}
			
			@Override
			public ADataPacketAlgoCmd<ADataPacket, ?, Void> getCmd() {
				return cmd;
			}
			
		});
		
	}
	
}
