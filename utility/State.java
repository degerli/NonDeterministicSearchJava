package utility;
import java.util.List;


/**
 * Interface of State representation for a tree-like search towards leafs that are 
 * solutions.
 * 
 * @author Pietro Grandinetti
 *
 */
public interface State {

	/**
	 * For a tree-like search with non determinism avoiding loops is necessary.
	 * So every State needs to tell whether it is contained in the path represented by
	 * another State
	 * 
	 * @param s The path to be compared against
	 * @return true is this state is contained in the path from s to the root.
	 */
	boolean isContained(State s); // done in StateADT
	
	/**
	 * When this state receive an action produced a new state, updated with the
	 * actions effects. The new state has a pointer to this State, as his previous in path 
	 * 
	 * @param a The action to execute
	 * @return A new state updated with the given action and with the same path until this state.
	 */
	State executeAction (Action a); // done in StateADT
	
	/**
	 * This method has to update this State internally according to prescribed action.
	 * 
	 * @param a The action to execute.
	 */
	void updateState(Action a);
	
	/**
	 * For the purpose of the search, a reference to the State which precedes this State in
	 * the path has to be stored and accessible.
	 * 
	 * @return The State preceding this in the path towards the solution.
	 */
	State getPreviousInPath();
	
	/**
	 * 
	 * @param s The State preceding this in the path towards the solution.
	 */
	void setPreviousInPath(State s);
	
	/**
	 * 
	 * @return True is this state is a correct solution to the search problem.
	 */
	boolean isSolution();
	
	/**
	 * 
	 * @return True is this state is inadmissible for the search purpose (e.g., is invalid)
	 */
	boolean isFailure();
	
	/**
	 * 
	 * @return All actions available in this state, according to the specific problem definition
	 */
	List<Action> getActions();
	
	/**
	 * This method set this State as root. It must be executed before to start a new search.
	 * 
	 */
	void setInitialState();
	
	/**
	 * 
	 * @return An exact copy of this State, but with different memory address.
	 */
	State deepCopy();
	
}
