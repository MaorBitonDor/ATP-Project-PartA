package algorithms.search;

import java.util.*;

public class BestFirstSearch extends ASearchingAlgorithm{
    private Queue<AState> queue;
    /**
     * this is the constructor of the Best First Search algorithm that extends the abstract class ASearchingAlgorithm
     */
    public BestFirstSearch() {
        super("Best First Search");
        this.queue = new PriorityQueue<>();
    }

    /**
     * this function uses Best First Search algorithm to solve the problem we face generically
     * (like a maze for example).
     * @param dom - is a searchable object that can be searched by Best First Search algorithm.
     * @return a Solution class that has the path from the start state to the goal state and the total
     * cost of this path
     * @throws Exception - from the function getAllSuccessors.
     */
    @Override
    public Solution solve(ISearchable dom) throws Exception {
        if(dom == null)
            return new Solution();
        this.queue.clear();
        HashSet<AState> visited = new HashSet<>();
        HashMap<String,AState> visitedStatesHash = new HashMap<>();
        ArrayList<AState> possibleStates;
        AState curState,root = dom.getStartState(), goal = dom.getGoalState();
        queue.add(root);
        visited.add(root);
        visitedStatesHash.put(root.getState(), root);
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
                    visitedStatesHash.put(possibleState.getState(),possibleState);
                    queue.add(possibleState);
                }
                //check if the old cost is higher the the new one
                else {
                    AState tempState = visitedStatesHash.get(possibleState.getState());
                    if(possibleState.getCost()+curState.getCost()<tempState.getCost()){
                        tempState.setPrevState(curState);
                        tempState.setCost(possibleState.getCost());
                    }
                }
            }
        }
        //no solution found by the algorithm
        return new Solution();
    }
}
