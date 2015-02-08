package dw35_wz23.server.chatroom.model;

import java.io.Serializable;
import java.rmi.RemoteException;

import common.ICmd2ModelAdapter;
import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.mixedData.MixedDataKey;
import dw35_wz23.server.game.controller.GameController;
import dw35_wz23.server.model.UpdatePanelMsg;
/**
 * the cmd for updating the matrix in the panel of the client
 * @author dw35, wz23
 *
 */
public class UpdatePanelCmd extends ADataPacketAlgoCmd<ADataPacket,UpdatePanelMsg, Void> implements Serializable{
	private static final long serialVersionUID = 1446980389173218800L;
	
	transient private ICmd2ModelAdapter model;
	
	@Override
	public ADataPacket apply(Class<?> index, DataPacket<UpdatePanelMsg> host,
			Void... params) {
		try {
			GameController gc = model.getMDD().get(new MixedDataKey<GameController>(host.getSender().getUUID(), "controller", GameController.class));
			gc.getModel().updateMatrix(host.getData().getMatrix());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		model = cmd2ModelAdpt;	
	}

}
