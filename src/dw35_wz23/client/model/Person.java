package dw35_wz23.client.model;

import java.rmi.RemoteException;
import java.util.UUID;

import javax.swing.JOptionPane;

import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacket;
import dw35_wz23.client.chatroom.controller.ChatroomController;
import dw35_wz23.client.controller.ClientController;
import common.IChatroom;
import common.IJoinRoomMsg;
import common.IPerson;

/**
 * The person class implements IPerson
 * 
 * @author dw35, wz23
 */
public class Person implements IPerson {
	/**
	 * the user name of person
	 */
	private String personName;
	/**
	 * uuID
	 */
	private UUID uuID;

	/**
	 * Constructor of Person class
	 * 
	 * @param personName
	 *            the name of the person.
	 */
	public Person(String personName) {
		uuID = UUID.randomUUID();
		this.personName = personName;
	}

	public UUID getUuID() {
		return uuID;
	}

	public void setUuID(UUID uuID) {
		this.uuID = uuID;
	}

	/**
	 * connect back method
	 */
	@Override
	public int connectBack(IPerson myPersonStub) throws RemoteException {	
		return STATUS_SUCC;
	}

	/**
	 * accept invitation to join a chatroom.
	 */
	@Override
	public int acceptInvitation(IChatroom chatroom) throws RemoteException {
		ChatroomController newController = ClientController.getInstance()
				.getAppModel().addChatroom(chatroom);
		System.out.println(newController.getMiniModel().getChatroom()
				.getMembers().size());
		ADataPacket joinPacket = new DataPacket<IJoinRoomMsg>(
				IJoinRoomMsg.class, newController.getMiniModel().getMember(),
				new JoinRoomMsg(newController.getMiniModel().getMember()));
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
		ClientController
				.getInstance()
				.getAppModel()
				.sendInvitation(
						requesterStub,
						ClientController.getInstance().getAppModel()
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
