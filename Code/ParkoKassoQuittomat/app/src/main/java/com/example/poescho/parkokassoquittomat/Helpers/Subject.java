package com.example.poescho.parkokassoquittomat.Helpers;

import com.example.poescho.parkokassoquittomat.Parkscheinausgabe.Parkschein;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Poescho on 17.11.2016.
 */

public abstract class Subject {
    private final List<Observer> observerList = new ArrayList();

    public void register(Observer newObserver){
        observerList.add(newObserver);
    }

    public void unregister(Observer newObserver){
        observerList.remove(newObserver);
    }

    public void notifyObservers(Parkschein parkschein){
        for (Observer observer : observerList) {
            System.out.println("notifyObservers");
            observer.update(parkschein);
        }
    }
}
