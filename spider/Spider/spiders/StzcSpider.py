# # -*- coding: utf-8 -*-

# 数据爬取文件

import scrapy
import pymysql
import pymssql
from ..items import StzcItem
import time
from datetime import datetime,timedelta
import datetime as formattime
import re
import random
import platform
import json
import os
import urllib
from urllib.parse import urlparse
import requests
import emoji
import numpy as np
from DrissionPage import Chromium
import pandas as pd
from sqlalchemy import create_engine
from selenium.webdriver import ChromeOptions, ActionChains
from scrapy.http import TextResponse
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait
import pandas as pd
from sqlalchemy import create_engine
from selenium.webdriver import ChromeOptions, ActionChains
from scrapy.http import TextResponse
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.chrome.service import Service
# 电气设备
class StzcSpider(scrapy.Spider):
    name = 'stzcSpider'
    custom_settings = {
        'HTTPERROR_ALLOWED_CODES': [400,403],
        'RETRY_HTTP_CODES': [500, 503]
    }
    spiderUrl = 'https://www.stzc.com/Products_103.html#pro7'
    start_urls = spiderUrl.split(";")
    protocol = ''
    hostname = ''
    realtime = False


    def __init__(self,realtime=False,*args, **kwargs):
        super().__init__(*args, **kwargs)
        self.realtime = realtime=='true'


    # 列表解析
    def parse(self, response):
        _url = urlparse(self.spiderUrl)
        self.protocol = _url.scheme
        self.hostname = _url.netloc
        plat = platform.system().lower()
        if not self.realtime and (plat == 'linux' or plat == 'windows'):
            connect = self.db_connect()
            cursor = connect.cursor()
            if self.table_exists(cursor, '64up3m37_stzc') == 1:
                cursor.close()
                connect.close()
                self.temp_data()
                return
        list = response.xpath('''//ul[@class="pro_list"]/li''')
        for item in list:
            fields = StzcItem()

            try:
                fields["title"] = str( item.xpath('''.//div[@class="detail"]/a/@title''').extract_first())

            except:
                pass
            try:
                fields["imgurl"] = str("https://www.stzc.com" +  item.xpath('''.//div[@class="img"]/img/@src''').extract_first())

            except:
                pass
            try:
                fields["xqurl"] = str("https://www.stzc.com" + item.xpath('''.//div[@class="detail"]/a/@href''').extract_first())

            except:
                pass
            detailUrlRule = item.xpath('''.//div[@class="detail"]/a/@href''').extract_first()
            detailUrlRule ="https://www.stzc.com" +  detailUrlRule 
            if self.protocol in detailUrlRule or detailUrlRule.startswith('http'):
                pass
            elif detailUrlRule.startswith('//'):
                detailUrlRule = self.protocol + ':' + detailUrlRule
            elif detailUrlRule.startswith('/'):
                detailUrlRule = self.protocol + '://' + self.hostname + detailUrlRule
            else:
                detailUrlRule = self.protocol + '://' + self.hostname + '/' + detailUrlRule
            yield scrapy.Request(url=detailUrlRule, meta={'fields': fields},  callback=self.detail_parse, dont_filter=True)

    # 详情解析
    def detail_parse(self, response):
        fields = response.meta['fields']
        try:
            fields["jieshao"] = str( response.xpath('''string(.//div[@class="de_introduce"])''').extract()[0].strip())

        except:
            pass
        try:
            fields["youshi"] = str( response.xpath('''string(//div[@class="de_parameter"])''').extract()[0].strip())

        except:
            pass
        try:
            fields["chanshu"] = str("https://www.stzc.com" + response.xpath('''//div[@class="de_parameter"]//img/@src''').extract()[0].strip())

        except:
            pass
        return fields

    # 数据清洗
    def pandas_filter(self):
        engine = create_engine('mysql+pymysql://root:123456@localhost/spider64up3m37?charset=UTF8MB4')
        df = pd.read_sql('select * from stzc limit 50', con = engine)

        # 重复数据过滤
        df.duplicated()
        df.drop_duplicates()

        #空数据过滤
        df.isnull()
        df.dropna()

        # 填充空数据
        df.fillna(value = '暂无')

        # 异常值过滤

        # 滤出 大于800 和 小于 100 的
        a = np.random.randint(0, 1000, size = 200)
        cond = (a<=800) & (a>=100)
        a[cond]

        # 过滤正态分布的异常值
        b = np.random.randn(100000)
        # 3σ过滤异常值，σ即是标准差
        cond = np.abs(b) > 3 * 1
        b[cond]

        # 正态分布数据
        df2 = pd.DataFrame(data = np.random.randn(10000,3))
        # 3σ过滤异常值，σ即是标准差
        cond = (df2 > 3*df2.std()).any(axis = 1)
        # 不满⾜条件的⾏索引
        index = df2[cond].index
        # 根据⾏索引，进⾏数据删除
        df2.drop(labels=index,axis = 0)

    # 去除多余html标签
    def remove_html(self, html):
        if html == None:
            return ''
        pattern = re.compile(r'<[^>]+>', re.S)
        return pattern.sub('', html).strip()

    # 数据库连接
    def db_connect(self):
        type = self.settings.get('TYPE', 'mysql')
        host = self.settings.get('HOST', 'localhost')
        port = int(self.settings.get('PORT', 3306))
        user = self.settings.get('USER', 'root')
        password = self.settings.get('PASSWORD', '123456')

        try:
            database = self.databaseName
        except:
            database = self.settings.get('DATABASE', '')

        if type == 'mysql':
            connect = pymysql.connect(host=host, port=port, db=database, user=user, passwd=password, charset='utf8mb4')
        else:
            connect = pymssql.connect(host=host, user=user, password=password, database=database)
        return connect

    # 断表是否存在
    def table_exists(self, cursor, table_name):
        cursor.execute("show tables;")
        tables = [cursor.fetchall()]
        table_list = re.findall('(\'.*?\')',str(tables))
        table_list = [re.sub("'",'',each) for each in table_list]

        if table_name in table_list:
            return 1
        else:
            return 0

    # 数据缓存源
    def temp_data(self):

        connect = self.db_connect()
        cursor = connect.cursor()
        sql = '''
            insert into `stzc`(
                id
                ,title
                ,imgurl
                ,jieshao
                ,youshi
                ,chanshu
                ,xqurl
            )
            select
                id
                ,title
                ,imgurl
                ,jieshao
                ,youshi
                ,chanshu
                ,xqurl
            from `64up3m37_stzc`
            where(not exists (select
                id
                ,title
                ,imgurl
                ,jieshao
                ,youshi
                ,chanshu
                ,xqurl
            from `stzc` where
                `stzc`.id=`64up3m37_stzc`.id
            ))
            order by rand()
            limit 50;
        '''

        cursor.execute(sql)
        connect.commit()
        connect.close()
