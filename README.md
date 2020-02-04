# PosSystem

[![Build Status](https://travis-ci.com/liuyuhang997/PosSystem.svg?branch=master)](https://travis-ci.com/liuyuhang997/PosSystem)
[![codecov](https://codecov.io/gh/liuyuhang997/PosSystem/branch/master/graph/badge.svg)](https://codecov.io/gh/liuyuhang997/PosSystem)

## 项目描述

PosSystem是一个命令行的Pos系统，Pos系统运行时会读取[购物车]文件和[优惠活动]文件，并根据购物车中的商品按优惠活动进行打折计算，然后将计算后的购物清单打印在命令行。

## 项目配置

1. 项目配置了git-hooks，在pre-push时会运行"./gradlew check"检查项目测试是否通过
2. 项目使用了travis CI环境，在.travis.yml中会运行"./gradlew clean build"进行build
3. 项目使用了codecov来显示测试覆盖率，在travis CI运行完成后会上报测试覆盖率，作为徽章显示在README页面

## 运行

1. 直接运行main函数
2. 传入商店名称运行main函数(可选)

    ```shell
    java -jar PosSystem.java [ShopName]
    ```

## 项目结构

```text
possystem
├── entities
│   ├── Cart.java #购物车，使用Map储存对应商品
│   ├── Item.java #商品，包含商品的信息
│   └── PromotionItem.java #优惠活动商品
├── enums
│   ├── FileNameEnum.java #购物车和优惠活动的文件名称
│   └── PromotionEnum.java #优惠活动的Type
├── factories
│   └── PromotionFactory.java #根据PromotionEnum返回对应的优惠活动
├── print
│   ├── ConsolePrint.java #负责打印购物车到命令行
│   └── PrintFormat.java #打印时所需要的格式静态常量
├── promotions
│   ├── BuyTwoGetOneFree.java #买二赠一优惠
│   ├── Discount.java #打折优惠
│   ├── Promotion.java #优惠活动的基类，计算商品优惠后的小结
│   └── SecondHalfPrice.java #第二件半价优惠
├── utils
│   └── FileUtil.java #获取resource路径和按行读取文件
└── PosSystem.java #主程序入口
```

## 备注

1. ### 优惠活动

    ```text
    src
    └── main
        └── resources
            ├── buy_two_get_one_free_promotion.txt
            ├── discount_promotion.txt
            └── second_half_price_promotion.txt
    ```

## 其他

作业要求: [pos-readme]

[购物车]: src/main/resources/cart.txt
[优惠活动]: #优惠活动
[pos-readme]: pos-readme.md
