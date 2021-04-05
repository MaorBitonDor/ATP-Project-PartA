package algorithms.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    /**
     * this is the constructor of the Best First Search algorithm that extends the abstract class ASearchingAlgorithm
     */
    public BestFirstSearch() {
        super();
        setName("Best First Search");
        this.queue = new PriorityQueue<>(1,new CostComparator());
    }


    @Override
    public Solution solve(ISearchable dom) {
        return super.solve(dom);
    }
}
