# Kakeibo Application

## initialize

### Docker

srart:
```docker-compose up -d```  
end
```docker-compose down -v```

#### postgres
user:```root```  
password:```root```  
db:```kakeibo```  
login  
```
psql -h localhost -p 5432 -U root -d kakeibo
```

## 実装
### Sub module
#### config
設定ファイルを置くためのmodule  
boot classもここにある。  
基本的にはjava fileはこのmoduleには置かない。

#### web
controllerやhtmlと言ったView部分を配置するmodule
```
web
├─build.gradle
└─src
    ├─main
    │  ├─java
    │  │  └─chigirh.app.kakeibo.web
    │  │    ├─controller:controller
    │  │    ├─form:web model object
    │  │    └─interceptor
    │  └─resources
    │      ├─static:static html
    │      └─templates:html
    └─test:test case
```
- controller  
  Controllerの役割はformで受け取った値をdomain entityに変換してUseCaseに渡す。
  UseCaseから受け取った対をmodelに設定してviewに渡す。  
  の2つのみ。Controllerで業務ロジックを書かない。
  
- form  
  viewからdataを受け取るためのmodel object。  
  単項目チェック相関チェックはここで行う。  
  使えるアノテーションは[こちら](https://spring.pleiades.io/specifications/platform/8/apidocs/javax/validation/constraints/package-summary.html)
  を参照。  
  相関チェックは[こちら](https://www.purin-it.com/spring-boot-web-check-form)
  を参照。
  
- html  
  とりあえず[この辺](https://qiita.com/NagaokaKenichi/items/c6d1b76090ef5ef39482)
  を参考にすれば、作れるはず。  
  cssは[Bootstrap](https://getbootstrap.jp/docs/4.3/getting-started/introduction/)
  を使えば楽。
  

#### application
domain layer
```
application
├─build.gradle
└─src
    ├─main
    │  └─java
    │      └─chigirh.app.kakeibo.application
    │         ├─service:domain service
    │         ├─repository:infra adapter
    │         └─usecase:usecase
    └─test:test case
```
- service  
  Serviceの作成は必須ではない。
  複数のUseCaseで利用するような共通の業務処理をServiceに記述する。  
  業務処理が複雑で、UseCaseが長くなる場合にも、処理の一部をServiceとして切り出して記述する。
  複数のRepositoryを跨いでデータを取得する場合にもServiceに記述する。
  `例:ARepositoryから取得した値を検索条件にBRepositoryでデータを検索する`
  
- repository
  infra layerとのadapter。domain layerのRepositoryにはinterfaceを記述する。  
  domain layerではclassやmethodにinfraを意識した名前は付けない。  
  `例1:PostgresUserRepositoryの様なclass名はNG`  
  `例2:findByUserIdの様なmethod名はNG`
  
- UseCase  
  処理の業務ロジックを記述する。  
  基本的には1処理につき1UseCaseを作成する。class名は`処理名 + UseCase`  
  `@UseCase`アノテーションを付与すること。  
  `UseCaseBase`を継承する。
#### domain
domain layer
```
domain
├─build.gradle
└─src
    ├─main
    │  └─java
    │      └─chigirh.app.kakeibo.domain
    │         ├─entity:domain entity
    │         │  ├─page:paging処理の共通entity
    │         │  │  └─condition
    │         │  └─sample:sample model entity
    │         │      └─condition:検索条件のようなエンティティはここに作る
    │         └─error
    │            ├─business:ビジネス例外
    │            └─system:system error
    └─test:test case
```
- entity  
  データの実態をEntityとして記述する。  
  Entityにロジックを持たせるのはOK。
  
- entity.condition  
  データの実態とは違うようなEntityはここに作成する。  
  `例1:targetUserTypeの様なEnumを使って特定の役職のユーザーを検索する`  
  `例2:検索条件が多く、Repositoryへの引数が多いのでFetchUserCondition classを作成する。`  
  `例3:総件数とEntityのListの様に複数のデータをRepositoryから返したい場合にFetchUserResult classを作成する。`
#### infra layer
infra layer
```
infra
├─build.gradle
└─src
    ├─main
    │  ├─java
    │  │  └─chigirh.app.kakeibo.infra
    │  │     ├─config:config class
    │  │     ├─convert:dtoとdomain entityのconverter
    │  │     ├─dto:dto(DBから取得するdataのmodelはここ。表結合をしたmodelもここであらわす)
    │  │     ├─entity:infra entity
    │  │     ├─mapper:mapper interface
    │  │     │  ├─cluster:insert,update,deleteはここ。
    │  │     │  └─reader:selectはここ。
    │  │     └─repository:domain layerのrepositoryの実装class
    │  └─resources
    │      └─mybatis
    │          └─sqls
    │              ├─cluster:mapper.clusterのxml定義。sqlはここに書く。
    │              └─reader:mapper.readerのxml定義。sqlはここに書く。
    └─test:test case
```
- config
  あまり触ることはないはず。
  
- converter
  DBから取得したDTOをdomain entityに変換したりする処理はconverterとして切り出す。  
  データの項目が少なかったり、あまり処理が複雑にならない場合は、特に作成する必要ななし。 
  
- dto
  DBから取得するdataのmodelはここ。表結合をしたmodelもここで表す。  
  DTOはたPOJO classである必要があるため、ロジックは持たせず、fieldをgetter,setterのみの単純な構造にする。
  
- entity
  infra layerのEntity。  
  insertやupdateで使うentityはここ。基本はテーブルの1レコードの情報をentityとして持つ
  
- mapper
  - cluster  
    insert,update,deleteをmapper interface
  - reader  
    selectはここ。読み取り専用のdata sourceなのでDBの更新はできない。
    
- resources/mybatis/sqls  
  mapper interfaceに記述したmethodのsqlをここに書く。
  
- Mybatis  
  [ここ](https://qiita.com/kazuki43zoo/items/ea79e206d7c2e990e478)
  とか分かりやすい。