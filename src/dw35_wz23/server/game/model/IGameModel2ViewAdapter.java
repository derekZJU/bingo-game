package dw35_wz23.server.game.model;

import java.io.Serializable;

import map.MapLayer;
/**
 * interface of the game model to game ivew adapter
 * @author dw35, wz23
 *
 */
public interface IGameModel2ViewAdapter extends Serializable{
	public void show(MapLayer layer);
	public void hide(MapLayer layer);
	public void updateLabelArray(int[] matrix);
	public void setTime(String timeString);
}
