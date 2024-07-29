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

Headers

| 参数名         | 类型     | 是否必须 | 备注            |
|-------------|--------|------|---------------|
| Oauth-Token | string | 必须   | 来自登录成功后的浏览器缓存 |

Body raw JSON

| 参数名         | 类型     | 是否必须 | 备注               |
|-------------|--------|------|------------------|
| category    | number | 必须   | 类别的ID            |
| title       | string | 非必须  | 任务的标题            |
| info        | string | 非必须  | 任务的详情            |
| deadline    | string | 非必须  | 截止日期(yyyy-mm-dd) |
| price_lower | number | 非必须  | 报价下限             |
| price_upper | number | 非必须  | 报价上限             |
| tel         | string | 非必须  | 联系方式             |

```json
{
    "category": "5",
    "title": "后端开发",
    "info": "后端开发",
    "deadline": "2024-09-10",
    "priceLower": "1000",
    "priceUpper": "3000",
    "tel": "123456"
}
```

#### 1.1.3 响应数据

JSON

| 参数名  | 类型     | 是否必须 | 备注                |
|------|--------|------|-------------------|
| code | number | 必须   | 响应码，0 代表成功        |
| msg  | string | 必须   | 提示信息              |
| data | number | 必须   | 发包任务的ID（由数据库自动生成） |

```json
{
  "code": 0,
  "msg": "成功",
  "data": 3
}
```

### 1.2 任务概览

#### 1.2.1 基本信息

> 请求路径：/projects
>
> 请求方式：GET
>
> 接口描述：查询所有发包任务

#### 1.2.2 请求参数

Headers  同上

#### 1.2.3 响应数据

```json
{
    "code": 0,
    "msg": "成功",
    "data": [
        {
            "pid": 1,
            "uid": 1,
            "avatar": "暂无头像",
            "nickname": "大号",
            "category": 5,
            "title": "后端开发",
            "info": "后端开发",
            "deadline": "2024-09-09T16:00:00.000+00:00",
            "priceLower": 1000,
            "priceUpper": 3000,
            "tel": "123456"
        },
        {
            "pid": 5,
            "uid": 1,
            "avatar": "暂无头像",
            "nickname": "大号",
            "category": 9,
            "title": "前端开发",
            "info": "前端开发",
            "deadline": "2024-09-09T16:00:00.000+00:00",
            "priceLower": 3000,
            "priceUpper": 5000,
            "tel": "987654321"
        },
        {
            "pid": 9,
            "uid": 1,
            "avatar": "暂无头像",
            "nickname": "大号",
            "category": 17,
            "title": "全栈开发",
            "info": "全栈开发",
            "deadline": "2024-09-09T16:00:00.000+00:00",
            "priceLower": 1000,
            "priceUpper": 3000,
            "tel": "1234567"
        }
    ]
}
```

### 1.3 任务详情

#### 1.3.1 基本信息

> 请求路径：/projects/{id}
>
> 请求方式：GET
>
> 接口描述：根据ID查询某个发包任务的详情

#### 1.3.2 请求参数

Headers  同上

| 参数名 | 类型     | 是否必须 | 备注      |
|-----|--------|------|---------|
| id  | number | 必须   | 发包任务的ID |

#### 1.3.3 响应数据

```json
{
    "code": 0,
    "msg": "成功",
    "data": {
        "pid": 1,
        "uid": 1,
        "avatar": "暂无头像",
        "nickname": "大号",
        "category": 5,
        "title": "后端开发",
        "info": "后端开发",
        "deadline": "2024-09-09T16:00:00.000+00:00",
        "priceLower": 1000,
        "priceUpper": 3000,
        "tel": "123456"
    }
}
```
