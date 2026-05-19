{
    "${apiName}": {
        "listTitle": "${tableName} manage",
        <#list fieldConfigs as fieldConfig>
        "${fieldConfig.fieldName}": "${fieldConfig.columnName?replace('_', ' ')?word_list?map(word -> word?cap_first)?join(' ')}",
        </#list>
    }
}