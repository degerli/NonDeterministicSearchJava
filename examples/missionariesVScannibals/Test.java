/**
 * 
 */
package examples.missionariesVScannibals;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import utility.NonDeterministicSearch;
import utility.State;

/**
 * @author Pietro Grandinetti
 *
 */
public class Test {

	public static void main(String args[]){
		State start = new RiverState();

		// this object will contain information about the results found
		StringBuilder sb = new StringBuilder();
		
		NonDeterministicSearch search = new NonDeterministicSearch();

		// by default 1000 executions
		int numRuns = args.length > 0 ? Integer.parseInt(args[0]) : 1000;

		State solution = null;

		for (int i=0; i<numRuns; i++){
			// set root
			start.setInitialState();
			// solve the problem with non deterministic search
			solution = search.solve(start);

			// print information on the screen and into the StringBuilder
			System.out.println("SOLUTION = ");
			System.out.println(solution.toString());
			sb.append("RUN #").append(i+1).append("\n").append(solution.toString()).append("\n\n");
		}

		PrintWriter pw = null;

		// Print all paths to a file
		try{
			pw = new PrintWriter("solutions","UTF-8");
			pw.println("# Paths are described by the state (num. Missionaries, num. Cannibals) on the left side at every step");
			pw.println();
			pw.println(sb.toString());
		}catch(IOException e){
			System.out.println(e);
		}finally{
			pw.close();
		}

		// Compute statistics and print it on a file
		Map<String, Integer> stats = new HashMap<String, Integer>();
		String [] lines = sb.toString().split("\n");
		for (String line : lines){
			if ( (! line.equals("")) && (! line.startsWith("RUN")) ){
				System.out.println(line);
				if (stats.get(line)!=null)
					stats.put(line, stats.get(line)+1);
				else
					stats.put(line,1);
			}
		}
		System.out.println(stats.toString());

		StringBuilder sb2 = new StringBuilder();

		int i = 1;
		for (Map.Entry<String,Integer> entry : stats.entrySet() ){
			sb2.append("PATH ").append(i).append(": ");
			sb2.append(entry.getKey()).append("\n");
			sb2.append("Occurrence: ").append(entry.getValue()).append(" over ").append(numRuns).append(" runs\n\n");
			i++;
		}

		try{
			pw = new PrintWriter("statistics","UTF-8");
			pw.println("# Paths are described by the state (num. Missionaries, num. Cannibals) on the left side at every step");
			pw.println();
			pw.println(sb2.toString());
		}catch(IOException e){
			System.out.println(e);
		}finally{
			pw.close();
		}

	}
}

