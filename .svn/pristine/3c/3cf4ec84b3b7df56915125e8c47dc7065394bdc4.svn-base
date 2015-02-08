package dw35_wz23.server.model;

import common.IRequestCmd;
/**
 * Request command class
 * @author dw35, wz23
 *
 */
public class Requestcmd implements IRequestCmd{
	/**
	 * the index of the command
	 */
	private Class<?> index;
	/**
	 * the serial version ID
	 */
	private static final long serialVersionUID = 8157158011831000001L;
	/**
	 * Constructor the command
	 * @param index of command
	 */
	public Requestcmd(Class<?> index){
		this.index = index;
	}
	/**
	 * get the command ID
	 */
	@Override
	public Class<?> getID() {
		return index;
	}

}
