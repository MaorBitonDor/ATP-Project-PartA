package algorithms.search;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class CostComparator implements Comparator<AState> {

    /**
     * @param o1 - the first state we want to compare
     * @param o2 - the second state we want to compare
     * @return - the subtract of the first state cost from the second state cost
     */
    @Override
    public int compare(AState o1, AState o2) {
        return (int) (o1.getCost()-o2.getCost());
    }

    //the rest we dont need so we dont implement them.
    @Override
    public Comparator<AState> reversed() {
        return null;
    }

    @Override
    public Comparator<AState> thenComparing(Comparator<? super AState> other) {
        return null;
    }

    @Override
    public <U> Comparator<AState> thenComparing(Function<? super AState, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return null;
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<AState> thenComparing(Function<? super AState, ? extends U> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<AState> thenComparingInt(ToIntFunction<? super AState> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<AState> thenComparingLong(ToLongFunction<? super AState> keyExtractor) {
        return null;
    }

    @Override
    public Comparator<AState> thenComparingDouble(ToDoubleFunction<? super AState> keyExtractor) {
        return null;
    }
}
