package dw35_wz23.server.chatroom.controller;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JTextArea;

import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacket;
import common.IChatroom;
import common.IMember;
import common.ITextMsg;
import dw35_wz23.client.model.TextMsg;
import dw35_wz23.server.chatroom.model.ChatroomModel;
import dw35_wz23.server.chatroom.model.IChatModel2ViewAdapter;
import dw35_wz23.server.chatroom.view.ChatroomView;
import dw35_wz23.server.chatroom.view.IChatView2ModelAdapter;
/**
 * the controller of the chat room, which starts each chat room.
 * @author dw35, wz23
 *
 */
public class ChatroomController{
	private ChatroomModel miniModel;
	private ChatroomView miniView;
	/**
	 * constructor of the chat room controller
	 * @param chatroom	input chatroom entity to construct a mini MVC
	 * @param name	input String to set the name of the owner of chatroom 
	 */
	public ChatroomController(IChatroom chatroom, String name){
		miniModel = new ChatroomModel(chatroom, name, new IChatModel2ViewAdapter(){
			@Override
			public void updateMemberList(Collection<IMember> collection) {
				miniView.memberListModel.clear();
				Iterator<IMember> it=collection.iterator();
				while(it.hasNext()){
					try {
						miniView.memberListModel.addElement(it.next().getName());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
				miniView.memberList.setModel(miniView.memberListModel);
			}
			@Override
			public JTextArea getTextArea() {
				return miniView.getTextArea();
			}
		});
		miniView = new ChatroomView(new IChatView2ModelAdapter(){
			@Override
			public void sendTextMessage(String str) {
				ITextMsg newMsg = new TextMsg(str);
				ADataPacket newPacket =new DataPacket<ITextMsg>(ITextMsg.class, miniModel.getMember(), newMsg);
				miniModel.sendMessage(newPacket);
			}

			@Override
			public void sendLeaveMessage() {
				miniModel.sendLeaveMsg();
				
			}

			@Override
			public IChatroom getChatroom() {
				return miniModel.getChatroom();
			}

			@Override
			public IMember getMember() {
				return miniModel.getMember();
			}		
		});
	}
	/**
	 * start of the mini controller
	 */
	public void start(){
		miniView.start();
		miniModel.start();
	}
	/**
	 * return the mini Model of the mini MVC
	 * @return	mini Model of the MVC
	 */
	public ChatroomModel getMiniModel(){
		return miniModel;
	}
	/**
	 * getter of the mini View
	 * @return	mini View of the chatroom
	 */
	public ChatroomView getChatroomView(){
		return miniView;
	}
	
}
