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
        incNumberOfNodesEvaluated();
        while(queue.size() > 0){
            curState = queue.poll();
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
                    incNumberOfNodesEvaluated();
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
