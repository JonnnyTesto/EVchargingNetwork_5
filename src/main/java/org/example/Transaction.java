package org.example;

import java.time.LocalDateTime;


public abstract class Transaction {
    private String id;
    private LocalDateTime dateTime;

    public Transaction(String id, LocalDateTime dateTime) {
        this.id = id;
        this.dateTime = dateTime;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }


    public abstract double getAmount();
}