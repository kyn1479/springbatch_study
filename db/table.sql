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

CREATE TABLE `batch_info` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `create_time` datetime DEFAULT NULL,
                              `update_time` datetime DEFAULT NULL,
                              `acct_no` varchar(255) DEFAULT NULL,
                              `batch_no` varchar(255) DEFAULT NULL,
                              `batch_status` varchar(255) DEFAULT NULL,
                              `inst_code` varchar(255) DEFAULT NULL,
                              `inst_req_no` varchar(255) DEFAULT NULL,
                              `inst_resp_code` varchar(255) DEFAULT NULL,
                              `inst_resp_msg` varchar(255) DEFAULT NULL,
                              `inst_resp_no` varchar(255) DEFAULT NULL,
                              `total_amount` decimal(19,2) DEFAULT NULL,
                              `total_num` bigint(20) DEFAULT NULL,
                              `total_suc_amount` decimal(19,2) DEFAULT NULL,
                              `total_suc_num` bigint(20) DEFAULT NULL,
                              `trans_code` varchar(255) DEFAULT NULL,
                              `trans_date` datetime DEFAULT NULL,
                              `trans_date_time` datetime DEFAULT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `deduct_trans` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `create_time` datetime DEFAULT NULL,
                                `update_time` datetime DEFAULT NULL,
                                `acct_no` varchar(255) DEFAULT NULL,
                                `batch_no` varchar(255) DEFAULT NULL,
                                `inst_code` varchar(255) DEFAULT NULL,
                                `inst_req_no` varchar(255) DEFAULT NULL,
                                `process_status` varchar(255) DEFAULT NULL,
                                `trans_amount` decimal(19,2) DEFAULT NULL,
                                `trans_date` datetime DEFAULT NULL,
                                `trans_date_time` datetime DEFAULT NULL,
                                `trans_no` varchar(255) DEFAULT NULL,
                                `trans_status` varchar(255) DEFAULT NULL,
                                `trans_type` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;


-- 清除表测试数据
DELETE FROM batch_job_seq;
DELETE FROM batch_step_execution_seq;
DELETE FROM batch_job_execution_seq;
DELETE FROM batch_job_execution_context;
DELETE FROM batch_job_execution_params;
DELETE FROM batch_step_execution_context;
DELETE FROM batch_step_execution;
DELETE FROM batch_job_execution;
DELETE FROM batch_job_instance;
