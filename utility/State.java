package utility;
import java.util.List;

/**
 * 
 */

/**
 * @author Pietro Grandinetti
 *
 */
public interface State {

	boolean isContained(State s); // done in StateADT
	
	State executeAction (Action a); // done in StateADT
	
	void updateState(Action a);
	
	State getPreviousInPath();
	
	void setPreviousInPath(State s);
	
	boolean isSolution();
	
	boolean isFailure();
	
	List<Action> getActions();
	
	void setInitialState();
	
	State deepCopy();
	
	//String printState();
}
