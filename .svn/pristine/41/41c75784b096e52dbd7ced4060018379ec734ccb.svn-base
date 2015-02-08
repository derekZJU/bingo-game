package dw35_wz23.server.chatroom.model;
import java.io.Serializable;

import common.ICmd2ModelAdapter;
import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import dw35_wz23.server.model.AddComponentMsg;
/**
 * command  for adding component
 * @author dw35,wz23
 *
 */
public class AddComponentCmd extends ADataPacketAlgoCmd<ADataPacket,AddComponentMsg, Void> implements Serializable{

	private static final long serialVersionUID = 7405576966148491251L;
	transient private ICmd2ModelAdapter model;
	@Override
	public ADataPacket apply(Class<?> index, DataPacket<AddComponentMsg> host,
			Void... params) {
		model.addGameComponent(host.getData().getComponentName(), host.getData().getTargetComponent());
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		model = cmd2ModelAdpt;
		
	}

}
