import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';

import { $t } from '#/locales';
import { dateRangeShortcuts } from '#/utils/dateTools';
<#if hasDictField>
import { useDict } from '#/hooks/app'
</#if>
export function use${classNamePrefix}EditFormSchema(
    <#if hasDictField>
    <#list dictCodes as dictCode>${dictCode}?: Ref<App.DictItem[]><#if dictCode_has_next>,\r\n </#if></#list>
    </#if>
): VbenFormSchema[] {
  return [
<#list fieldConfigs as fieldConfig>
  <#if fieldConfig.showInForm>
    {
      label: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
      fieldName: '${fieldConfig.fieldName}',
    <#if fieldConfig.formType = 'INPUT'>
      component: 'Input',
    <#elseif fieldConfig.formType = 'TEXT_AREA'>
      component: 'Textarea',
      componentProps: {
        autoSize: true,
      },
    <#elseif fieldConfig.formType = 'DATE'>
      component: 'DatePicker',
    <#elseif fieldConfig.formType = 'DATE_TIME'>
      component: 'DatePicker',
      componentProps: {
        placeholder: '${apiModuleName}.${apiName}.${fieldConfig.fieldName}Tip',
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'YYYY-MM-DD HH:mm:ss',
      },
    <#elseif fieldConfig.formType = 'TIME'>
      component: 'TimePicker',
    <#elseif fieldConfig.formType = 'INPUT_NUMBER'>
      component: 'InputNumber',
    <#elseif fieldConfig.formType = 'INPUT_PASSWORD'>
      component: 'InputPassword',
    <#elseif fieldConfig.formType = 'SWITCH'>
      component: 'Switch',
      componentProps: {
        class: 'w-auto',
      },
    <#elseif fieldConfig.formType = 'CHECK_BOX'>
      component: 'CheckboxGroup',
    <#elseif fieldConfig.formType = 'TREE_SELECT'>
      component: 'TreeSelect',
      dependencies: {
          show: (model) => model.parentId !== 0,
          triggerFields: ['parentId'],
        },
      componentProps: {
          props: {
            label: 'name',
            value: 'id',
          },
          // 是否在点击节点的时候展开或者收缩节点， 默认值为 true，如果为 false，则只有点箭头图标的时候才会展开或者收缩节点。
          expandOnClickNode: false,
          // 是否默认展开所有节点
          defaultExpandAll: true,
          // 是否在点击节点的时候选中节点，默认值为 false，即只有在点击复选框时才会选中节点。
          checkOnClickNode: true,
          // checkOnClickLeaf: false,
          // 设置弹窗滚动高度 默认256
          listHeight: 300,
          data: [],
          nodeKey: 'id',
      },
      rules: 'selectRequired',
    <#elseif fieldConfig.formType = 'SELECT'>
      component: 'Select',
      componentProps: {
        buttonStyle: 'solid',
    <#if fieldConfig.dictCode?? && fieldConfig.dictCode != ''>
        options: () => useDict('${fieldConfig.dictCode}').${fieldConfig.dictCode}?.value,
    <#elseif fieldConfig.dictType?? && fieldConfig.dictType != ''>
        options: [
          { label: $t('common.yes'), value: true },
          { label: $t('common.no'), value: false },
        ],
    <#elseif fieldConfig.dictType?? && fieldConfig.dictType != ''>
        options: [
          { label: $t('common.yes'), value: true },
          { label: $t('common.no'), value: false },
        ],
    </#if>
      },
    <#elseif fieldConfig.formType = 'RADIO'>
      component: 'RadioGroup',
      componentProps: {
        buttonStyle: 'solid',
      <#if fieldConfig.dictCode?? && fieldConfig.dictCode != ''>
        options: () => useDict('${fieldConfig.dictCode}').${fieldConfig.dictCode}?.value,
      <#elseif fieldConfig.dictType?? && fieldConfig.dictType != ''>
        options: [
          { label: $t('common.yes'), value: true },
          { label: $t('common.no'), value: false },
        ],
      </#if>
        optionType: 'button',
      },
      defaultValue: true,
    <#elseif fieldConfig.formType = 'RICH_TEXT'>
      component: 'VbenTiptap',
    </#if>

    <#if fieldConfig.isRequired>
      rules: 'required',
    </#if>
    },
  </#if>
</#list>
  ];
}

export function use${classNamePrefix}GridSearchFormSchema(
<#list dictCodes as dictCode>
    ${dictCode}?: Ref<App.DictItem[]><#if dictCode_has_next>,\r\n </#if>
</#list>
): VbenFormSchema[] {
  return [
    <#list fieldConfigs as fieldConfig>
    <#if fieldConfig.showInQuery>
      {
        fieldName: '${fieldConfig.fieldName}',
        label: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
      <#if fieldConfig.formType == "SELECT"><#-- 下拉框 -->
        component: 'Select',
        componentProps: {
          placeholder: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
          options: <#list dictCodes as dictCode><#if dictCode==fieldConfig.fieldName>, </#if></#list>,
        },
      <#elseif fieldConfig.formType == "RADIO"><#-- 单选框 -->
        component: 'RadioGroup',
        componentProps: {
          placeholder: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}Tip'),
          isButton: true,
          buttonStyle: 'solid',
          size: 'small',
          options: <#list dictCodes as dictCode><#if dictCode==fieldConfig.fieldName>, </#if></#list>,
        },
        defaultValue: 1,
      <#elseif fieldConfig.formType == "DATE"><#-- 日期框 -->
        component: 'DatePicker',
        componentProps: {
          placeholder: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}Tip'),
        <#if fieldConfig.queryType == "BETWEEN">
          type: 'daterange',
        </#if>
        },
      <#elseif fieldConfig.formType == "DATE_TIME"><#-- 日期时间框 -->
        component: 'DatePicker',
        componentProps: {
          placeholder: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}Tip'),
          showTime: true,
          format: 'YYYY-MM-DD HH:mm:ss',
          valueFormat: 'YYYY-MM-DD HH:mm:ss',
        <#if fieldConfig.queryType == "BETWEEN">
          type: 'daterange',
          shortcuts: dateShortcuts,
        </#if>
        },
      <#else>
        component: 'Input',
      </#if>
      },
    </#if>
    </#list>
  ];
}

// Table 字段配置
export function use${classNamePrefix}GridFieldColumns(): VxeTableGridOptions['columns'] {
  return [
    { type: 'checkbox', width: 50, fixed: 'left' },
    { type: 'seq', width: 50, fixed: 'left' },
<#if fieldConfigs??>
  <#list fieldConfigs as fieldConfig>
    <#if fieldConfig.showInList>
      <#if fieldConfig.fieldName=="createUser" >
    {
        field: 'createUserString',
        title: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
        minWidth: 150,
        align: 'center',
    },
      <#elseif fieldConfig.fieldName=="updateUser"  >
    {
        field: 'updateUserString',
        title: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
        minWidth: 150,
        align: 'center',
     },
      <#else>
    {
        field: '${fieldConfig.fieldName}',
        title: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
        // slots: { default: '${fieldConfig.fieldName}' },
        minWidth: 150,
        align: 'center',
    },
      </#if>
    </#if>
  </#list>
</#if>
    {
      align: 'center',
      field: 'action',
      fixed: 'right',
      slots: { default: 'action' },
      title: $t('common.operation'),
      width: 150,
    },
  ];
}
