package com.kyn.springbatch_study.hello_world.j_hello_world_partitioner.utils;

import org.springframework.batch.core.scope.context.ChunkContext;

import java.util.Map;

/**
 * @author Kangyanan
 * @Description:  spring batch批处理工具类
 * @date 2021/2/26
 */
public class TaskletUtil {

     /**
       * @Description 从批处理任务上下文中获取string参数
       *
       * @Params
       * @Return
       * @Exceptions
       */
    public static String getParamValue(ChunkContext chunkContext, String key){
        Object paramValue = chunkContext.getStepContext().getJobParameters().get(key);
        return paramValue==null ?"":paramValue.toString();
    }

    /**
     * @Description 从批处理任务上下文中获取参数
     *
     * @Params
     * @Return
     * @Exceptions
     */
    public static Object getObjectValue(ChunkContext chunkContext, String key){
        Object paramValue = chunkContext.getStepContext().getJobParameters().get(key);
        return paramValue;
    }


    /**
     * @Description 从批处理任务上下文中获取参数
     * @Params
     * @Return
     * @Exceptions
     */
    public static Object getValueFromStepExecutionContext(ChunkContext chunkContext, String key) {
        Map<String, Object>  objectMap = chunkContext.getStepContext().getStepExecutionContext();
        if(objectMap != null && objectMap.size() > 0) {
            return objectMap.get(key);
        }
        return null;
    }


     /**
      * @Description 设置上下文参数值
      *
      * @Params
      * @Return
      * @Exceptions
      */
    public static void setParamValue(ChunkContext chunkContext, String key, String value){
        chunkContext.getStepContext().getJobParameters().put(key, value);
    }


}
