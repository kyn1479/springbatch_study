package com.kyn.springbatch_study.hello_world.g_hello_world_item_reader.skip;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/25
 */
public class MySkipPolicy implements SkipPolicy {
    @Override
    public boolean shouldSkip(Throwable throwable, int i) throws SkipLimitExceededException {
        return false;
    }
}
