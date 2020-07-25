package com.cinder.im.protocol.util;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:31 2020/7/19/019
 * @Modified By:
 */
public class ThreadPoolUtil {


    private static ThreadPoolExecutor threadPoolExecutor;

    static{
        threadPoolExecutor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*2,
                Runtime.getRuntime().availableProcessors()*2, 60, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(100),new MyThreadFactory());

    }
    public static void execute(Runnable command){
        threadPoolExecutor.execute(command);
    }

    public static <T> Future<T> submit(Callable<T> task){
        return threadPoolExecutor.submit(task);
    }

    public static void shutdown(){
        if (threadPoolExecutor != null) {
            threadPoolExecutor.shutdown();
        }
    }
    private static class MyThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        MyThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "cinderPool-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }
}
