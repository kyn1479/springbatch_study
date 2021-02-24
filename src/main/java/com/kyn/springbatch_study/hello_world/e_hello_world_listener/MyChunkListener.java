package com.kyn.springbatch_study.hello_world.e_hello_world_listener;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;

/**
 * @author Kangyanan
 * @Description: 注解的方式实现Chunk级别的监听
 * @date 2021/2/22
 */
public class MyChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext context){
        System.out.println(context.getStepContext().getStepName()+ "  beforeChunk...");
    }

    @AfterChunk
    public void afterChunk(ChunkContext context){
        System.out.println(context.getStepContext().getStepName()+ "   afterChunk...");
    }
}
