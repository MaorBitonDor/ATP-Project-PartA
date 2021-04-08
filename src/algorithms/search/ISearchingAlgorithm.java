package algorithms.search;

public interface ISearchingAlgorithm {
    String getName();
    Solution solve(ISearchable dom) throws Exception;
    int getNumberOfNodesEvaluated();
}
