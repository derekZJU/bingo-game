package dw35_wz23.client.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dw35_wz23.client.controller.ClientController;
import common.IChatroom;
import common.IPerson;
/**
 * The view of chat app MVC
 * @author dw35, wz23
 *
 */
public class ClientView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4695904962210911457L;
	private JPanel contentPane;
	private JTextField IPInputText;

	private JList<String> chatroomList;
	private DefaultListModel<String> chatroomListModel;
	public JPanel chatroomPanel;
	/**
	 * The adapter of the model.
	 */
	private IView2ModelAdapter model;
	private JLabel UserNameLabel;
	private JLabel IPLabel;


	/**
	 * Create the frame.
	 */
	public ClientView(IView2ModelAdapter modelAdapter) {
		this.model = modelAdapter;
		initGUI();
	}

	
	/**
	 * Initialize components in the GUI.
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				
			}
			
		});
		setBounds(100, 100, 650, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel chatListPanel = new JPanel();
		chatListPanel.setBackground(Color.WHITE);
		contentPane.add(chatListPanel, BorderLayout.WEST);
		
		chatroomList = new JList<String>();
		chatroomList.setBorder(null);
		chatroomListModel = new DefaultListModel<String>();
		chatroomList.setModel(chatroomListModel);
		chatroomList.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int selIndex = chatroomList.getSelectedIndex();
				changeChatroomPanel(selIndex);
			}
		});
		chatListPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane(chatroomList);
		scrollPane.setViewportBorder(null);
		chatListPanel.add(scrollPane);
		
		JLabel chatListLabel = new JLabel("Team List");
		chatListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		chatListPanel.add(chatListLabel, BorderLayout.SOUTH);
		
		JPanel toolBar = new JPanel();
		toolBar.setLayout(new BorderLayout(0, 0));
		
		JPanel infoPanel = new JPanel();
		toolBar.add(infoPanel, BorderLayout.NORTH);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setForeground(Color.RED);
		infoPanel.add(lblUsername);
		
		UserNameLabel = new JLabel("Anonymous User");
		infoPanel.add(UserNameLabel);
		
		JLabel label = new JLabel("                ");
		infoPanel.add(label);
		
		JLabel lblUserip = new JLabel("ClientIP: ");
		lblUserip.setForeground(Color.RED);
		infoPanel.add(lblUserip);
		
		IPLabel = new JLabel("Default IP");
		infoPanel.add(IPLabel);
		
		JPanel toolPanel = new JPanel();
		toolBar.add(toolPanel, BorderLayout.SOUTH);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JLabel lblUserIp = new JLabel("Server IP:");
		toolPanel.add(lblUserIp);
		
		IPInputText = new JTextField();
		IPInputText.setToolTipText("Enter the IP address of another member");
		toolPanel.add(IPInputText);
		IPInputText.setColumns(10);
		
		JButton btnRequest = new JButton("Request");
		btnRequest.setToolTipText("Request to join the target IP's chatroom");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int status = model.sendRequest(IPInputText.getText());
				if (IPerson.STATUS_FAILED == status) {
					System.err.println("Send request failed.");
				}
			}
		});
		toolPanel.add(btnRequest);
		
		
		chatroomPanel = new JPanel();
		contentPane.add(chatroomPanel, BorderLayout.CENTER);
	}
	
	/**
	 * Start the GUI.
	 */
	public void start() {
		setVisible(true);
	}
	/**
	 * add chat room to the list
	 * @param chatroom char room object
	 */
	public void addChatroomToList(IChatroom chatroom) {
		chatroomListModel.addElement(chatroom.getName());
	}
	/**
	 * leave chat room
	 * @param index chat room index
	 */
	
	public void removeChatroom(int index) {
		chatroomListModel.removeElementAt(index);
	}
	/**
	 * change chat room panel
	 * @param selIndex chat room index
	 */
	public void changeChatroomPanel(int selIndex){
		contentPane.remove(chatroomPanel);
//		String newName = "chatroom" + selIndex;
		String newName = ClientController.getInstance().getAppModel().getChatroomList().get(selIndex).getMiniModel().getChatroom().getName();
		chatroomPanel = model.getChatroomList().get(selIndex).getChatroomView();
		chatroomPanel.setName(newName);
		contentPane.add(chatroomPanel, BorderLayout.CENTER);
		this.setContentPane(contentPane);
	}
	/**
	 * set the user name
	 * @param name user name
	 */
	public void setUsername(String name){
		UserNameLabel.setText(name);
	}
	/**
	 * set the user IP
	 * @param IP user IP
	 */
	public void setLocalIP(String IP){
		IPLabel.setText(IP);
	}
	
	
	/**
	 * Remove the selected chat room.
	 */
	public void removeChatroom() {
		
		int sel = chatroomList.getSelectedIndex();
		try {
			chatroomListModel.remove(sel);
		} catch (Exception e) {}
		
		int size = chatroomListModel.getSize();
		if (size>0){
			chatroomList.setSelectedIndex(sel >= size ? size - 1:sel);
		}
		contentPane.repaint();
		
	}
}
