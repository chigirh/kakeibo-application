# サンプル機能設計書

## FXX-01(サンプル登録)

### description

サンプルデータを登録する。

### crud

|table|create|update|read|delete|description
|:---|:---|:---|:---|:---|:---|
|main.sample|〇|-|-|-|versionは1固定|

### model

|field|type|notnull|min|max|regex|description|
|:---|:---|:---|:---|:---|:---|:---|
|key|String|Y|1|10|-||
|value|String|Y|1|30|-||

### URI

```
sample/create
```

### error

TODO

## FXX-02(サンプル更新)

### description

サンプルデータを更新する。

### crud

|table|create|update|read|delete|description
|:---|:---|:---|:---|:---|:---|
|main.sample|-|〇|-|-|-|

### model

|field|type|notnull|min|max|regex|description|
|:---|:---|:---|:---|:---|:---|:---|
|key|String|Y|1|10|-||
|value|String|Y|1|30|-||
|version|String|Y|1||-||

### URI

```
sample/update
```

## FXX-03(サンプル参照)

### description

サンプルデータ一覧を表示する。

### crud

|table|create|update|read|delete|description
|:---|:---|:---|:---|:---|:---|
|main.sample|-|-|〇|-|-|

### model

|field|type|notnull|min|max|regex|description|
|:---|:---|:---|:---|:---|:---|:---|
|key|String|Y|1|10|-||
|value|String|Y|1|30|-||
|version|String|Y|1||-|hidden|

### URI

```
sample
```

## FXX-02(サンプル削除)

### description

サンプルデータを削除する。

### crud

|table|create|update|read|delete|description
|:---|:---|:---|:---|:---|:---|
|main.sample|-|-|-|〇|-|

### model

|field|type|notnull|min|max|regex|description|
|:---|:---|:---|:---|:---|:---|:---|
|key|String|Y|1|10|-||
|version|String|Y|1||-||

### URI

```
sample/delete
```
