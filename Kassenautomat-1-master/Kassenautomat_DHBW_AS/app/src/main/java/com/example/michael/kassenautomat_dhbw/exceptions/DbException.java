package com.example.michael.kassenautomat_dhbw.exceptions;

/**
 * Created by Michael on 07.04.2016.
 */
public class DbException extends Exception {

    public DbException(String msg) {
        super(msg);
    }

    public DbException() {
        super();
    }
}
