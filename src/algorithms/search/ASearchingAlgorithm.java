package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private String Name;
    private int EvalNodesNum;

    public ASearchingAlgorithm(String name) {
        Name = name;
        EvalNodesNum = 0;
    }

    @Override
    public String getName() {
        return Name;
    }

    public int getNumberOfNodesEvaluated() {
        return EvalNodesNum;
    }

    public void setEvalNodesNum(int evalNodesNum) {
        EvalNodesNum = evalNodesNum;
    }
}
