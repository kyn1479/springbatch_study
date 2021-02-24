package com.kyn.springbatch_study.hello_world.i_hello_world_item_processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/23
 */
@Component("idFilterProcessor")
public class IdFilterProcessor implements ItemProcessor<Boy, Boy> {

    @Override
    public Boy process(Boy boy) throws Exception {
        if(boy.getId()%2==1)
            return boy;
        else
            return null;
    }
}
