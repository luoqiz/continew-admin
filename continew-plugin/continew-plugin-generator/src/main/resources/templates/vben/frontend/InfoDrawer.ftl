<script setup lang="ts">
import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { type ${classNamePrefix}Resp,get${classNamePrefix} } from '#/api/${apiModuleName}/${apiName}'

const ${classNamePrefix?uncap_first}Info = ref<${classNamePrefix}Resp>();
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

const [Drawer, drawerApi] = useVbenDrawer({
  showConfirmButton: false,
  cancelText: $t('common.cancel'),
  async onOpenChange(isOpen) {
    if (isOpen) {
      try {
        drawerApi.lock(true);
        const infoId = drawerApi.getData<${classNamePrefix}Resp>();
         if (infoId) {
            ${classNamePrefix?uncap_first}Info.value = await get${classNamePrefix}(infoId.id);
         }
        <#if listType == 2>
        await setup${treePid}Select();
        </#if>
      } finally {
        drawerApi.unlock();
      }
    }
  },
});

const getDrawerTitle = computed(() => {
  return $t('pages.common.info');
});
</script>

<template>
  <Drawer :title="getDrawerTitle" class="w-[40%]">
    <div class="p-4">
      <ElDescriptions :column="2" border class="general-description">
        <#if fieldConfigs??>
        <#list fieldConfigs as fieldConfig>
          // ${fieldConfig.comment}
          <ElDescriptionsItem :label="$t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}')" label-align="right">
            {{ ${classNamePrefix?uncap_first}Info?.${fieldConfig.fieldName} }}
          </ElDescriptionsItem>
        </#list>
        </#if>
      </ElDescriptions>
    </div>
  </Drawer>
</template>
<style lang="scss" scoped></style>
