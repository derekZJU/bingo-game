package dw35_wz23.server.view;

import javax.swing.JDialog;

import java.awt.BorderLayout;
import java.awt.Component;
/**	
 * Component class that implements JDialog
 * @author dw35, wz23
 *
 */
public class ComponentShowCaseDlg extends JDialog {
		
	/**
	 * serial ID
	 */
	private static final long serialVersionUID = -326582489731056181L;
	/**
	 * Constructor of class
	 * @param senderName sender name
	 * @param c component object
	 */
	public ComponentShowCaseDlg(String senderName, Component c) {
		getContentPane().add(c, BorderLayout.CENTER);		
		setBounds(200, 200, c.getWidth(), c.getHeight());
		setTitle("Message from: " + senderName);
		
	}
	
}