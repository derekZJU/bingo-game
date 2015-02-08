package dw35_wz23.client.chatroom.view;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dw35_wz23.client.controller.ClientController;
/**
 * view panel for the chatroom
 * @author dw35,wz23
 *
 */
public class ChatroomView extends JPanel{
	
	private static final long serialVersionUID = 3335681412100562629L;
	public JList<String> memberList;
	public DefaultListModel<String> memberListModel;
	private JTextField inputField;
	private JButton inputButton;
	private JButton LeaveChatroomButton;
	private JScrollPane displayPanel;
	private JLabel lblChatRoom;
	private JTextArea textArea;
	private IChatView2ModelAdapter chatModel;
	/**
	 * constructor of the view
	 * @param iChatView2ModelAdapter	adapter from view to model
	 */
	public ChatroomView(IChatView2ModelAdapter iChatView2ModelAdapter) {	
		chatModel = iChatView2ModelAdapter;
		init();
	}
	/**
	 * initial operations when starting the mini GUI
	 */
	public void init(){
		setLayout(new BorderLayout(0, 0));	
		/*South*/
		JPanel memberListPanel = new JPanel();		
		memberListPanel.setLayout(new BorderLayout(0, 0));	
		memberList = new JList<String>();
		memberList.setToolTipText("chat room member list");
		memberListModel = new DefaultListModel<String>();
		memberList.setModel(memberListModel);
		memberListPanel.add(memberList, BorderLayout.CENTER);		
		JLabel chatroomMemberLabel = new JLabel("Chatroom Member");
		chatroomMemberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		memberListPanel.add(chatroomMemberLabel, BorderLayout.SOUTH);
		
		/*North*/
		JPanel toolPanel = new JPanel();
		toolPanel.setLayout(new BorderLayout(0, 0));		
		lblChatRoom = new JLabel("Chat Room");
		lblChatRoom.setToolTipText("chatroom name");
		lblChatRoom.setHorizontalAlignment(SwingConstants.CENTER);
		toolPanel.add(lblChatRoom, BorderLayout.CENTER);		
		LeaveChatroomButton = new JButton("Leave Chatroom");
		LeaveChatroomButton.setToolTipText("button for leaving chatroom");
		toolPanel.add(LeaveChatroomButton, BorderLayout.EAST);
		LeaveChatroomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatModel.getChatroom().removeMember(chatModel.getMember());
				chatModel.sendLeaveMessage();
				ClientController.getInstance().getAppModel().removeChatroom(chatModel.getChatroom());
				ClientController.getInstance().getAppView().removeChatroom();
			}
		});
		
		/*East*/
		JPanel inputPanel = new JPanel();		
		inputPanel.setLayout(new BorderLayout(0, 0));		
		inputField = new JTextField();
		inputField.setToolTipText("input textfield ");
		inputField.setColumns(10);
		inputPanel.add(inputField);		
		inputButton = new JButton();
		inputButton.setToolTipText("button for sending text");
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chatModel.sendTextMessage(inputField.getText());
			}
		});
		inputButton.setText("send");
		inputPanel.add(inputButton, BorderLayout.EAST);
		
		/*Center*/
		displayPanel = new JScrollPane();
		textArea = new JTextArea();
		textArea.setToolTipText("chatting window");
		displayPanel.setViewportView(textArea);
		
		add(inputPanel, BorderLayout.SOUTH);
		add(toolPanel, BorderLayout.NORTH);
		add(memberListPanel, BorderLayout.EAST);
		add(displayPanel, BorderLayout.CENTER);
		
	}
	/**
	 * starting operation of the view
	 */
	public void start() {
		setVisible(true);
	}
	/**
	 * set the name of the chatroom
	 */
	public void setName(String newName){
		lblChatRoom.setText(newName);
	}
	/**
	 * get the member list of the model
	 * @return	return the model of the member list
	 */
	public DefaultListModel<String> getMemberListModel(){
		return memberListModel;
	}
	/**
	 * getter of the textArea
	 * @return	the textArea
	 */
	public JTextArea getTextArea(){
		return textArea;
	}
}
