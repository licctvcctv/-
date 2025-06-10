# 数据容器文件

import scrapy

class SpiderItem(scrapy.Item):
    pass

class StzcItem(scrapy.Item):
    # 名称
    title = scrapy.Field()
    # 图片链接
    imgurl = scrapy.Field()
    # 产品介绍
    jieshao = scrapy.Field()
    # 核心优势
    youshi = scrapy.Field()
    # 技术参数(图片)
    chanshu = scrapy.Field()
    # 详情链接
    xqurl = scrapy.Field()

