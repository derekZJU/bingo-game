package dw35_wz23.server.game.view;

import java.io.Serializable;
/**
 * interface for game view to model adapter
 * @author dw35, wz23
 *
 */
public interface IGameView2ModelAdapter extends Serializable{
	/**
	 * send the update of the matrix
	 * @param l index of the dish
	 */
	void sendUpdate(int l);
}
