<script setup lang="ts">
import type { CascaderNode, TreeNodeData } from 'element-plus';

import type { ${classNamePrefix}Resp, ${classNamePrefix}Query } from '#/api/${apiModuleName}/${apiName}';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';
import { getPopupContainer } from '@vben/utils';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { add${classNamePrefix}, get${classNamePrefix}, list${classNamePrefix}, update${classNamePrefix} } from '#/api/${apiModuleName}/${apiName}'
import { defaultFormValueGetter, useBeforeCloseDiff } from '#/utils/popup';

import { use${classNamePrefix}EditFormSchema } from './${classNamePrefix}Data';

const emits = defineEmits(['success']);
const dataId = ref('');
const isUpdate = computed(() => !!dataId.value);
<#if hasDictField>
import { useDict } from '#/hooks/app'

const { <#list dictCodes as dictCode>${dictCode}<#if dictCode_has_next>,</#if></#list> } = useDict(<#list dictCodes as dictCode>'${dictCode}'<#if dictCode_has_next>,</#if></#list>)
</#if>
const [EditorForm, editorFormApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
  },
  layout: 'horizontal',
  resetButtonOptions: {
    show: false,
  },
  schema: use${classNamePrefix}EditFormSchema(<#if hasDictField><#list dictCodes as dictCode>${dictCode}<#if dictCode_has_next>,</#if></#list></#if>),
  submitButtonOptions: {
    show: false,
  },
  wrapperClass: 'grid-cols-1 md:grid-cols-1 lg:grid-cols-1',
});

<#if listType == 2>
// 加载所有父选项
async function setup${treePid}Select() {
  const deptArray = await list${classNamePrefix}({});
  editorFormApi.updateSchema([
    {
      componentProps: {
        props: {
          label: ${treeLabel},
          value: ${treeId},
        },
        // 是否在点击节点的时候展开或者收缩节点， 默认值为 true，如果为 false，则只有点箭头图标的时候才会展开或者收缩节点。
        expandOnClickNode: false,
        // 是否默认展开所有节点
        defaultExpandAll: true,
        // 是否在点击节点的时候选中节点，默认值为 false，即只有在点击复选框时才会选中节点。
        checkOnClickNode: true,
        // checkOnClickLeaf: false,
        getPopupContainer,
        // 设置弹窗滚动高度 默认256
        listHeight: 300,
        data: deptArray,
        nodeKey: 'id',
        onNodeClick: (
                data: TreeNodeData,
                node: CascaderNode,
                // treeNode: TreeNode,
        ) => {
          // treeNode.expanded = false;
          node.checked = !node.checked;
          editorFormApi.form.setFieldValue('${treePid}', data.id);
        },
      },
      fieldName: '${treePid}',
    },
  ]);
}
</#if>

const { onBeforeClose, markInitialized, resetInitialized } = useBeforeCloseDiff(
        {
          initializedGetter: defaultFormValueGetter(editorFormApi),
          currentGetter: defaultFormValueGetter(editorFormApi),
        },
);

async function handleClosed() {
  await editorFormApi.resetForm();
  resetInitialized();
}

const [Drawer, drawerApi] = useVbenDrawer({
  onBeforeClose,
  onClosed: handleClosed,
  async onConfirm() {
    const { valid } = await editorFormApi.validate();
    if (!valid) return false;
    drawerApi.lock();
    try {
      if (isUpdate.value) {
        await update${classNamePrefix}(editorFormApi.form.values, dataId.value);
        ElMessage.success($t('pages.common.modifySuccess'));
      } else {
        await add${classNamePrefix}({
          ...editorFormApi.form.values,
        });
        ElMessage.success($t('pages.common.addSuccess'));
      }
      resetInitialized();
      emits('success');
      drawerApi.close();
      return true;
    } catch (error) {
      console.error(error);
    } finally {
      drawerApi.unlock();
    }
  },
  async onOpenChange(isOpen) {
    if (isOpen) {
      try {
        drawerApi.lock(true);
        const data = drawerApi.getData<${classNamePrefix}Resp>();
        dataId.value = data.id;

        <#if listType == 2>
        await setup${treePid}Select();
        </#if>

        if (data && data.id) {
          dataId.value = data.id;
          const res = await get${classNamePrefix}(data.id);
          editorFormApi.form.setValues(res);
        }
      } finally {
        await markInitialized();
        drawerApi.unlock();
      }
    }
  },
});

const getDrawerTitle = computed(() => {
  return isUpdate.value ? $t('pages.common.edit') : $t('pages.common.add');
});
</script>

<template>
  <Drawer :title="getDrawerTitle" class="w-[40%]">
    <div class="mx-auto flex h-full w-full flex-col">
      <EditorForm />
    </div>
  </Drawer>
</template>
<style lang="scss" scoped></style>
