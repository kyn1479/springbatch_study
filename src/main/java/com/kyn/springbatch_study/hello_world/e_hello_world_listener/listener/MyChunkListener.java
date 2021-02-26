package com.kyn.springbatch_study.hello_world.e_hello_world_listener.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description: 注解的方式实现Chunk级别的监听
 * @date 2021/2/22
 */
@Component
public class MyChunkListener {
    private static final Logger logger = LoggerFactory.getLogger(MyChunkListener.class);
    @BeforeChunk
    public void beforeChunk(ChunkContext context){
        logger.info("进入--MyChunkListener--beforeChunk");
//        System.out.println(context.getStepContext().getStepName()+ "  beforeChunk...");
    }

    @AfterChunk
    public void afterChunk(ChunkContext context){
        logger.info("进入--MyChunkListener--afterChunk");
//        System.out.println(context.getStepContext().getStepName()+ "   afterChunk...");
    }
}
