package edu.umb.cs681.hw01;

import java.util.LinkedList;

public abstract class Observable{
    protected boolean changed;
    protected LinkedList<Observer> observer = new LinkedList<Observer>();

    public void addObserver(Observer o){
        if (!observer.contains(o)){
            observer.add(o);
        }
    }

    public void deleteObserver(Observer o){
        if (observer.contains(o)){
            observer.remove(o);
        }
    }

    protected boolean hasChanged(){
        return changed;
    }

    protected  int countObserver(){
        return  observer.size();
    }

    protected void setChanged() {
        changed = true;
    }

    protected void clearChanged() {
        changed = false;
    }

    public void notifyObservers(Object object) {
        if (hasChanged()) {
            observer.forEach((Observer observer) -> observer.update(this, object));
            clearChanged();
        }
    }

}