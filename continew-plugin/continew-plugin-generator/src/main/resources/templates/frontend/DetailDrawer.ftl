<template>
  <a-drawer v-model:visible="visible" :title="$t('${tableName?replace("_",".")}.page.detail.title')" :width="width >= 580 ? 580 : '100%'" :footer="false">
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

<script lang="ts" setup>
import { useWindowSize } from '@vueuse/core'
import { type ${classNamePrefix}DetailResp, get${classNamePrefix} } from '@/apis/${apiModuleName}/${apiName}'

const { width } = useWindowSize()

const visible = ref(false)
const dataId = ref('')
const dataDetail = ref<${classNamePrefix}DetailResp>()
// 查询详情
const getDataDetail = async () => {
  const res = await get${classNamePrefix}(dataId.value)
  dataDetail.value = res.data
}

// 打开详情
const onDetail = async (id: string) => {
  dataId.value = id
  await getDataDetail()
  visible.value = true
}

defineExpose({ onDetail })
</script>

<style lang="scss" scoped></style>
