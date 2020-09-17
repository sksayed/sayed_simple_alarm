package com.sayed.sayedsimplealarm.utilities;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class AppExecutor {
    public Executor IOExecutor;
    public Executor normalExecutor;
    public MainThreadExecutor mainThreadExecutor;
    private static volatile AppExecutor appExecutor;

    private AppExecutor() {
        IOExecutor = Executors.newFixedThreadPool(StringUtilities.NUMBER_OF_THREADS);
        normalExecutor = Executors.newSingleThreadExecutor();
        mainThreadExecutor = new MainThreadExecutor();
    }

    public static AppExecutor getInstance() {
        if (appExecutor == null) {
            synchronized (AppExecutor.class) {
                if (appExecutor == null) {
                    appExecutor = new AppExecutor();
                }
            }
        }
        return appExecutor;
    }

    private static class MainThreadExecutor implements Executor {
        private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable command) {
            mainThreadHandler.post(command);
        }
    }

}
