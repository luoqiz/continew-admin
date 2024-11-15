<template>
  <a-modal
    v-model:visible="visible"
    :title="title"
    :mask-closable="false"
    :esc-to-close="false"
    :width="width >= 600 ? 600 : '100%'"
    @before-ok="save"
    @close="reset"
  >
    <GiForm ref="formRef" v-model="form" :options="options" :columns="columns" />
  </a-modal>
</template>

<script setup lang="ts">
import { useI18n } from 'vue-i18n'
import { Message } from '@arco-design/web-vue'
import { get${classNamePrefix}, add${classNamePrefix}, update${classNamePrefix} } from '@/apis/${apiModuleName}/${apiName}'
import { type Columns, GiForm, type Options } from '@/components/GiForm'
import { useForm } from '@/hooks'
import { useDict } from '@/hooks/app'

const emit = defineEmits<{
  (e: 'save-success'): void
}>()

const { t } = useI18n()

const dataId = ref('')
const visible = ref(false)
const isUpdate = computed(() => !!dataId.value)
const title = computed(() => (isUpdate.value ? t('${tableName?replace("_",".")}.page.modify.title') : t('${tableName?replace("_",".")}.page.add.title')))
const formRef = ref<InstanceType<typeof GiForm>>()

<#if hasDictField>
const { <#list dictCodes as dictCode>${dictCode}<#if dictCode_has_next>,</#if></#list> } = useDict(<#list dictCodes as dictCode>'${dictCode}'<#if dictCode_has_next>,</#if></#list>)
</#if>

const options: Options = {
  form: { size: 'large' },
  btns: { hide: true },
}

const columns: Columns = reactive([
<#list fieldConfigs as fieldConfig>
  <#if fieldConfig.showInForm>
  {
    label: t('${tableName?replace("_",".")}.field.${fieldConfig.fieldName}'),
    field: '${fieldConfig.fieldName}',
    <#if fieldConfig.formType = 'INPUT'>
    type: 'input',
    <#elseif fieldConfig.formType = 'TEXT_AREA'>
    type: 'textarea',
    <#elseif fieldConfig.formType = 'DATE'>
    type: 'date-picker',
    <#elseif fieldConfig.formType = 'DATE_TIME'>
    type: 'time-picker',
    <#elseif fieldConfig.formType = 'INPUT_NUMBER'>
    type: 'input-number', 
    <#elseif fieldConfig.formType = 'INPUT_PASSWORD'>
    type: 'input-password',
    <#elseif fieldConfig.formType = 'SWITCH'>
    type: 'switch',
    <#elseif fieldConfig.formType = 'CHECK_BOX'>
    type: 'check-group',
   	<#elseif fieldConfig.formType = 'TREE_SELECT'>
    type: 'tree-select',
    <#elseif fieldConfig.formType = 'SELECT'>
    type: 'select', 
    <#elseif fieldConfig.formType = 'RADIO'>
    type: 'radio-group',
    </#if>
    <#if fieldConfig.dictCode?? && fieldConfig.dictCode != ''>
    options: ${fieldConfig.dictCode},
    </#if>
    <#if fieldConfig.isRequired>
    rules: [{ required: true, message: t('${tableName?replace("_",".")}.field.${fieldConfig.fieldName}_placeholder') }]
    </#if>
  },
  </#if>
</#list>
])

const { form, resetForm } = useForm({
  // todo 待补充
})

// 重置
const reset = () => {
  formRef.value?.formRef?.resetFields()
  resetForm()
}

// 保存
const save = async () => {
  try {
    const isInvalid = await formRef.value?.formRef?.validate()
    if (isInvalid) return false
    if (isUpdate.value) {
      await update${classNamePrefix}(form, dataId.value)
      Message.success(t('page.common.message.modify.success'))
    } else {
      await add${classNamePrefix}(form)
      Message.success(t('page.common.message.add.success'))
    }
    emit('save-success')
    return true
  } catch (error) {
    return false
  }
}

// 新增
const onAdd = async () => {
  reset()
  dataId.value = ''
  visible.value = true
}

// 修改
const onUpdate = async (id: string) => {
  reset()
  dataId.value = id
  const { data } = await get${classNamePrefix}(id)
  Object.assign(form, data)
  visible.value = true
}

defineExpose({ onAdd, onUpdate })
</script>
