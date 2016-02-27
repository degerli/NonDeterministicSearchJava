/**
 * 
 */
package examples.missionariesVScannibals;

import utility.Action;

/**
 * @author Pietro Grandinetti
 *
 */
public class TransportationAction implements Action {
	
	private int [] action;
	
	public TransportationAction(int nMiss, int nCann){
		action = new int[2];
		action[0] = nMiss;
		action[1] = nCann;
	}
	
	public int getNumMissionaries(){
		return action[0];
	}
	
	public int getNumCannibals(){
		return action[1];
	}
	
	public String toString(){
		return action[0]+"M,"+action[1]+"C";
	}

}
