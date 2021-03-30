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

    @Override
    public Solution solve(ISearchable dom) {
        Stack<AState> stack = new Stack<>();
        HashSet<AState> visited = new HashSet<>();
        ArrayList<AState> possibleStates;
        AState curState,root = dom.getStartState(), goal = dom.getGoalState();
        stack.push(root);
        visited.add(root);
        while(!stack.empty()){
            curState = stack.pop();
            if(curState.equals(goal))
            {
                return new Solution(curState);
            }
            if(!visited.contains(curState)){
                visited.add(curState);
                possibleStates = dom.getAllPossibleStates(curState);
                for (AState possibleState : possibleStates){
                    stack.push(possibleState);
                }
            }
        }
        return null;
    }
}
