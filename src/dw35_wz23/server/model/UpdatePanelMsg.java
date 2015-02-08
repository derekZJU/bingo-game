package dw35_wz23.server.model;

import java.io.Serializable;

/**
 * update panel  Message implements Serializable to update panel
 * @author dw35, wz23
 *
 */
public class UpdatePanelMsg implements Serializable{

	private static final long serialVersionUID = 6904128614705504363L;
	int[] matrix;
	
	/**
	 * the constructor of UpdatePanelMsg
	 * @param m matrix array
	 */
	public UpdatePanelMsg(int[] m){
		matrix = m;
	}

	/**
	 * get matrix
	 * @return matrix array
	 */
	public int[] getMatrix() {
		return matrix;
	}

	/**
	 * set matrix
	 * @param matrix matrix array
	 */
	public void setMatrix(int[] matrix) {
		this.matrix = matrix;
	}
	
}
