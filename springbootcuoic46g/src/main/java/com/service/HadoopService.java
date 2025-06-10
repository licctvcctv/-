package com.service;

import java.util.List;
import java.util.Map;

/**
 * HadoopService接口定义了运行Hadoop MapReduce作业的服务方法
 * 该接口主要针对数据分析和处理，支持基于时间和分组的MapReduce作业执行
 */
public interface HadoopService {

    /**
     * 运行一个针对特定值的MapReduce作业
     * 该方法主要用于处理需要根据时间类型对一个或多个列进行分析的数据列表
     *
     * @param dataList     含有待处理数据的列表，每个元素是一个Map，键为列名，值为对应的数据项
     * @param className    MapReduce作业的主类名称
     * @param xColumnName  X轴列名，通常代表时间或其他自变量列
     * @param timeType     时间类型，用于指定时间序列的处理方式
     * @param yColumnNames Y轴列名数组，代表需要进行分析的一个或多个列
     * @throws Exception 如果作业运行过程中出现错误，则抛出异常
     */
    void runMapReduceJobForValue(List<Map<String, Object>> dataList, String className, String xColumnName, String timeType, String... yColumnNames) throws Exception;


    /**
     * 运行一个针对分组数据的MapReduce作业
     * 该方法主要用于处理需要根据时间类型对某一列进行分组分析的数据列表
     *
     * @param dataList   含有待处理数据的列表，每个元素是一个Map，键为列名，值为对应的数据项
     * @param className  MapReduce作业的主类名称
     * @param columnName 需要进行分组分析的列名
     * @throws Exception 如果作业运行过程中出现错误，则抛出异常
     */
    void runMapReduceJobForGroup(List<Map<String, Object>> dataList, String className, String columnName) throws Exception;
}
