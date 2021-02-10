package ru.dimagor555.usecase;

import java.util.concurrent.Executor;

public class Interactor {
    private Executor mainExecutor;
    private Executor postExecutor;

    public void buildInteractor(Executor main, Executor post) {
        this.mainExecutor = main;
        this.postExecutor = post;
    }
    
    protected void executeMain(Runnable command) {
        mainExecutor.execute(command);
    } 
    
    protected void executePost(Runnable command) {
        postExecutor.execute(command);
    }
}
