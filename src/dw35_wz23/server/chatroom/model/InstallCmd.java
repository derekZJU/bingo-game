package dw35_wz23.server.chatroom.model;

import java.io.Serializable;
import java.rmi.RemoteException;

import common.ICmd2ModelAdapter;
import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.mixedData.MixedDataKey;
import dw35_wz23.server.game.controller.GameController;
import dw35_wz23.server.model.InstallMsg;
/**
 * cmd for install the game on the client end
 * @author dw35, wz23
 *
 */
public class InstallCmd extends ADataPacketAlgoCmd<ADataPacket,InstallMsg, Void> implements Serializable{
	transient private ICmd2ModelAdapter model;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1106581587492504309L;

	@Override
	public ADataPacket apply(Class<?> index, DataPacket<InstallMsg> host,
			Void... params) {
		GameController gc = new GameController();
		gc.getModel().setServerStub(host.getSender());
		gc.getModel().setTeamID(host.getData().getTeamID());
		try {
			model.getMDD().put(
					new MixedDataKey<GameController>(host.getSender().getUUID(), "controller", GameController.class)
					, gc);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		gc.start();
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		model = cmd2ModelAdpt;
	}

}
