package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Solution implements Serializable {
    private ArrayList<AState> SolutionPath;
    private double solutionCost;

    /**
     * this is an empty constructor that just initiate a new ArrayList to SolutionPath
     */
    public Solution() {
        SolutionPath = new ArrayList<>();
        solutionCost = 0;
    }

    /**
     * this is a constructor that gets the goal state and retrace the path from the start state
     * to the goal state and insert it to SolutionPath.
     * @param goal - this is the final state we want to be in - the solution state.
     */
    public Solution(AState goal){
        SolutionPath = new ArrayList<>();
        if(goal == null)
            solutionCost = 0;
        else {
            Stack<AState> stack = new Stack<>();
            this.solutionCost = goal.getCost();
            AState curState = goal;
            while(curState.getPrevState() != null){
                stack.push(curState);
                curState = curState.getPrevState();
            }
            stack.push(curState);
            while(!stack.empty()){
                SolutionPath.add(stack.pop());
            }
        }
    }

    /**
     * @return an ArrayList that contains the solution path from the starting state to the goal state.
     */
    public ArrayList<AState> getSolutionPath() {
        return SolutionPath;
    }

    /**
     * @return return the cost of all the path from the start state to the goal state.
     */
    public double getSolutionCost() {
        return solutionCost;
    }
}
