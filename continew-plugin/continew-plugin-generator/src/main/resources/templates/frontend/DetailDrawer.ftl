<template>
  <a-drawer v-model:visible="visible" :title="$t('${tableName?replace("_",".")}.page.detail.title')" :width="width >= 600 ? 600 : '100%'" :footer="false">
    <a-descriptions :column="2" size="large" class="general-description">
      <#list fieldConfigs as fieldConfig>
      <a-descriptions-item :label="$t('${tableName?replace("_",".")}.field.${fieldConfig.fieldName}')">{{ dataDetail?.${fieldConfig.fieldName} }}</a-descriptions-item>
      <#if fieldConfig.fieldName = 'createUser'>
      <a-descriptions-item :label="$t('page.common.field.createUser')">{{ dataDetail?.createUserString }}</a-descriptions-item>
      <#elseif fieldConfig.fieldName = 'updateUser'>
      <a-descriptions-item :label="$t('page.common.field.updateUser')">{{ dataDetail?.updateUserString }}</a-descriptions-item>
      </#if>
      </#list>
    </a-descriptions>
  </a-drawer>
</template>

<script setup lang="ts">
import { useWindowSize } from '@vueuse/core'
import { type ${classNamePrefix}DetailResp, get${classNamePrefix} } from '@/apis/${apiModuleName}/${apiName}'

const { width } = useWindowSize()

const dataId = ref('')
const dataDetail = ref<${classNamePrefix}DetailResp>()
const visible = ref(false)

// 查询详情
const getDataDetail = async () => {
  const { data } = await get${classNamePrefix}(dataId.value)
  dataDetail.value = data
}

// 打开
const onOpen = async (id: string) => {
  dataId.value = id
  await getDataDetail()
  visible.value = true
}

defineExpose({ onOpen })
</script>

<style lang="scss" scoped></style>
