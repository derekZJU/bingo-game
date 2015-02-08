package dw35_wz23.client.controller;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.JOptionPane;

import common.IChatroom;
import common.IPerson;
import dw35_wz23.client.model.ClientModel;
import dw35_wz23.client.model.IModel2ViewAdapter;
import dw35_wz23.client.view.ClientView;
import dw35_wz23.client.view.IView2ModelAdapter;
import dw35_wz23.client.chatroom.controller.ChatroomController;

/**
 * Controller for the chat app system.
 * @author dw35,wz23
 *
 */
public class ClientController {
	
	/**
	 * the object of app controller
	 */
	private static ClientController instance = new ClientController();
	/**
	 * get the instance of controller
	 * @return instance of controller
	 */
	public static ClientController getInstance() {
		return instance;
	}	
	
	/**
	 * The model of the ChatApp.
	 */
	private ClientModel model;
	
	private UUID uuID;
	public UUID getUuID() {
		return uuID;
	}


	public void setUuID(UUID uuID) {
		this.uuID = uuID;
	}

	/**
	 * The view of the ChatApp.
	 */
	private ClientView view;
	
	
	/**
	 * Construct the controller.
	 */
	private ClientController() {
		model = new ClientModel(new IModel2ViewAdapter() {
			/**
			 * add chat room to list
			 */
			@Override
			public void addChatroomToList(IChatroom chatroom) {
				view.addChatroomToList(chatroom);
			}
		});
		this.uuID = model.getUuID();
		view = new ClientView(new IView2ModelAdapter() {
			/**
			 * send request
			 */
			@Override
			public int sendRequest(String ip) {
				return model.sendRequest(ip);
			}
			/**
			 * create chat room
			 */
			@Override
			public void createChatroom() {
				model.createChatroom();
			}
			/**
			 * get the arraylist of chat room
			 */
			@Override
			public ArrayList<ChatroomController> getChatroomList() {
				return model.getChatroomList();
			}
			/**
			 * add chat room
			 */
			@Override
			public ChatroomController addChatroom(IChatroom chatroom) {
				return model.addChatroom(chatroom);
				
			}
			/**
			 * send invitation
			 */
			@Override
			public void sendInvitation(String IP, IChatroom chatroom) {
				IPerson newPersonStub = model.getPersonStub(IP);
				model.sendInvitation(newPersonStub, chatroom);
				
			}
		});
	}
	
	
	/**
	 * Start the controller.
	 */
	public void start() {
		String name = JOptionPane.showInputDialog("Please input your userName");
		if(name == null || name =="")
			name = "Anonymous User";
		model.setUsername(name);
		view.setUsername(name);
		view.setLocalIP(model.getLocalIP());
		model.start();
		view.start();
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientController.getInstance().start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * get the app model
	 * @return app model
	 */
	public ClientModel getAppModel() {
		return model;
	}
	/**
	 * get the app view
	 * @return app view
	 */
	public ClientView getAppView() {
		return view;
	}

}
