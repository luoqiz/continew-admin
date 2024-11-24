CREATE TABLE `wms_whse_addr`  (
                                  `id` 							bigint(20) 		NOT NULL 										COMMENT 'id编号',
                                  `whse_no` 				varchar(100) 	NOT NULL 								 		COMMENT '仓库编号',
                                  `name` 						varchar(24) 	NOT NULL 								 		COMMENT '仓库名称',
                                  `addr` 						varchar(48) 	NULL 										 		COMMENT '仓库地址',
                                  `whse_type`				tinyint(1)  	NULL 				DEFAULT NULL 		COMMENT '仓库类型 (1国仓  2地仓  3店仓)',
                                  `status` 					tinyint(0) 		NOT NULL 		DEFAULT 1 			COMMENT '状态 (1使用  2停用)',
                                  `memo` 						varchar(1000) NULL				DEFAULT ''			COMMENT '备注信息',
                                  `dept_id` 				bigint(20) 		NULL				DEFAULT NULL		COMMENT '所属部门',
                                  `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                  `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                  `update_user`    	bigint(20)   					                    COMMENT '修改人',
                                  `update_time`    	datetime     					                    COMMENT '修改时间',
                                  PRIMARY KEY (`id`),
                                  INDEX `whse_no`(`whse_no`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='仓库地址表';

CREATE TABLE `wms_goods_sku`  (
                                  `id` 							bigint(20) 		NOT NULL 										COMMENT '编号',
                                  `barcode` 				varchar(120)  NOT NULL				 				 		COMMENT '条形码',
                                  `spu_id` 					bigint(0) 		NULL 										 		COMMENT '商品编号',
                                  `name` 						varchar(48) 	NOT NULL 								 		COMMENT '规格名称',
                                  `unit` 						varchar(48) 	NOT NULL 		 								COMMENT '单位',
                                  `amount` 					int 					NOT NULL		DEFAULT 1 		 	COMMENT '数量',
                                  `unpacking` 			bool					NOT NULL 		DEFAULT b'0' 		COMMENT '拆箱',
                                  `pack_unit`				varchar(48)		NULL 										 		COMMENT '拆箱单位',
                                  `pack_amount`			int(0)				NULL 										 		COMMENT '拆箱数量',
                                  `sell_point` 			varchar(100) 	NULL 				DEFAULT ''	 		COMMENT '卖点',
                                  `specs` 					json 					NULL 												COMMENT '规格列表',
                                  `price` 					decimal(10,2) NULL 										 		COMMENT '售价',
                                  `img` 						varchar(120)  NULL 										 		COMMENT '首图',
                                  `pics` 						varchar(255)  NULL 										 		COMMENT '图片列表',
                                  `status` 					tinyint(0) 		NOT NULL 		DEFAULT 1 			COMMENT '状态 1上架  2下架',
                                  `memo` 						varchar(3000) NULL				DEFAULT ''			COMMENT '备注信息',
                                  `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                  `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                  `update_user`    	bigint(20)   	      				              COMMENT '修改人',
                                  `update_time`    	datetime     				        	            COMMENT '修改时间',
                                  PRIMARY KEY (`id`),
                                  INDEX `wms_goods_sku_barcode`(`barcode`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = '物料规格表';


CREATE TABLE `wms_whse_stock_in`  (
                                      `id` 							bigint(20) 		NOT NULL 										COMMENT 'id编号',
                                      `name` 						varchar(100) 	NOT NULL 								 		COMMENT '入库名称',
                                      `stock_in_no` 		varchar(100) 	NOT NULL 								 		COMMENT '入库单号',
                                      `stock_in_type` 	int(0) 				NOT NULL 		DEFAULT 1 			COMMENT '入库类型(1 采购入库 2移库入库)',
                                      `whse_id` 				bigint(20) 		NOT NULL 								 		COMMENT '仓库id编号',
                                      `whse_area_id`		bigint(20) 		NULL								 		 		COMMENT '仓库区域id编号',
                                      `stock_move_id`		bigint(20)	 	NULL								 		 		COMMENT '关联移库单号',
                                      `in_time` 				datetime		 	NULL		 								 		COMMENT '入库时间',
                                      `status` 					tinyint(0) 		NOT NULL 		DEFAULT 1 			COMMENT '状态 (1审核中 2待入库 3已完成)',
                                      `memo` 						varchar(3000) NULL				DEFAULT ''			COMMENT '备注信息',
                                      `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                      `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                      `update_user`    	bigint(20)   					                    COMMENT '修改人',
                                      `update_time`    	datetime     					                    COMMENT '修改时间',
                                      PRIMARY KEY (`id`) ,
                                      INDEX `wms_whse_stock_in_no`(`stock_in_no`)
) ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = '仓库入库表';

CREATE TABLE `wms_whse_stock_in_deatil`  (
                                             `id` 							bigint(20) 		NOT NULL 										COMMENT 'id编号',
                                             `stock_in_id` 		bigint(20) 		NOT NULL 								 		COMMENT '入库单号',
                                             `goods_sku` 			varchar(100) 	NOT NULL 								 		COMMENT '商品sku',
                                             `goods_name` 			varchar(100) 	NOT NULL 								 		COMMENT '商品名称',
                                             `prod_time`		 		datetime 			NOT NULL 								 		COMMENT '生产日期',
                                             `expiry_time`		 	datetime 			NOT NULL 								 		COMMENT '过期日期',
                                             `plan_num` 				int					 	NOT NULL								 		COMMENT '计划入库数量',
                                             `real_num` 				int(48) 			NULL 										 		COMMENT '实际入库数量',
                                             `memo` 						varchar(3000) NULL				DEFAULT ''			COMMENT '备注信息',
                                             `status` 					tinyint(0) 		NULL 				DEFAULT 1 			COMMENT '状态 (1待核检 2核检通过)',
                                             `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                             `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                             `update_user`    	bigint(20)   					                    COMMENT '修改人',
                                             `update_time`    	datetime     					                    COMMENT '修改时间',
                                             PRIMARY KEY (`id`),
                                             INDEX `whse_stock_in_deatil_stockInId`(`stock_in_id`),
                                             INDEX `whse_stock_in_deatil_goodsSku`(`goods_sku`)
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '仓库入库明细表';


CREATE TABLE `wms_goods_stock`  (
                                    `id` 							bigint(20) 		NOT NULL 										COMMENT '编号',
                                    `stock_in_id`			bigint(20) 		NOT NULL 										COMMENT '入库单编号',
                                    `stock_in_no` 		varchar(100) 	NOT NULL 								 		COMMENT '入库单号',
                                    `stock_in_detail_id`		bigint(20) 		NOT NULL 										COMMENT '入库单明细编号',
                                    `goods_id` 				bigint(20) 		NULL 								 		COMMENT '物料编号',
                                    `goods_sku` 			varchar(100)	NOT NULL 		 						 		COMMENT '物料sku条码',
                                    `init_num`		 		int(0)				NOT NULL 								 		COMMENT '初始库存',
                                    `real_num` 				int(0)				NOT NULL 								 		COMMENT '实际库存',
                                    `whse_type`				tinyint				NOT NULL 										COMMENT '仓库类型',
                                    `whse_id`					bigint(20)  	NOT NULL				 				 		COMMENT '仓库id',
                                    `whse_area_id`		varchar(100) 	NULL										 		COMMENT '仓库区域id编号',
                                    `status` 					tinyint(0) 		NOT NULL 		DEFAULT 1 			COMMENT '状态 1待出库 2已出库 3部分出库',
                                    `prod_time`		 		datetime 			NOT NULL 								 		COMMENT '生产日期',
                                    `expiry_time`		 	datetime 			NOT NULL 								 		COMMENT '过期日期',
                                    `info` 						varchar(1000) NULL				DEFAULT ''			COMMENT '备注信息',
                                    `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                    `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                    `update_user`    	bigint(20)   	        				            COMMENT '修改人',
                                    `update_time`    	datetime     					                    COMMENT '修改时间',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `wms_goods_stock_goodsSku`(`goods_sku`) USING BTREE,
                                    INDEX `wms_goods_stock_stockInNo`(`stock_in_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '物料库存表';

CREATE TABLE `wms_whse_stock_out`  (
                                       `id` 							bigint(20) 		NOT NULL 										COMMENT 'id编号',
                                       `name` 						varchar(100) 	NOT NULL 								 		COMMENT '出库名称',
                                       `stock_out_no` 		varchar(100) 	NOT NULL 								 		COMMENT '出库单号',
                                       `stock_out_type` 	int(0) 				NOT NULL 		DEFAULT 1 			COMMENT '出库类型(1 销售出库 2移库出库)',
                                       `whse_id` 				varchar(100) 	NOT NULL 								 		COMMENT '仓库id编号',
                                       `whse_area_id`		varchar(100) 	NULL									  		COMMENT '仓库区域id编号',
                                       `stock_move_id`		bigint(20)	 	NULL								 		 		COMMENT '关联移库单号',
                                       `out_time` 				datetime		 	NULL		 								 		COMMENT '出库时间',
                                       `status` 					tinyint(0) 		NOT NULL 		DEFAULT 1 			COMMENT '状态 (1审核中 2操作中 3已完成)',
                                       `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                       `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                       `update_user`    	bigint(20)   					                    COMMENT '修改人',
                                       `update_time`    	datetime     					                    COMMENT '修改时间',
                                       PRIMARY KEY (`id`) USING BTREE,
                                       INDEX `wms_whse_stock_out_stockOutNo`(`stock_out_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库出库表';


CREATE TABLE `wms_whse_stock_out_detail`  (
                                              `id` 							bigint(20) 		NOT NULL 										COMMENT 'id编号',
                                              `stock_out_id` 		bigint(20) 		NOT NULL 								 		COMMENT '出库单号',
                                              `goods_stock_id` 	bigint(20) 		NOT NULL 								 		COMMENT '库存id',
                                              `goods_sku` 			varchar(100) 	NOT NULL 						 				COMMENT '商品sku',
                                              `goods_name` 			varchar(100) 	NOT NULL 										COMMENT '商品名称',
                                              `prod_time`		 		datetime 			NOT NULL 								 		COMMENT '生产日期',
                                              `expiry_time`		 	datetime 			NOT NULL 								 		COMMENT '过期日期',
                                              `plan_num` 				int					 	NOT NULL								 		COMMENT '计划出库数量',
                                              `real_num` 				int(48) 			NULL 										 		COMMENT '实际出库数量',
                                              `memo` 						varchar(3000) NULL				DEFAULT ''			COMMENT '备注信息',
                                              `status` 					tinyint(0) 		NULL 				DEFAULT 1 			COMMENT '状态 (1待核检 2核检通过)',
                                              `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                              `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                              `update_user`    	bigint(20)   					                    COMMENT '修改人',
                                              `update_time`    	datetime     					                    COMMENT '修改时间',
                                              PRIMARY KEY (`id`) USING BTREE,
                                              INDEX `wms_whse_stock_out_deatil_stockOutId`(`stock_out_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库出库明细表';


CREATE TABLE `wms_whse_stock_move`  (
                                        `id` 							bigint(20) 		NOT NULL 										COMMENT 'id编号',
                                        `name` 						varchar(100) 	NOT NULL 										COMMENT '移库名称',
                                        `stock_move_no` 	varchar(100) 	NOT NULL 								 		COMMENT '移库单号',
                                        `stock_move_type` int(0) 				NOT NULL 		DEFAULT 1 			COMMENT '出库类型(1国-国 2国-地 3地-地 4地-店 5店-店)',
                                        `stock_in_whse_id` 				bigint(20) 			NOT NULL 								 		COMMENT '入仓id编号',
                                        `stock_in_whse_area_id`		varchar(100) 		NULL		 								 		COMMENT '入仓区域id编号',
                                        `stock_out_whse_id` 			bigint(20) 			NOT NULL 								 		COMMENT '出仓id编号',
                                        `stock_out_whse_area_id` 	varchar(100)  	NULL 								 				COMMENT '出库区域id编号',
                                        `move_time` 			datetime		 	NULL		 								 		COMMENT '移库时间',
                                        `status` 					tinyint(0) 		NOT NULL 		DEFAULT 1 			COMMENT '状态 (1审核中 2操作中 2已完成)',
                                        `stock_in_id`			bigint(20)	 	NULL								 		 		COMMENT '关联入库单号',
                                        `stock_out_id`		bigint(20)	 	NULL								 		 		COMMENT '关联出库单号',
                                        `memo` 						varchar(3000) NULL				DEFAULT ''			COMMENT '备注信息',
                                        `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                        `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                        `update_user`    	bigint(20)   					                    COMMENT '修改人',
                                        `update_time`    	datetime     					                    COMMENT '修改时间',
                                        PRIMARY KEY (`id`),
                                        INDEX `wms_whse_stock_move_stockInWhseId`(`stock_in_whse_id`),
                                        INDEX `wms_whse_stock_move_stockOutWhseId`(`stock_out_whse_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库移库表';



CREATE TABLE `wms_whse_stock_move_detial`  (
                                               `id` 							bigint(20) 		NOT NULL 										COMMENT 'id编号',
                                               `stock_move_no` 	varchar(100) 	NOT NULL 								 		COMMENT '移库单号',
                                               `goods_stock_id` 	bigint(20) 		NOT NULL 								 		COMMENT '库存id',
                                               `goods_sku` 			varchar(100) 	NOT NULL 								 		COMMENT '商品sku',
                                               `goods_name` 			varchar(100) 	NOT NULL 								 		COMMENT '商品名称',
                                               `move_time` 			datetime		 	NOT NULL 								 		COMMENT '移库时间',
                                               `prod_time`		 		datetime 			NOT NULL 								 		COMMENT '生产日期',
                                               `expiry_time`		 	datetime 			NOT NULL 								 		COMMENT '过期日期',
                                               `plan_num` 				int					 	NOT NULL								 		COMMENT '计划数量',
                                               `real_num` 				int(48) 			NULL 										 		COMMENT '实际数量',
                                               `memo` 						varchar(3000) NULL				DEFAULT ''			COMMENT '备注信息',
                                               `create_user`    	bigint(20)   	NOT NULL                    COMMENT '创建人',
                                               `create_time`    	datetime     	NOT NULL                    COMMENT '创建时间',
                                               `update_user`    	bigint(20)   						                  COMMENT '修改人',
                                               `update_time`    	datetime     						                  COMMENT '修改时间',
                                               PRIMARY KEY (`id`),
                                               INDEX `wms_whse_stock_move_detial_goodsSku`(`goods_sku`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '仓库移库明细表';
