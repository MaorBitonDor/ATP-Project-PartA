package algorithms.search;

import java.util.Objects;

public abstract class AState {
    private final String State;
    private double cost;
    private AState prevState;

    public AState(String state) {
        State = state;
    }

    public String getState() {
        return State;
    }

    public double getCost() {
        return cost;
    }

    public AState getPrevState() {
        return prevState;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setPrevState(AState prevState) {
        this.prevState = prevState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return Objects.equals(State, aState.State);
    }

    @Override
    public int hashCode() {
        return Objects.hash(State);
    }
}
