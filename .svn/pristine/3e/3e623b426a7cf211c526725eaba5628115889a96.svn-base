package dw35_wz23.server.model;

import java.io.Serializable;
import javax.swing.JComponent;
/**
 * Add Component Message implements Serializable to add component
 * @author dw35, wz23
 *
 */
public class AddComponentMsg implements Serializable{

	private static final long serialVersionUID = -3303553408306238534L;
	JComponent targetComponent;
	String componentName;
	
	/**
	 * constructor of AddComponentMsg
	 * @param componentName
	 * @param targetComponent
	 */
	public AddComponentMsg(String componentName, JComponent targetComponent){
		this.componentName = componentName;
		this.targetComponent = targetComponent;
	}
	
	/**
	 * getter of  the component name
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}
	/**
	 * setter of the component name
	 * @param componentName 
	 *                      the component name
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	
	/**
	 * get the target component
	 * @return target component
	 */
	public JComponent getTargetComponent() {
		return targetComponent;
	}
	/**
	 * set the target component
	 * @param targetComponent 
	 *                        the target component
	 */
	public void setTargetComponent(JComponent targetComponent) {
		this.targetComponent = targetComponent;
	}
}
