# PosSystem

[![Build Status](https://travis-ci.com/liuyuhang997/PosSystem.svg?branch=master)](https://travis-ci.com/liuyuhang997/PosSystem)
[![codecov](https://codecov.io/gh/liuyuhang997/PosSystem/branch/master/graph/badge.svg)](https://codecov.io/gh/liuyuhang997/PosSystem)

## 运行环境

- JDK 8
- IntelliJ IDEA

## 准备工作

- 在操作系统上用相应的包管理工具安装 Maven
- 使用IntelliJ IDEA将本项目作为一个新的Maven项目导入并成功编译

## 教学目标

1. 熟悉Java的基本语法；
2. 熟悉Java的基本逻辑语句及关键字；

## 需求描述

商店里进行购物结算时会使用收银机（POS）系统，这台收银机会在结算时根据客户的购物车（Cart）中的商品（Item）和商店正在进行的优惠活动（Promotion）进行结算和打印购物清单。

已知该商店正在对部分商品进行“买二赠一”、“指定商品打折”、“第二件商品半价”等优惠活动，如遇到某件商品存在多种优惠活动的情况时，则按照优惠力度最高的活动进行计算。

我们需要实现一个程序，该程序能够将一个 ```cart.txt``` 文件中指定格式的数据（条形码）作为参数输入，然后然后在操作系统的命令行中输出结算清单的文本。

```cart.txt``` 文本的格式（样例）：

```
ITEM000001
ITEM000001
ITEM000001
ITEM000001
ITEM000001
ITEM000003-2
ITEM000005
ITEM000005
ITEM000005
```

其中对'ITEM000003-2'来说,"-"之前的是标准的条形码,"-"之后的是数量（可能有小数）。
当我们购买需要称量的物品的时候,由称量的机器生成此类条形码,收银机负责识别生成小票。

“买二赠一”优惠活动文本文件 ```buy_two_get_one_free_promotion.txt``` 内容（样例，只包含唯一的参与优惠活动的物品条形码）：

```
ITEM00001
ITEM00003
ITEM00005
```

“第二件商品半价”优惠活动文本文件 ```second_half_price_promotion.txt``` 内容（样例，只包含唯一的参与优惠活动的物品条形码）：

```
ITEM00001
ITEM00003
ITEM00005
```

“指定商品打折”优惠活动文本文件 ```discount_promotion.txt``` 内容（样例，只包含唯一的参与优惠活动的物品条形码和由":"符号分割的两位数折扣）：

```
ITEM00001:75
ITEM00003:85
ITEM00005:90
```

清单内容需要包括以下信息（格式自定）：

- 商店名称
- 打印时间
- 购物明细（数量、单价、计量单位、小计）
- 总计金额（优惠前、优惠后、优惠差价）


## 作业要求

1. 请自行设计代码实现；
2. 严格按照面向对象（OOP）的方式编写代码；
3. 请在保证代码可读性的前提下，尽可能用最少的代码行数完成作业；
4. 将清单输出到命令行；
5. 请保持良好的代码提交（Commit）习惯。

## 作业提示

1. 请掌握基本的Java异常处理知识
2. 善于利用IDE便捷的调试功能

## 验收作业的层次

验收层次从低到高排序，完成时切勿好高骛远，应根据自身水平脚踏实地循序渐进，每一层的推进都是更多知识的学习。

1. 是否符合面向对象编程的要求
2. 是否拥有单元测试
3. 是否使用了测试驱动开发的方式
4. 是否合理使用了Java 8的新特性
5. 是否使用了 [Guava](https://code.google.com/p/guava-libraries/) 改进编程

## 已经集成的测试工具

如需要编写测试代码，请参考以下内容：

- [JUnit](http://junit.org)
- [Mockito](https://code.google.com/p/mockito/)
- [FEST Fluent Assertions](https://github.com/alexruiz/fest-assert-2.x)

