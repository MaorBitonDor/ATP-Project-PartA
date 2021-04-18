package algorithms.search;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    private String Name;
    private int EvalNodesNum;

    /**
     * this is the constructor of the abstract class ASearchingAlgorithm
     * @param name - a string that represent the searching algorithm name
     */
    public ASearchingAlgorithm(String name) {
        Name = name;
        EvalNodesNum = 0;
    }

    /**
     * @return the name of the searching algorithm
     */
    @Override
    public String getName() {
        return Name;
    }

    /**
     * @return the current number of visited nodes by the algorithm
     */
    public int getNumberOfNodesEvaluated() {
        return EvalNodesNum;
    }

    /**
     * this function allow to change the name of the algorithm for any class that inherit this
     * abstract class
     * @param name - the new name of the algorithm
     */
    protected void setName(String name) {
        Name = name;
    }

    /**
     * this function increase the number of evaluated nodes by 1
     */
    public void incNumberOfNodesEvaluated() {
        EvalNodesNum++;
    }
}
