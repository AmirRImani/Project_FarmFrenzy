package vehicles;

public abstract class Vehicle {
    protected int capacity;
    protected int highestLevel;
    protected int currentLevel;

    protected abstract void transport();
}
