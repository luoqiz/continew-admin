<script setup lang="ts">
    import type {VxeTableGridOptions} from '#/adapter/vxe-table';
    import type {${classNamePrefix}Resp, ${classNamePrefix}Query} from '#/api/${apiModuleName}/${apiName}';


    import {ref} from 'vue';

    import {Page, useVbenDrawer, useVbenModal} from '@vben/common-ui';
    import {$t} from '@vben/locales';

    import {ElButton, ElMessage, ElPopconfirm, ElSpace} from 'element-plus';

    import {useVbenVxeGrid} from '#/adapter/vxe-table';
    import {
        delete${classNamePrefix},
        export${classNamePrefix},
        list${classNamePrefix}
    } from '#/api/${apiModuleName}/${apiName}'
    import {useDownload} from '#/hooks/app/useDownload';

    import {
        use${classNamePrefix}GridFieldColumns,
        use${classNamePrefix}GridSearchFormSchema
    } from './${classNamePrefix}Data';

    <#if dialogType==1>
    import ${classNamePrefix}EditDrawer from './${classNamePrefix}EditDrawer.vue';

    <#else >
    import ${classNamePrefix}EditModal from './${classNamePrefix}EditModal.vue';

    </#if>
    <#if hasDictField>
    import {useDict} from '#/hooks/app'

    const {<#list dictCodes as dictCode>${dictCode}<#if dictCode_has_next>, </#if></#list>} = useDict(<#list dictCodes as dictCode>'${dictCode}'<#if dictCode_has_next>, </#if></#list>)
    </#if>

    const [TableGrid, tableGridApi] = useVbenVxeGrid({
        formOptions: {
            schema: use${classNamePrefix}GridSearchFormSchema(),
            submitOnChange: true,
            showCollapseButton: false,
            wrapperClass: 'grid-cols-1 md:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4',
        },
        gridOptions: {
            columns: use${classNamePrefix}GridFieldColumns(),
            border: true,
            height: 'auto',
            keepSource: true,
            columnConfig: {
                resizable: true,
            },
            <#if listType== 2>
            pagerConfig: {
                enabled: false,
            },
            treeConfig: {
                rowField: 'id',
                parentField: 'parentId',
                childrenField: 'children',
                transform: false,
            },
            </#if>
            proxyConfig: {
                response: {
                    list: 'list',
                },
                ajax: {
                    query: async ({page}, formValues) => {
                        const res = await list${classNamePrefix}({
                            page: page.currentPage,
                            size: page.pageSize,
                            ...formValues,
                        });
                        <#if listType== 2>
                        return {list: res, total: res.length};
                        <#else>
                        return res;
                        </#if>
                    },
                    querySuccess: ({$grid}) => {
                        $grid?.setAllTreeExpand(true);
                    },
                },
            },
            rowConfig: {
                keyField: 'id',
                isHover: true,
                isCurrent: true,
            },
            // checkboxConfig: {
            //   highlight: true,
            // },
            toolbarConfig: {
                custom: true,
                export: false,
                refresh: true,
                refreshOptions: {
                    code: 'query',
                },
                search: true,
                zoom: true,
                zoomOptions: {},
            },
        } as VxeTableGridOptions < ${classNamePrefix}Resp >,
    });

    <#if dialogType==1>
    const [EditorWindow, editorApi] = useVbenDrawer({
        connectedComponent: ${classNamePrefix}EditDrawer,
        destroyOnClose: true,
    });
    <#else >
    const [EditorWindow, editorApi] = useVbenModal({
        connectedComponent: ${classNamePrefix}EditModal,
        destroyOnClose: true,
    });
    </#if>


    const handleEdit = (record: ${classNamePrefix}Resp) => {
        editorApi.setData({id: record.id});
        editorApi.open();
    };

    const handleAdd = () => {
        editorApi.setData({});
        editorApi.open();
    };

    const handleDelete = async (row: ${classNamePrefix}Resp) => {
        try {
            await delete${classNamePrefix}(row.id);
            ElMessage.success($t('pages.common.deleteSuccess'));
            await tableGridApi.query();
            return true;
        } catch {
            return false;
        }
    };

    const handleExport = () => {
        useDownload(async () =>
            export${classNamePrefix}(
                await tableGridApi.formApi.getValues < ${classNamePrefix}Query > (),
            ),
        );
    };

    <#if listType== 2>
    // 树列表折叠状态
    const expanded = ref < boolean > (true);
    const handleExpand = () => {
        expanded.value = !expanded.value;
        tableGridApi.grid.setAllTreeExpand(expanded.value);
    };
    </#if>

</script>

<template>
    <Page auto-content-height>
        <TableGrid :table-title="$t('${apiModuleName}.${apiName}.listTitle')">
            <template #toolbar-tools>
                <ElSpace>
          <span v-access:code="['${apiModuleName}:${apiName}:create']">
            <ElButton type="primary" @click="handleAdd">
              {{ $t('pages.common.add') }}
            </ElButton>
          </span>
                    <span v-access:code="['${apiModuleName}:${apiName}:export']">
            <ElButton type="danger" @click="handleExport">
              {{ $t('pages.common.export') }}
            </ElButton>
          </span>
                    <#if listType== 2>
                        <ElButton v-if="!expanded" @click="handleExpand">
                            {{ $t('pages.common.expand') }}
                        </ElButton>
                        <ElButton v-if="expanded" @click="handleExpand">
                            {{ $t('pages.common.collapse') }}
                        </ElButton>
                    </#if>
                </ElSpace>
            </template>
            <template #status="{ row }">
                <ElTag v-if="row.status === 1" type="success">
                    {{ $t('common.enabled') }}
                </ElTag>
                <ElTag v-else type="danger">
                    {{ $t('common.disabled') }}
                </ElTag>
            </template>
            <template #action="{ row }">
                <ElSpace>
          <span v-access:code="['${apiModuleName}:${apiName}:update']">
            <ElButton type="primary" text link @click="handleEdit(row)">
              {{ $t('pages.common.edit') }}
            </ElButton>
          </span>
                    <span v-access:code="['${apiModuleName}:${apiName}:delete']">
            <ElPopconfirm :title="$t('ui.actionMessage.deleteConfirm', [row.name])" icon-color="red"
                          @confirm="handleDelete(row)">
              <template #reference>
                <ElButton type="danger" text link>
                  {{ $t('pages.common.delete') }}
                </ElButton>
              </template>
            </ElPopconfirm>
          </span>
                </ElSpace>
            </template>
        </TableGrid>
        <EditorWindow @success="tableGridApi.query()"/>
    </Page>
</template>
<style lang="scss" scoped></style>
