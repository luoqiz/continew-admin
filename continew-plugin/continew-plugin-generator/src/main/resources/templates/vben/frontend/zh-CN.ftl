{
"${apiName}": {
"listTitle": "${businessName}管理",
<#list fieldConfigs as fieldConfig>
    "${fieldConfig.fieldName}": "${fieldConfig.comment}",
</#list>
}
}