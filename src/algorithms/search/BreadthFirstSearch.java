package algorithms.search;

import java.util.*;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    protected Queue<AState> queue;

    /**
     * this is the constructor of the Breadth First Search algorithm that extends the abstract class ASearchingAlgorithm
     */
    public BreadthFirstSearch() {
        super("Breadth First Search");
        this.queue = new LinkedList<>();
    }

    /**
     * this function uses BFS to solve the problem we face generically (like a maze for example).
     * @param dom - is a searchable object that can be searched by BFS.
     * @return a Solution class that has the path from the start state to the goal state and the total
     * cost of this path.
     * @throws Exception - from the function getAllSuccessors.
     */
    @Override
    public Solution solve(ISearchable dom) throws Exception {
        if(dom == null)
            return new Solution();
        this.queue.clear();
        HashSet<AState> visited = new HashSet<>();
        ArrayList<AState> possibleStates;
        AState curState,root = dom.getStartState(), goal = dom.getGoalState();
        queue.add(root);
        visited.add(root);
        while(queue.size() > 0){
            curState = queue.poll();
            incNumberOfNodesEvaluated();
            if(curState.equals(goal)){
                return new Solution(curState);
            }
            possibleStates = dom.getAllSuccessors(curState);
            for (AState possibleState : possibleStates) {
                if (!visited.contains(possibleState)) {
                    possibleState.setPrevState(curState);
                    possibleState.setCost(possibleState.getCost());
                    visited.add(possibleState);
                    queue.add(possibleState);
                }
            }
        }
        //no solution found by the algorithm
        return new Solution();
    }
}
