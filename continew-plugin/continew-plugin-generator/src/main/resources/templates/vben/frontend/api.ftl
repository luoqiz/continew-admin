import type { BaseEntity, PageQuery, PageRes } from '#/types/api';

import { requestClient as http } from '#/api/request';

const BASE_URL = '/${apiModuleName}/${apiName}';

export interface ${classNamePrefix}Resp extends BaseEntity {
<#if fieldConfigs??>
<#list fieldConfigs as fieldConfig>
  <#if fieldConfig.showInList>
  /** ${fieldConfig.comment} */
  ${fieldConfig.fieldName}: string;
  </#if>
</#list>
</#if>
}

export interface ${classNamePrefix}DetailResp extends BaseEntity {
<#if fieldConfigs??>
<#list fieldConfigs as fieldConfig>
  ${fieldConfig.fieldName}: string;
</#list>
  createUserString: string;
  updateUserString: string;
</#if>
}

export interface ${classNamePrefix}Query {
<#if fieldConfigs??>
<#list fieldConfigs as fieldConfig>
  <#if fieldConfig.showInQuery>
  ${fieldConfig.fieldName}?: string | undefined;
  </#if>
</#list>
</#if>
  sort: Array<string> | string;
}

export interface ${classNamePrefix}PageQuery extends ${classNamePrefix}Query, PageQuery {}

/** @desc 查询${businessName}列表 */
export function list${classNamePrefix}(query: ${classNamePrefix}PageQuery) {
  return http.get<PageRes<${classNamePrefix}Resp[]>>(BASE_URL, {
    params: query,
  });
}

/** @desc 查询${businessName}详情 */
export function get${classNamePrefix}(id: string) {
  return http.get<${classNamePrefix}DetailResp>(`${'$'}{BASE_URL}/${'$'}{id}`);
}

/** @desc 新增${businessName} */
export function add${classNamePrefix}(data: any) {
  return http.post(BASE_URL, data);
}

/** @desc 修改${businessName} */
export function update${classNamePrefix}(data: any, id: string) {
  return http.put(`${'$'}{BASE_URL}/${'$'}{id}`, data);
}

/** @desc 删除${businessName} */
export function delete${classNamePrefix}(id: string) {
  return http.delete(BASE_URL, { data: { ids: [id] } });
}

/** @desc 导出${businessName} */
export function export${classNamePrefix}(query: ${classNamePrefix}Query) {
  return http.download(`${'$'}{BASE_URL}/export`, {
      params: query,
    });
}
