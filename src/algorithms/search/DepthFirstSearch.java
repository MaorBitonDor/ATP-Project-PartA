package algorithms.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    /**
     * this is the constructor of the Depth First Search algorithm that extends the abstract class ASearchingAlgorithm
     */
    public DepthFirstSearch() {
        super("Depth First Search");
    }

    /**
     * this function uses DFS to solve the problem we face generically (like a maze for example).
     * @param dom - is a searchable object that can be searched by DFS.
     * @return a Solution class that has the path from the start state to the goal state.
     */
    @Override
    public Solution solve(ISearchable dom) throws Exception {
        if(dom == null)
            return new Solution();
        Stack<AState> stack = new Stack<>();
        HashSet<AState> visited = new HashSet<>();
        ArrayList<AState> possibleStates;
        AState curState,root = dom.getStartState(), goal = dom.getGoalState();
        stack.push(root);
        while(!stack.empty()){
            curState = stack.pop();
            if(curState.equals(goal))
                return new Solution(curState);
            if(!visited.contains(curState)){
                visited.add(curState);
                incNumberOfNodesEvaluated();
                possibleStates = dom.getAllSuccessors(curState);
                for (AState possibleState : possibleStates){
                    possibleState.setPrevState(curState);
                    possibleState.setCost(possibleState.getCost());
                    stack.push(possibleState);
                }
            }
        }
        //no solution found by the algorithm
        return new Solution();
    }
}
