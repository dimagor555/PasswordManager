package ru.dimagor555.usecase;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MultiThreadMainExecutor implements Executor {
    private static final int CORES = Runtime.getRuntime().availableProcessors();
    private final Executor executor = Executors.newFixedThreadPool(CORES);

    @Override
    public void execute(Runnable command) {
        executor.execute(command);
    }
}
