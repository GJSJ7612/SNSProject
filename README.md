# SNS Project 接口文档

## 1. 发包模块

### 1.1 创建发包

#### 1.1.1 基本信息

> 请求路径：/projects/create
>
> 请求方式：POST
>
> 接口描述：该接口用于创建发包



#### 1.1.2 请求参数

无



#### 1.1.3 响应数据

参数格式：application/json

参数说明：

| 参数名            | 类型        | 是否必须 | 备注                |
|----------------|-----------|------|-------------------|
| code           | number    | 必须   | 响应码，1 代表成功，0 代表失败 |
| msg            | string    | 非必须  | 提示信息              |
| data           | object[ ] | 非必须  | 返回的数据             |
| \|- id         | number    | 非必须  | id                |
| \|- name       | string    | 非必须  | 部门名称              |
| \|- createTime | string    | 非必须  | 创建时间              |
| \|- updateTime | string    | 非必须  | 修改时间              |

响应数据样例：

```json
{
  "code": 1,
  "msg": "success",
  "data": [
    {
      "id": 1,
      "name": "学工部",
      "createTime": "2022-09-01T23:06:29",
      "updateTime": "2022-09-01T23:06:29"
    },
    {
      "id": 2,
      "name": "教研部",
      "createTime": "2022-09-01T23:06:29",
      "updateTime": "2022-09-01T23:06:29"
    }
  ]
}
```
