-- liquibase formatted sql

-- changeset charles7c:1
-- comment 初始化代码生成插件数据表
-- 初始化表结构
CREATE TABLE "public"."gen_config"
(
    "table_name"     varchar(64)  NOT NULL,
    "entity_name"    varchar(60)  NOT NULL,
    "list_type"      int4         NOT NULL,
    "tree_id"        varchar(60),
    "tree_pid"       varchar(60),
    "tree_label"     varchar(60),
    "module_name"    varchar(60)  NOT NULL,
    "business_name"  varchar(50)  NOT NULL,
    "package_name"   varchar(60)  NOT NULL,
    "author"         varchar(100) NOT NULL,
    "table_prefix"   varchar(20),
    "is_override"    bool         NOT NULL,
    "dialog_type"    int4,
    "parent_menu_id" int8,
    "front_path"     varchar(255),
    "create_time"    timestamp(6) NOT NULL,
    "update_time"    timestamp(6),
    PRIMARY KEY ("table_name")
)
;

COMMENT
ON COLUMN "public"."gen_config"."table_name" IS '表名称';

COMMENT
ON COLUMN "public"."gen_config"."entity_name" IS '实体名称';

COMMENT
ON COLUMN "public"."gen_config"."list_type" IS '列表类型（1 表格列表 2树状列表）';

COMMENT
ON COLUMN "public"."gen_config"."tree_id" IS '树编码';

COMMENT
ON COLUMN "public"."gen_config"."tree_pid" IS '树父编码';

COMMENT
ON COLUMN "public"."gen_config"."tree_label" IS '树名称';

COMMENT
ON COLUMN "public"."gen_config"."module_name" IS '模块名称';

COMMENT
ON COLUMN "public"."gen_config"."business_name" IS '业务名称';

COMMENT
ON COLUMN "public"."gen_config"."package_name" IS '包名称';

COMMENT
ON COLUMN "public"."gen_config"."author" IS '作者';

COMMENT
ON COLUMN "public"."gen_config"."table_prefix" IS '表前缀';

COMMENT
ON COLUMN "public"."gen_config"."is_override" IS '是否覆盖';

COMMENT
ON COLUMN "public"."gen_config"."dialog_type" IS '弹窗类型（1 modal弹窗 2 drawer抽屉）';

COMMENT
ON COLUMN "public"."gen_config"."parent_menu_id" IS '父级菜单id';

COMMENT
ON COLUMN "public"."gen_config"."front_path" IS '前端项目路径';

COMMENT
ON COLUMN "public"."gen_config"."create_time" IS '创建时间';

COMMENT
ON COLUMN "public"."gen_config"."update_time" IS '修改时间';

COMMENT
ON TABLE "public"."gen_config" IS '生成配置表';

CREATE TABLE "public"."gen_field_config"
(
    "id"            int8         NOT NULL,
    "table_name"    varchar(64)  NOT NULL,
    "column_name"   varchar(64)  NOT NULL,
    "column_type"   varchar(25)  NOT NULL,
    "column_size"   int8,
    "field_name"    varchar(64)  NOT NULL,
    "field_type"    varchar(25)  NOT NULL,
    "field_sort"    int4         NOT NULL,
    "comment"       varchar(512),
    "is_required"   bool         NOT NULL,
    "show_in_list"  bool         NOT NULL,
    "show_in_form"  bool         NOT NULL,
    "show_in_query" bool         NOT NULL,
    "form_type"     int2,
    "query_type"    int2,
    "dict_code"     varchar(30),
    "create_time"   timestamp(6) NOT NULL,
    PRIMARY KEY ("id")
)
;

CREATE INDEX "idx_table_name_copy1" ON "public"."gen_field_config" USING btree (
    "table_name" "pg_catalog"."text_ops" ASC NULLS LAST
    );

COMMENT
ON COLUMN "public"."gen_field_config"."id" IS 'ID';

COMMENT
ON COLUMN "public"."gen_field_config"."table_name" IS '表名称';

COMMENT
ON COLUMN "public"."gen_field_config"."column_name" IS '列名称';

COMMENT
ON COLUMN "public"."gen_field_config"."column_type" IS '列类型';

COMMENT
ON COLUMN "public"."gen_field_config"."column_size" IS '列大小';

COMMENT
ON COLUMN "public"."gen_field_config"."field_name" IS '字段名称';

COMMENT
ON COLUMN "public"."gen_field_config"."field_type" IS '字段类型';

COMMENT
ON COLUMN "public"."gen_field_config"."field_sort" IS '字段排序';

COMMENT
ON COLUMN "public"."gen_field_config"."comment" IS '注释';

COMMENT
ON COLUMN "public"."gen_field_config"."is_required" IS '是否必填';

COMMENT
ON COLUMN "public"."gen_field_config"."show_in_list" IS '是否在列表中显示';

COMMENT
ON COLUMN "public"."gen_field_config"."show_in_form" IS '是否在表单中显示';

COMMENT
ON COLUMN "public"."gen_field_config"."show_in_query" IS '是否在查询中显示';

COMMENT
ON COLUMN "public"."gen_field_config"."form_type" IS '表单类型';

COMMENT
ON COLUMN "public"."gen_field_config"."query_type" IS '查询方式';

COMMENT
ON COLUMN "public"."gen_field_config"."dict_code" IS '字典编码';

COMMENT
ON COLUMN "public"."gen_field_config"."create_time" IS '创建时间';

COMMENT
ON TABLE "public"."gen_field_config" IS '字段配置表';
        
-- 初始化默认菜单
INSERT INTO "sys_menu"
("id", "title", "parent_id", "type", "path", "name", "component", "redirect", "icon", "is_external", "is_cache", "is_hidden", "permission", "sort", "status", "create_user", "create_time")
VALUES
(9000, '开发工具', 0, 1, '/code', 'Code', 'Layout', '/code/generator', 'code-release-managment', false, false, false, NULL, 9, 1, 1, NOW()),
(9010, '代码生成', 9000, 2, '/code/generator', 'CodeGenerator', 'code/generator/index', NULL, 'code', false, false, false, NULL, 1, 1, 1, NOW()),
(9011, '列表', 9010, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'code:generator:list', 1, 1, 1, NOW()),
(9012, '配置', 9010, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'code:generator:config', 2, 1, 1, NOW()),
(9013, '预览', 9010, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'code:generator:preview', 3, 1, 1, NOW()),
(9014, '生成', 9010, 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'code:generator:generate', 4, 1, 1, NOW());