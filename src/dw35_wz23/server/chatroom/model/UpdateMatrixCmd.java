package dw35_wz23.server.chatroom.model;

import java.io.Serializable;

import common.ICmd2ModelAdapter;
import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import dw35_wz23.server.controller.ServerController;
import dw35_wz23.server.model.UpdateMatrixMsg;
/**
 * cmd for updating the matrix in the server to syn the data
 * @author dw35, wz23
 *
 */
public class UpdateMatrixCmd extends ADataPacketAlgoCmd<ADataPacket,UpdateMatrixMsg, Void> implements Serializable{
	private static final long serialVersionUID = 657460209307562993L;
	transient private ICmd2ModelAdapter model;
	/**
	 * getter of the model
	 * @return got model
	 */
	public ICmd2ModelAdapter getModel() {
		return model;
	}
	/**
	 * setter of the model
	 */
	public void setModel(ICmd2ModelAdapter model) {
		this.model = model;
	}

	@Override
	public ADataPacket apply(Class<?> index, DataPacket<UpdateMatrixMsg> host,
			Void... params) {
		ServerController.getInstance().getAppModel().updateMatrix(
				host.getData().getTeamID(), host.getData().getDishNumber());
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		model = cmd2ModelAdpt;	
	}

}
