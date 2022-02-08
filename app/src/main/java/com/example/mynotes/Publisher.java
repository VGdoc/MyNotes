package com.example.mynotes;

import java.util.ArrayList;
import java.util.List;

public class Publisher {

    private List<Observer> observers;   // Все подписчики (Fragment1, Fragment2)

    public Publisher() {
        observers = new ArrayList<>();
    }

    // Подписать кого-то на событие, то есть сохранить в список с подписчиками
    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    // Отписать
    public void unsubscribe(Observer observer) {
        observers.remove(observer);
    }

    // Разослать событие по подписчикам (изменение в тексте)
    public void notify(String text) {
        if (text.equals(MainActivity.REFRESH_NOTIFICATION)){
            for (Observer observer : observers) {
                observer.refresh();
            }
        }

    }
}
