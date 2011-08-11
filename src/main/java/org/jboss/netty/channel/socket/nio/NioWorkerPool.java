package org.jboss.netty.channel.socket.nio;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: timfox
 * Date: 20/07/2011
 * Time: 12:39
 */
public class NioWorkerPool {
  private final NioWorker[] workers;
  private final AtomicInteger workerIndex = new AtomicInteger();

  public NioWorkerPool(int workerCount, Executor executor) {
    workers = new NioWorker[workerCount];
    for (int i = 0; i < workers.length; i ++) {
        workers[i] = new NioWorker(executor);
        workers[i].start();
    }
  }

  NioWorker nextWorker() {
    return workers[Math.abs(workerIndex.getAndIncrement() % workers.length)];
  }

}