CREATE TABLE `boy` (
                       `id` bigint(20) NOT NULL AUTO_INCREMENT,
                       `name` varchar(255) DEFAULT NULL,
                       `sex` tinyint(4) DEFAULT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;


CREATE TABLE `customer` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `firstName` varchar(255) DEFAULT NULL,
                            `lastName` varchar(255) DEFAULT NULL,
                            `birthday` varchar(255) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

--清除表测试数据
DELETE FROM batch_job_seq;
DELETE FROM batch_step_execution_seq;
DELETE FROM batch_job_execution_seq;
DELETE FROM batch_job_execution_context;
DELETE FROM batch_job_execution_params;
DELETE FROM batch_step_execution_context;
DELETE FROM batch_step_execution;
DELETE FROM batch_job_execution;
DELETE FROM batch_job_instance;
