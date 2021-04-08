package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    ArrayList<AState> getAllSuccessors(AState curState) throws Exception;
    AState getGoalState();
    AState getStartState();
}
