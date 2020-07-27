package com.tools.streamTask;

import lombok.Synchronized;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xcc
 * @2019/12/14 14:55
 * 文件说明 流式任务处理
 */
@Component
public abstract class StreamTaskExec<T> {

    List<T> taskParam;
    List<Object> result = new ArrayList<>();
    Boolean lock=true;

    public List<Object> run(List<T> taskParam, int poolSize) {
        this.taskParam = taskParam;
        CompletableFuture[] cfs = taskParam.stream()
                .map(data -> CompletableFuture.supplyAsync(() -> task(data), this.getExecutor(poolSize))
                        .thenApply(h -> h)
                        .whenComplete((s, e) -> {
                            if (s != null) {
                                addResult(s);
                            }
                        })
                ).toArray(CompletableFuture[]::new);
        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.anyOf(cfs).join();
        return result;
    }

    /**
     * @param obj
     * @return
     */
    abstract  Object task(Object obj);

    public ExecutorService getExecutor(int pool) {
        return Executors.newFixedThreadPool(10);
    }

    public synchronized void addResult(Object obj){
        result.add(obj);
    }

}
