package dw35_wz23.client.chatroom.model;

import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.SwingUtilities;

import provided.datapacket.ADataPacket;
import provided.datapacket.ADataPacketAlgoCmd;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.extvisitor.IExtVisitorCmd;
import provided.mixedData.IMixedDataDictionary;
import dw35_wz23.client.controller.ClientController;
import dw35_wz23.client.model.LeaveRoomMsg;
import dw35_wz23.client.model.Requestcmd;
import dw35_wz23.client.view.ComponentShowCaseDlg;
import common.IAddCmd;
import common.IChatroom;
import common.ICmd2ModelAdapter;
import common.IFailMsg;
import common.IJoinRoomMsg;
import common.ILeaveRoomMsg;
import common.IMember;
import common.IRequestCmd;
import common.ISuccessMsg;
import common.ITextMsg;
/**
 * model of the mini MVC
 * @author dw35,wz23
 *
 */
public class ChatroomModel {
	
	/**
	 * The chat room.
	 */
	private IChatroom chatroom;
	
	
	/**
	 * The IMember stub.
	 */
	private IMember member;
	
	
	/**
	 * The model uses this adapter to talk to view.
	 */
	private IChatModel2ViewAdapter model2ViewAdapter;
	
	/**
	 * constructor of the model
	 * @param chatroom
	 * @param name
	 * @param model2ViewAdapter
	 */
	public ChatroomModel(IChatroom chatroom, String name, IChatModel2ViewAdapter model2ViewAdapter) {
		this.chatroom = chatroom;
		try {
			this.member = new Member(name, new IMember2ModelAdapter(){
				@Override
				public DataPacketAlgo<ADataPacket, Void> getVisitor() {
					return cmdVisitor;
				}
				
			}, ClientController.getInstance().getUuID());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.model2ViewAdapter = model2ViewAdapter;
	}
	/**
	 * visitor in each mini MVC
	 */
	public DataPacketAlgo<ADataPacket,Void> cmdVisitor
	= new DataPacketAlgo<ADataPacket,Void>(new ADataPacketAlgoCmd<ADataPacket,Object, Void>(){
		/**
		 * unknown message
		 */
		private static final long serialVersionUID = -3246365918710192755L;
		@Override
		public ADataPacket apply(Class<?> index, DataPacket<Object> host,
				Void... params) {
			try {
				IRequestCmd cmd = new Requestcmd(index);
				ADataPacket msg = new DataPacket<IRequestCmd>(IRequestCmd.class, member, cmd);
				ADataPacket addCmdPacket = host.getSender().recvMessage(msg);
				System.out.println("now trying to add cmd: " + msg.getClass());
				System.out.println("----in request construct----");
				addCmdPacket.execute(cmdVisitor);
				host.execute(cmdVisitor);
			} catch (RemoteException e) {
				e.printStackTrace();
				return new DataPacket<IFailMsg>(IFailMsg.class, member, null);
			}
			return new DataPacket<ISuccessMsg>(ISuccessMsg.class, member, null);
		}

		@Override
		public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
			
		}});
	
	/**
	 * init the visitor
	 */
	private void initVisitor() {
		//ISuccessMsg
		cmdVisitor.setCmd(ISuccessMsg.class, new ADataPacketAlgoCmd<ADataPacket,Object, Void>(){
			private static final long serialVersionUID = -1579347566593508620L;

			@Override
			public ADataPacket apply(Class<?> index, DataPacket<Object> host,
					Void... params) {
				return new DataPacket<ISuccessMsg>(ISuccessMsg.class, member, new SuccessMsg());
			}

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}
			
		});
		/**
		 * IAddCmd
		 */
		cmdVisitor.setCmd(IAddCmd.class, new ADataPacketAlgoCmd<ADataPacket,IAddCmd, Void>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 8408691959122383177L;
			@Override
			public ADataPacket apply(Class<?> index, DataPacket<IAddCmd> host,
					Void... params) {
				System.out.println("msg receiving is: " + host.getData());
				System.out.println("cmd receiving is: " + host.getData().getCmd());
				ADataPacketAlgoCmd<ADataPacket, ?, ?> newCmd =  host.getData().getCmd();
				System.out.println("cmd received is: " + newCmd.getClass());
				/*set cmd2modelAdapter*/
				//error: cmd is empty
				newCmd.setCmd2ModelAdpt(new ICmd2ModelAdapter(){
					@Override
					public void displayStringMsg(String s) {
						try {
							model2ViewAdapter.getTextArea().append(host.getSender().getName()+": "+s + "\n");
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						
					}
					@Override
					public void displayComponentMsg(String name,Component comp) {
						ComponentShowCaseDlg dlg = new ComponentShowCaseDlg(comp);
						dlg.setVisible(true);		
					}
					@Override
					public IMember getLocalMember() {
						return member;
					}
					@Override
					public void addGameComponent(String name,Component comp) {
						ComponentShowCaseDlg dlg = new ComponentShowCaseDlg(comp);
						//dlg.setVisible(true);	
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								dlg.setVisible(true);
							}
						});
						
					}
					@Override
					public void send2Member(IMember member, ADataPacket msg) {
						try {
							member.recvMessage(msg);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						
					}
					@Override
					public void send2Team(ADataPacket msg) {
						sendMessage(msg);
						
					}
					@Override
					public IMixedDataDictionary getMDD() {
						return ClientController.getInstance().getAppModel().getMDD();
					}

					
				});
				cmdVisitor.setCmd(host.getData().getID(), (IExtVisitorCmd<ADataPacket, Class<?>, Void, ADataPacket>) host.getData().getCmd());
				
				return new DataPacket<ISuccessMsg>(ISuccessMsg.class, member, null);
			}

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}
			
		});
		
		/**
		 * IRequestCmd
		 */
		cmdVisitor.setCmd(IRequestCmd.class, new ADataPacketAlgoCmd<ADataPacket,IRequestCmd, Void>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 4954604826153130846L;
			@Override
			public ADataPacket apply(Class<?> index, DataPacket<IRequestCmd> host,
					Void... params) {
				
				Class<?> id = host.getData().getID();
				
				@SuppressWarnings("unchecked")
				ADataPacketAlgoCmd<ADataPacket, ?, Void> cmd = (ADataPacketAlgoCmd<ADataPacket, ?, Void>) cmdVisitor.getCmd(id);
				System.out.println("----in processing requestCmd----");
				System.out.println("The id get from DataPacket is: " + id);
				System.out.println("The id get from DataPacket hashcode: " + id.hashCode());
				System.out.println("the cmd trying to be requested is: " + cmd);
				return new AddCmdMsg(member, id, cmd);
			}
			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}
		});
		
		/**
		 * ITextMsg
		 */
		cmdVisitor.setCmd(ITextMsg.class, new ADataPacketAlgoCmd<ADataPacket,Object, Void>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 3448963859920984755L;

			@Override
			public ADataPacket apply(Class<?> index, DataPacket<Object> host,
					Void... params) {
				ITextMsg textMsg = (ITextMsg) host.getData();
				String rcvdMsg = textMsg.getText();
				//append msg in the textArea
				try {
					model2ViewAdapter.getTextArea().append(host.getSender().getName()+": "+rcvdMsg + "\n");
				} catch (RemoteException e) {
					e.printStackTrace();
					return new DataPacket<IFailMsg>(IFailMsg.class, member, null);
				}
				return new DataPacket<ISuccessMsg>(ISuccessMsg.class, member, null);
			}

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}
			
		});
		
		/**
		 * IJoinRoomMsg
		 */
		cmdVisitor.setCmd(IJoinRoomMsg.class, new ADataPacketAlgoCmd<ADataPacket,Object, Void>(){

			/**
			 * 
			 */
			private static final long serialVersionUID = -2162082738460641288L;
			@Override
			public ADataPacket apply(Class<?> index, DataPacket<Object> host,
					Void... params) {
				//IJoinRoomMsg joinMsg = (IJoinRoomMsg) host.getData();
				IMember jointMember = host.getSender();
				//add the new member to the memberList in chatroom and update the Chatroom MemberList
				for(IMember m : chatroom.getMembers()){
					try {
						if (m.getUUID().equals(jointMember.getUUID())){
							updateMemberList();
							return new DataPacket<IFailMsg>(IFailMsg.class, member, null);
						}
					} catch (RemoteException e) {
						System.out.println("UUID compare Error when process joinMsg");
						e.printStackTrace();
						return new DataPacket<IFailMsg>(IFailMsg.class, member, null);
					}
				}
				chatroom.addMember(jointMember);
				updateMemberList();
				return new DataPacket<ISuccessMsg>(ISuccessMsg.class, member, null);
			}
			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}
			
		});
		
		/**
		 * ILeaveRoomMsg
		 */
		cmdVisitor.setCmd(ILeaveRoomMsg.class, new ADataPacketAlgoCmd<ADataPacket,Object, Void>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 2658765937391683540L;
			@Override
			public ADataPacket apply(Class<?> index, DataPacket<Object> host,
					Void... params) {
				IMember leftMember = host.getSender();
				try {
					if (!member.getUUID().equals(leftMember.getUUID())) {
						chatroom.removeMember(leftMember);
						updateMemberList();
						return new DataPacket<ISuccessMsg>(ISuccessMsg.class, member, null);
					} else {
						chatroom.removeMember(leftMember);
						updateMemberList();
						return new DataPacket<ISuccessMsg>(ISuccessMsg.class, member, null);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
					return new DataPacket<IFailMsg>(IFailMsg.class, member, null);
				}
			}
			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
				
			}
		});
		
	}
	/**
	 * getter of the chatroom
	 * @return	 the chatroom in the mini Model
	 */
	public IChatroom getChatroom() {
		return chatroom;
	}
	
	
	
	/**
	 * Get IMember stub.
	 * @return IMember stub.
	 */
	public IMember getMember() {
		return member;
	}
	
	/**
	 * Set IMember stub
	 * @param mb IMember stub
	 */
	public void setMember(IMember mb) {
		member = mb;
	}
	
	
	/**
	 * Send message to the chat room.
	 * @param msg The message.
	 */
	public void sendMessage(ADataPacket msg) {
		(new Thread(){
			public void run(){
				chatroom.broadcastMsg(msg);
			}
		}).start();	
	}
	/**
	 * send message with blocking
	 * @param msg
	 */
	public void sendLeaveMessage(ADataPacket msg){
		chatroom.broadcastMsg(msg);
	}
	
	/**
	 * Start the model.
	 */
	public void start(){
		initVisitor();
	}
	
	
	/**
	 * Update member list.
	 */
	public void updateMemberList(){
		System.out.println("before update, memberList is "+chatroom.getMembers());
		model2ViewAdapter.updateMemberList(chatroom.getMembers());
	}
	
	
	/**
	 * Send leave chat room message.
	 */
	public void sendLeaveMsg() {
		ADataPacket leavePacket = new DataPacket<ILeaveRoomMsg>(ILeaveRoomMsg.class, getMember(), new LeaveRoomMsg(member));
		sendLeaveMessage(leavePacket);
		
		ClientController.getInstance().getAppView().removeChatroom();
		
	}
}
