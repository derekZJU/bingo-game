package dw35_wz23.server.model;

import java.io.Serializable;

/**
 * update matrix  Message implements Serializable to update matrix
 * @author dw35, wz23
 *
 */
public class UpdateMatrixMsg implements Serializable{
	private static final long serialVersionUID = 5027016097642879379L;
	int teamID;
	int dishNumber;
	
	/**
	 * the constructor of UpdateMatrixMsg
	 * @param team   the index number of team
	 * @param dishNum the index number of dish
	 */
	public UpdateMatrixMsg(int team, int dishNum){
		teamID = team;
		dishNumber = dishNum;
	}
	
	/**
	 * get the index number of dish
	 * @return the index number of dish
	 */
	public int getDishNumber() {
		return dishNumber;
	}

	/**
	 * set the index number of dish
	 * @param dishNumber  the index number of dish
	 */
	public void setDishNumber(int dishNumber) {
		this.dishNumber = dishNumber;
	}

	/**
	 * get the index number of team
	 * @return team ID
	 */
	public int getTeamID() {
		return teamID;
	}

	/**
	 *  set the index number of team
	 * @param teamID team ID
	 */
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
}
