package examples.missionariesVScannibals;

import java.util.LinkedList;
import java.util.List;

import utility.Action;
import utility.State;
import utility.StateADT;

public class RiverState extends StateADT {
	
	private int nMissLeft, nCannLeft, nMissRight, nCannRight;
	private boolean left;
	private State previous;

	@Override
	public void updateState(Action a) {
		//TODO
		if (! (a instanceof TransportationAction)){
			System.err.println("Type Error");
			System.exit(-1);
		}
		TransportationAction ta = (TransportationAction) a;
		
		if (left){
			nMissLeft -= ta.getNumMissionaries();
			nCannLeft -= ta.getNumCannibals();
			nMissRight += ta.getNumMissionaries();
			nCannRight += ta.getNumCannibals();
		}
		else{
			nMissLeft += ta.getNumMissionaries();
			nCannLeft += ta.getNumCannibals();
			nMissRight -= ta.getNumMissionaries();
			nCannRight -= ta.getNumCannibals();
		}
		
		left = left? false : true;
	}

	@Override
	public State getPreviousInPath() {
		return previous;
	}

	@Override
	public void setPreviousInPath(State s) {
		previous = s;
	}

	@Override
	public boolean isSolution() {
		return nMissLeft==0 && nCannLeft==0 && !left;
	}

	@Override
	public boolean isFailure() {
		return (nMissLeft>0 && nCannLeft>nMissLeft) || (nMissRight>0 && nCannRight>nMissRight) || nMissLeft<0 || nCannLeft<0 || nMissRight<0 || nCannRight<0;
	}

	@Override
	public List<Action> getActions() {
		// we add all actions because the failure method detects also unlegal actions
		List<Action> list = new LinkedList<Action>();
		Action one = new TransportationAction(2,0);
		list.add(one);
		Action two = new TransportationAction(1, 0);
		list.add(two);
		Action three = new TransportationAction(1, 1);
		list.add(three);
		Action four = new TransportationAction(0, 1);
		list.add(four);
		Action five = new TransportationAction(0, 2);
		list.add(five);
		return list;
	}

	@Override
	public void setInitialState() {
		nMissLeft = 3;
		nCannLeft = 3;
		nMissRight = 0;
		nCannRight = 0;
		left = true;
		previous = null;
	}

	@Override
	public State deepCopy() {
		RiverState novel = new RiverState();
		novel.nMissLeft = this.nMissLeft;
		novel.nCannLeft = this.nCannLeft;
		novel.nMissRight = this.nMissRight;
		novel.nCannRight = this.nCannRight;
		novel.left = this.left;
		novel.previous = this.previous;
		return novel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (! (o instanceof RiverState)) return false;
		RiverState s = (RiverState) o;
		return nMissLeft==s.nMissLeft && nCannLeft==s.nCannLeft && nMissRight==s.nMissRight && nCannRight==s.nCannRight && left==s.left;
	}
	
	public String toString(){
		// every State is able to print the full path till itself
		StringBuilder sb = new StringBuilder();
		RiverState tmp = this;
		do{
			sb.append(" - C").append(tmp.nCannLeft).append(",M").append(tmp.nMissLeft);
			tmp = (RiverState) tmp.getPreviousInPath();
		}
		while (tmp != null);
		
		return sb.reverse().toString();
	}

}
