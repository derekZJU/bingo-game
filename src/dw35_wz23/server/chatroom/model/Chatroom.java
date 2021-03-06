package dw35_wz23.server.chatroom.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import provided.datapacket.ADataPacket;
import common.IChatroom;
import common.IMember;
/**
 * The chatroom entity which holds the chatroom information
 * @author dw35, wz23
 *
 */
public class Chatroom implements IChatroom {
	
	private static final long serialVersionUID = -9153930171282651558L;
	private ArrayList<IMember> memberList = new ArrayList<IMember>();
	private String chatroomName;
	UUID uuID = UUID.randomUUID();
	/**
	 * chatroom constructor without input parameter
	 */
	public Chatroom(){}
	/**
	 * chatroom constructor with input chatroom name
	 * @param name	chatroom name
	 */
	public Chatroom(String name){
		chatroomName = name;
	}
	
	@Override
	/**
	 * getter of the member list
	 */
	public ArrayList<IMember> getMembers() {
		return memberList;
	}

	@Override
	/**
	 * add member collection to the member list
	 */
	public void addMembers(Collection<IMember> userstub) {
		memberList.addAll(userstub);
	}

	@Override
	/**
	 * add single member to the member list
	 */
	public void addMember(final IMember member) {
		memberList.add(member);
	}
	
	@Override
	/**
	 * remove a single member of the member list
	 */
	public void removeMember(IMember userstub) {
		memberList.remove(userstub);
	}

	@Override
	/**
	 *  broadcast the dataPacket to all the members in the member list
	 */
	public Collection<ADataPacket> broadcastMsg(ADataPacket msg) {
		Collection<ADataPacket> packetList = new ArrayList<ADataPacket>();
				for (final IMember member : memberList) {
					try {
						if(!member.equals(msg.getSender())){
						ADataPacket dp = member.recvMessage(msg);
						packetList.add(dp);
						}
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
		return packetList;
	}

	@Override
	/**
	 * getter of the name of the chatroom
	 */
	public String getName() {
		return chatroomName;
	}

	@Override
	/**
	 * getter of the UUID of the chatroom
	 */
	public UUID getUUID() {
		return uuID;
	}

}
