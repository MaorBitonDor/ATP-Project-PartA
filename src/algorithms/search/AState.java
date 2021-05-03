package algorithms.search;

import java.util.Objects;

public abstract class AState implements Comparable{
    private final String State;
    private double cost;
    private AState prevState;


    public AState() {
        State="";
    }

    /**
     * this is the constructor of the abstract class.
     * @param state - a String represent the current state that we are in.
     */
    public AState(String state) {
        State = state;
        prevState = null;
        cost = 0;
    }

    /**
     * @return a string that represent the current state that we are in.
     */
    public String getState() {
        return State;
    }

    /**
     * @return a double that represent the cost of moving from the previous state to the current state.
     */
    public double getCost() {
        return cost;
    }

    /**
     * @return the previous state we were in.
     */
    public AState getPrevState() {
        return prevState;
    }

    /**
     * @param cost - the cost of passing from the previous state to this state.
     */
    public void setCost(double cost) {
        if(prevState != null)
            this.cost = prevState.getCost()+cost;
        else
            this.cost = cost;
    }

    /**
     * @param prevState - the previous state we were in before moving to this state.
     */
    public void setPrevState(AState prevState) {
        this.prevState = prevState;
    }

    @Override
    public int compareTo(Object o) {
        AState other = (AState) o;
        return (int) (this.cost- other.getCost());
    }

    /**
     * @param o - the object we want to check if is equal to this object.
     * @return a boolean value that say if they are equals or not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AState aState = (AState) o;
        return Objects.equals(State, aState.State);
    }

    /**
     * @return an int for a hash map
     */
    @Override
    public int hashCode() {
        return Objects.hash(State);
    }
}
