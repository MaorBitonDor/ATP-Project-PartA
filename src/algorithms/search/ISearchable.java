package algorithms.search;

import java.util.ArrayList;

public interface ISearchable {
    ArrayList<AState> getAllPossibleStates(AState curState);
    AState getGoalState();
    AState getStartState();
}
