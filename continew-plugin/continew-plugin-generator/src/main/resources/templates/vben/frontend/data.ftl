import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';

import { $t } from '#/locales';
import { dateRangeShortcuts } from '#/utils/dateTools';

export function use${classNamePrefix}EditFormSchema(): VbenFormSchema[] {
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
            component: 'time-picker',
        <#elseif fieldConfig.formType = 'INPUT_NUMBER'>
            component: 'InputNumber',
        <#elseif fieldConfig.formType = 'INPUT_PASSWORD'>
            component: 'InputPassword',
        <#elseif fieldConfig.formType = 'SWITCH'>
            component: 'switch',
        <#elseif fieldConfig.formType = 'CHECK_BOX'>
            component: 'CheckboxGroup',
        <#elseif fieldConfig.formType = 'TREE_SELECT'>
            component: 'tree-select',
        <#elseif fieldConfig.formType = 'SELECT'>
            component: 'select',
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
        </#if>
        <#if fieldConfig.isRequired>
            rules: 'required',
        </#if>
        },
    </#if>
</#list>
];
}

export function use${classNamePrefix}GridSearchFormSchema(): VbenFormSchema[] {
return [
<#list fieldConfigs as fieldConfig>
    <#if fieldConfig.showInQuery>
        {
        fieldName: '${fieldConfig.fieldName}',
        label: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
        <#if fieldConfig.formType == "SELECT"><#-- 下拉框 -->
            component: 'Select',
            componentProps: {
            placeholder: '${apiModuleName}.${apiName}.${fieldConfig.fieldName}Tip',
            options: [
            { value: 1, label: '表格列表' },
            { value: 2, label: '树状列表' },
            ],
            },
        <#elseif fieldConfig.formType == "RADIO"><#-- 单选框 -->
            component: 'RadioGroup',
            componentProps: {
            placeholder: '${apiModuleName}.${apiName}.${fieldConfig.fieldName}Tip',
            isButton: true,
            buttonStyle: 'solid',
            size: 'small',
            options: [
            { value: 1, label: '表格列表' },
            { value: 2, label: '树状列表' },
            ],
            },
            defaultValue: 1,
        <#elseif fieldConfig.formType == "DATE"><#-- 日期框 -->
            component: 'DatePicker',
            componentProps: {
            placeholder: '${apiModuleName}.${apiName}.${fieldConfig.fieldName}Tip',
            <#if fieldConfig.queryType == "BETWEEN">
                type: 'daterange',
            </#if>
            },
        <#elseif fieldConfig.formType == "DATE_TIME"><#-- 日期时间框 -->
            component: 'DatePicker',
            componentProps: {
            placeholder: '${apiModuleName}.${apiName}.${fieldConfig.fieldName}Tip',
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
{ type: 'seq', width: 70, fixed: 'left' },
<#if fieldConfigs??>
    <#list fieldConfigs as fieldConfig>
        <#if fieldConfig.showInList>
            <#if fieldConfig.fieldName=="createUser" >
                { field: 'createUserString', title: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'), align: 'center', },
            <#elseif fieldConfig.fieldName=="updateUser"  >
                {
                field: 'updateUserString',
                title: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
                align: 'center', },
            <#else>
                {
                field: '${fieldConfig.fieldName}',
                title: $t('${apiModuleName}.${apiName}.${fieldConfig.fieldName}'),
                // slots: { default: '${fieldConfig.fieldName}' },
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
