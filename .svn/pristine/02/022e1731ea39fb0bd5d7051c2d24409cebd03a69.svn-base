package dw35_wz23.server.chatroom.model;

import java.io.Serializable;

import common.ICmd2ModelAdapter;
import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import dw35_wz23.server.controller.ServerController;
import dw35_wz23.server.model.EndTimeMsg;
/**
 * cmd for client to tell server the end of the time
 * @author Derek
 *
 */
public class EndTimeCmd extends ADataPacketAlgoCmd<ADataPacket,EndTimeMsg, Void> implements Serializable{

	private static final long serialVersionUID = 4022401461082124189L;

	@Override
	public ADataPacket apply(Class<?> index, DataPacket<EndTimeMsg> host,
			Void... params) {
		ServerController.getInstance().getAppModel().endTime();
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		
	}

}
