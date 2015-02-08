package dw35_wz23.server.model;

import java.rmi.RemoteException;
import java.util.UUID;
import javax.swing.JOptionPane;

import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacket;
import dw35_wz23.server.chatroom.controller.ChatroomController;
import dw35_wz23.server.controller.ServerController;
import common.IChatroom;
import common.IJoinRoomMsg;
import common.IPerson;

/**
 * The person class implements IPerson
 * 
 * @author dw35, wz23
 *
 */
public class Person implements IPerson {
	/**
	 * the user name of person
	 */
	private String personName;
	/**
	 * uuID
	 */
	private UUID uuID = UUID.randomUUID();

	/**
	 * Constructor of Person class
	 * 
	 * @param personName
	 *            the name of the person.
	 */
	public Person(String personName) {
		this.personName = personName;
	}

	/**
	 * connect back method
	 */
	@Override
	public int connectBack(IPerson myPersonStub) throws RemoteException {
		//recvRequest(myPersonStub);
		return STATUS_SUCC;
	}

	/**
	 * accept invitation to join a chatroom.
	 */
	@Override
	public int acceptInvitation(IChatroom chatroom) throws RemoteException {
		ChatroomController newController = ServerController.getInstance()
				.getAppModel().addChatroom(chatroom);
		System.out.println(newController.getMiniModel().getChatroom()
				.getMembers().size());
		ADataPacket joinPacket = new DataPacket<IJoinRoomMsg>(
				IJoinRoomMsg.class, newController.getMiniModel().getMember(),
				null);
		newController.getMiniModel().sendMessage(joinPacket);
		return IPerson.STATUS_SUCC;
	}
	/**
	 * Receive the request to join the chat room.
	 */
	@Override
	public int recvRequest(IPerson requesterStub) throws RemoteException {
		Object[] obj2 ={ "Team01", "Team02"};  
		String s = (String) JOptionPane.showInputDialog(null,"Please choose the team for "+requesterStub.getName()+":\n", "Team Distribution", JOptionPane.PLAIN_MESSAGE, null, obj2, "Team01");  
		int team;
		if(s.equals("Team01"))
			team = 1;
		else
			team = 2;
		ServerController
		.getInstance()
		.getAppModel()
		.sendInvitation(
				requesterStub,
				ServerController.getInstance().getAppModel()
						.getChatroomList()
						.get(0)
						.getMiniModel().getChatroom());
		ServerController
				.getInstance()
				.getAppModel()
				.sendInvitation(
						requesterStub,
						ServerController.getInstance().getAppModel()
								.getChatroomList()
								.get(team)
								.getMiniModel().getChatroom());
		return 0;
	}
	/**
	 * get the user name 
	 */
	@Override
	public String getName() throws RemoteException {
		return this.personName;
	}
	/**
	 * Get the UUID
	 */
	@Override
	public UUID getUUID() throws RemoteException {
		return uuID;
	}
}