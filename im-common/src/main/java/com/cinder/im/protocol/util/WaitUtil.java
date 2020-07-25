package com.cinder.im.protocol.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Cinder
 * @Description:
 * @Date create in 21:47 2020/7/19/019
 * @Modified By:
 */
public class WaitUtil {
    /**
     * 得到响应后继续操作
     */
    private static CyclicBarrier WAITER = new CyclicBarrier(2);

    public static void waitResponse() {
        try {
            WAITER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
    public static void responseFinished(){
        try {
            WAITER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
