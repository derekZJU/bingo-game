package dw35_wz23.server.game.controller;

import java.io.Serializable;

import map.MapLayer;
import dw35_wz23.server.game.model.GameModel;
import dw35_wz23.server.game.model.IGameModel2ViewAdapter;
import dw35_wz23.server.game.view.GameView;
import dw35_wz23.server.game.view.IGameView2ModelAdapter;
/**
 * controller of the game
 * @author dw35, wz23
 *
 */
public class GameController implements Serializable{

	private static final long serialVersionUID = -7564654839492567184L;
	private GameModel model;
	private GameView view;
	/**
	 * getter of the game model
	 * @return game model
	 */
	public GameModel getModel() {
		return model;
	}
	/**
	 * getter of the game view
	 * @return game view
	 */
	public GameView getView() {
		return view;
	}
	/**
	 * constructor of the game controller
	 */
	public GameController(){
		model = new GameModel(new IGameModel2ViewAdapter(){

			/**
			 * 
			 */
			private static final long serialVersionUID = -1259202315488676767L;

			@Override
			public void show(MapLayer layer) {
				
			}

			@Override
			public void hide(MapLayer layer) {
				
			}

			@Override
			public void updateLabelArray(int[] matrix) {
				view.updateLabelArray(matrix);
				
			}

			@Override
			public void setTime(String timeString) {
				view.setTime(timeString);
				
			}
			
		});
		view = new GameView(new IGameView2ModelAdapter(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7211817522998472659L;

			@Override
			public void sendUpdate(int l) {
				model.sendUpdate(l);
			}

		});
	}
	/**
	 * start of the game
	 */
	public void start() {
		model.start();
		view.start();
	}
}
