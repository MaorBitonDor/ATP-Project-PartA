package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    ArrayList<AState> getAllSuccessors(AState curState);
    AState getGoalState();
    AState getStartState();
}
