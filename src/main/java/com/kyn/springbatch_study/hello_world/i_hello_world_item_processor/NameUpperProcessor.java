package com.kyn.springbatch_study.hello_world.i_hello_world_item_processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


/**
 * @author Kangyanan
 * @Description:
 * @date 2021/2/23
 */
@Component("nameUpperProcessor")
public class NameUpperProcessor implements ItemProcessor<Boy,Boy> {

    @Override
    public Boy process(Boy item) throws Exception {
        Boy boy =new Boy();
        boy.setId(item.getId());
        boy.setName(item.getName().toUpperCase());
        boy.setSex(item.getSex());
        return boy;
    }
}
