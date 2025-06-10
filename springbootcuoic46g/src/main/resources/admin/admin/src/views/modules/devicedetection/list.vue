<template>
	<div class="main-content" :style='{"padding":"20px 30px","fontSize":"15px"}'>
		<!-- 列表页 -->
		<template v-if="showFlag">
			<el-form class="center-form-pv" :style='{"margin":"0 10px 20px"}' :inline="true" :model="searchForm">
				<el-row :style='{"padding":"10px","background":"#fff","display":"flex"}' >
				</el-row>

				<el-row class="actions" :style='{"padding":"10px","margin":"0","flexWrap":"wrap","background":"#fff","display":"flex"}'>
					<el-button class="add" v-if="isAuth('devicedetection','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia14" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","height":"34px"}'></span>
						新增
					</el-button>
					<el-button class="del" v-if="isAuth('devicedetection','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","height":"34px"}'></span>
						删除
					</el-button>

					<el-button class="btn18" v-if="isAuth('devicedetection','导入')" type="success" @click="importHandler()">
						<span class="icon iconfont icon-daoru11" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						导入
					</el-button>
					<el-button class="btn18" v-if="isAuth('devicedetection','上传模板')" type="success" @click="importClcik">
						<span class="icon iconfont icon-shangchuan8" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						上传模板
					</el-button>
					<el-button class="btn18" v-if="isAuth('devicedetection','下载模板')" type="success" @click="download('upload/devicedetection_template.xlsx')">
						<span class="icon iconfont icon-xiazai15" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						下载模板
					</el-button>

					<download-excel v-if="isAuth('devicedetection','导出')" class="export-excel-wrapper" :data = "dataList" :fields = "json_fields" name = "设备检测.xls">
						<!-- 导出excel -->
						<el-button class="btn18" type="success">
							<span class="icon iconfont icon-daochu17" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
							导出
						</el-button>
					</download-excel>
					<el-button class="btn18" type="success" @click="cleanClick()">
						<span class="icon iconfont icon-shuju19" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						数据清洗
					</el-button>
					<el-button class="btn18" v-if="isAuth('devicedetection','工作温度')" type="success" @click="chartDialog1()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						工作温度
					</el-button>
					<el-button class="btn18" v-if="isAuth('devicedetection','工作压力')" type="success" @click="chartDialog2()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						工作压力
					</el-button>
					<el-button class="btn18" v-if="isAuth('devicedetection','设备数据')" type="success" @click="chartDialog3()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						设备数据
					</el-button>
					<el-button class="btn18" v-if="isAuth('devicedetection','工作时长')" type="success" @click="chartDialog4()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						工作时长
					</el-button>
					<el-button class="btn18" v-if="isAuth('devicedetection','防护等级')" type="success" @click="chartDialog5()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						防护等级
					</el-button>
					<el-button class="btn18" v-if="isAuth('devicedetection','设备状态')" type="success" @click="chartDialog6()">
						<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#5BAAFF","height":"34px"}'></span>
						设备状态
					</el-button>
				</el-row>
			</el-form>
			<div :style='{"width":"100%","padding":"0 10px","fontSize":"15px","color":"#000"}'>
				<el-table class="tables"
					:stripe='true'
					:style='{"borderColor":"#eee","borderRadius":"5px 5px 0 0","borderWidth":"1px 0 0 1px","background":"#fff","width":"100%","fontSize":"inherit","borderStyle":"solid"}' 
					:border='true'
					v-if="isAuth('devicedetection','查看')"
					:data="dataList"
					v-loading="dataListLoading"
				@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'  
						prop="title"
						label="名称">
						<template slot-scope="scope">
							{{scope.row.title}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="ratedvoltage"
						label="额定电压(kv)">
						<template slot-scope="scope">
							{{scope.row.ratedvoltage}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="ratedfrequencyhz"
						label="额定频率(Hz)">
						<template slot-scope="scope">
							{{scope.row.ratedfrequencyhz}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="ratedcurrent"
						label="额定电流(A)">
						<template slot-scope="scope">
							{{scope.row.ratedcurrent}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="ratedshortcircuitbreakingcurrentka"
						label="额定短路开断电流(kA)">
						<template slot-scope="scope">
							{{scope.row.ratedshortcircuitbreakingcurrentka}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="effectivevalueofratedshor"
						label="额定短时耐受电流有效值(kA)">
						<template slot-scope="scope">
							{{scope.row.effectivevalueofratedshor}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="ratedshortcircuitclosingcurrent"
						label="额定短路关合电流(KA)">
						<template slot-scope="scope">
							{{scope.row.ratedshortcircuitclosingcurrent}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="ratedpeakwithstandcurrentka"
						label="额定峰值耐受电流(kA)">
						<template slot-scope="scope">
							{{scope.row.ratedpeakwithstandcurrentka}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="powerfrequencywithstan"
						label="工频耐受电压(kV)">
						<template slot-scope="scope">
							{{scope.row.powerfrequencywithstan}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="lightningimpulsewithstan"
						label="雷电冲击耐受电压(kV)">
						<template slot-scope="scope">
							{{scope.row.lightningimpulsewithstan}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="protectiongrade"
						label="防护等级">
						<template slot-scope="scope">
							{{scope.row.protectiongrade}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="equipmentstatus"
						label="设备状态">
						<template slot-scope="scope">
							{{scope.row.equipmentstatus}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="runningtime"
						label="工作时长">
						<template slot-scope="scope">
							{{scope.row.runningtime}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="operationtemperature"
						label="工作温度">
						<template slot-scope="scope">
							{{scope.row.operationtemperature}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="workingpressure"
						label="工作压力">
						<template slot-scope="scope">
							{{scope.row.workingpressure}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="detectiontime"
						label="检测时间">
						<template slot-scope="scope">
							{{scope.row.detectiontime}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="zhanghao"
						label="账号">
						<template slot-scope="scope">
							{{scope.row.zhanghao}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'  
						prop="shebeibiaozhun"
						label="设备标准">
						<template slot-scope="scope">
							{{scope.row.shebeibiaozhun}}
						</template>
					</el-table-column>
					<el-table-column  :resizable='true' prop="imgurl" width="200" label="图片">
						<template slot-scope="scope">
							<div v-if="scope.row.imgurl">
								<img v-if="scope.row.imgurl.substring(0,4)=='http'&&scope.row.imgurl.split(',w').length>1" :src="scope.row.imgurl" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.imgurl)">
								<img v-else-if="scope.row.imgurl.substring(0,4)=='http'" :src="scope.row.imgurl.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.imgurl.split(',')[0])">
								<img v-else :src="$base.url+scope.row.imgurl.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.imgurl.split(',')[0])">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('devicedetection','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="edit" v-if=" isAuth('devicedetection','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								修改
							</el-button>




							<el-button class="del" v-if="isAuth('devicedetection','删除') " type="primary" @click="deleteHandler(scope.row.id )">
								<span class="icon iconfont icon-xihuan" :style='{"margin":"0 2px","fontSize":"14px","color":"#fff","display":"none","height":"40px"}'></span>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="< "
				next-text="> "
				:hide-on-single-page="false"
				:style='{"border":" 1px solid #F1F1F1","padding":"0 20px","margin":"20px auto 0","whiteSpace":"nowrap","color":"#000000","textAlign":"center","display":"flex","justifyContent":"space-between","borderRadius":"5px","background":"#fff","width":"750px","fontSize":"12","fontWeight":"400","height":"50px"}'
			></el-pagination>
		</template>
		
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>



		<el-dialog title="导入" :visible.sync="importVisiable" width="50%">
			<el-form ref="form" :model="form" label-width="80px">
				<el-form-item class="upload" label="文件" prop="excelFile">
				  <excel-file-upload
				  tip="点击上传直接导入excel文件"
				  action="devicedetection/importExcel"
				  :limit="1"
				  :fileUrls="importUrl"
				  @change="importChange"
				  ></excel-file-upload>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="importHandler()">关 闭</el-button>
			</span>
		</el-dialog>
		<el-dialog title="上传模板" :visible.sync="importVis" width="50%">
			<el-form ref="form" :model="importForm" label-width="80px">
				<el-form-item class="upload" label="文件" prop="excelFile">
					<el-upload class="upload-demo" drag :action="$base.url + 'file/upload?type=devicedetection_template'"
						:show-file-list="false" :on-success="importSuccess">
						<i class="el-icon-upload"></i>
						<div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
					</el-upload>
				</el-form-item>
			</el-form>
			<span slot="footer" class="dialog-footer">
				<el-button @click="importVis=false">关 闭</el-button>
			</span>
		</el-dialog>

		<el-dialog
			:visible.sync="chartVisiable1"
			width="800">
			<div id="operationtemperatureChart1" style="width:100%;height:600px;"></div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog1">返回</el-button>
			</span>
		</el-dialog>
		<el-dialog
			:visible.sync="chartVisiable2"
			width="800">
			<div id="workingpressureChart2" style="width:100%;height:600px;"></div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog2">返回</el-button>
			</span>
		</el-dialog>
		<el-dialog
			:visible.sync="chartVisiable3"
			width="800">
			<div id="titleChart3" style="width:100%;height:600px;"></div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog3">返回</el-button>
			</span>
		</el-dialog>
		<el-dialog
			:visible.sync="chartVisiable4"
			width="800">
			<div id="runningtimeChart4" style="width:100%;height:600px;"></div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog4">返回</el-button>
			</span>
		</el-dialog>
		<el-dialog
			:visible.sync="chartVisiable5"
			width="800">
			<div id="protectiongradeChart5" style="width:100%;height:600px;"></div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog5">返回</el-button>
			</span>
		</el-dialog>
		<el-dialog
			:visible.sync="chartVisiable6"
			width="800">
			<div id="equipmentstatusChart6" style="width:100%;height:600px;"></div>
			<span slot="footer" class="dialog-footer">
				<el-button @click="chartDialog6">返回</el-button>
			</span>
		</el-dialog>

		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	import * as echarts from 'echarts'
	import chinaJson from "@/components/echarts/china.json";
	import axios from 'axios';
	import AddOrUpdate from "./add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				searchForm: {
					key: ""
				},
				form:{},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				importVisiable: false,
				importVis: false,
				importForm: {},
				importUrl: '',
				chartVisiable1: false,
				line: {"backgroundColor":"","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":15,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"minInterval":1,"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":30,"padding":0,"shadowOffsetX":0,"margin":10,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"truncate","borderRadius":0,"borderWidth":0,"width":120,"interval":0,"fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"","shadowBlur":0,"background":"red","width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#5470c6","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"#333","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"showSymbol":true,"symbol":"emptyCircle","symbolSize":4},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"}},
				bar: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":12,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"minInterval":1,"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":30,"padding":0,"shadowOffsetX":0,"margin":10,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"truncate","borderRadius":0,"borderWidth":0,"width":120,"interval":0,"fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#00ff00","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"#333","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"barWidth":"auto","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"colorBy":"data","barCategoryGap":"20%"},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"base":{"animate":false,"interval":2000}},
				pie: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#5470c6","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"title":{"borderType":"solid","padding":[5,0,0,0],"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":[5,0,0,0],"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":0,"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#666","color":"inherit","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#666","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
				funnel: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#5470c6","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"title":{"borderType":"solid","padding":2,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"center","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#666","textShadowColor":"transparent","fontSize":14,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"#ccc","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":5,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"left","borderWidth":0,"width":"auto","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":20,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#000","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#fff","color":"","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#fff","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
				boardBase: {"funnelNum":8,"lineNum":12,"gaugeNum":8,"barNum":12,"pieNum":8},
				gauge: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#5470c6","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"title":{"top":"top","left":"left","textStyle":{"fontSize":14,"lineHeight":24,"color":"#333","fontWeight":600}},"series":{"pointer":{"offsetCenter":[0,"10%"],"icon":"path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z","width":8,"length":"80%"},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"opacity":0.5,"shadowBlur":1,"shadowColor":"#000"},"roundCap":true},"anchor":{"show":true,"itemStyle":{"color":"inherit"},"size":18,"showAbove":true},"emphasis":{"disabled":false},"progress":{"show":true,"roundCap":true,"overlap":true},"splitNumber":25,"detail":{"formatter":"{value}","backgroundColor":"inherit","color":"#fff","borderRadius":3,"width":20,"fontSize":12,"height":10},"title":{"fontSize":14},"animation":true}},
				chartVisiable2: false,
				chartVisiable3: false,
				chartVisiable4: false,
				chartVisiable5: false,
				chartVisiable6: false,
				addOrUpdateFlag:false,
				layouts: ["total","prev","pager","next","sizes","jumper"],
//导出excel
				json_fields: {
					"名称": "title",    //常规字段
					"额定电压(kv)": "ratedvoltage",    //常规字段
					"额定频率(Hz)": "ratedfrequencyhz",    //常规字段
					"额定电流(A)": "ratedcurrent",    //常规字段
					"额定短路开断电流(kA)": "ratedshortcircuitbreakingcurrentka",    //常规字段
					"额定短时耐受电流有效值(kA)": "effectivevalueofratedshor",    //常规字段
					"额定短路关合电流(KA)": "ratedshortcircuitclosingcurrent",    //常规字段
					"额定峰值耐受电流(kA)": "ratedpeakwithstandcurrentka",    //常规字段
					"工频耐受电压(kV)": "powerfrequencywithstan",    //常规字段
					"雷电冲击耐受电压(kV)": "lightningimpulsewithstan",    //常规字段
					"防护等级": "protectiongrade",    //常规字段
					"设备状态": "equipmentstatus",    //常规字段
					"工作时长": "runningtime",    //常规字段
					"工作温度": "operationtemperature",    //常规字段
					"工作压力": "workingpressure",    //常规字段
					"检测时间": "detectiontime",    //常规字段
					"账号": "zhanghao",    //常规字段
					"设备标准": "shebeibiaozhun",    //常规字段
					"图片": "imgurl",    //常规字段
				},
				json_meta: [
					[
						{
							" key ": " charset ",
							" value ": " utf- 8 "
						}
					]
				],
				previewImg: '',
				previewVisible: false,
			};
		},
		created() {
			this.init();
			this.getDataList();
			this.contentStyleChange();
		},
		mounted() {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
		},
		components: {
			AddOrUpdate,
		},
		methods: {
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
				
			},
			contentStyleChange() {
				this.contentPageStyleChange()
			},
			// 分页
			contentPageStyleChange(){
				let arr = []

				// if(this.contents.pageTotal) arr.push('total')
				// if(this.contents.pageSizes) arr.push('sizes')
				// if(this.contents.pagePrevNext){
				//   arr.push('prev')
				//   if(this.contents.pagePager) arr.push('pager')
				//   arr.push('next')
				// }
				// if(this.contents.pageJumper) arr.push('jumper')
				// this.layouts = arr.join()
				// this.contents.pageEachNum = 10
			},


			// 统计接口
			chartDialog1() {
				this.chartVisiable1 = !this.chartVisiable1;
				this.$nextTick(()=>{
					var operationtemperatureChart1 = echarts.init(document.getElementById("operationtemperatureChart1"),'macarons');
					this.$http({
						url: `devicedetection/value/detectiontime/operationtemperature/日`,
						method: "get",
					}).then(({ data }) => {
						if (data && data.code === 0) {
							let res = data.data;
							let xAxis = [];
							let yAxis = [];
							let pArray = []
							for(let i=0;i<res.length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis.push(res[i].detectiontime);
								yAxis.push(parseFloat((res[i].total)));
								pArray.push({
									value: parseFloat((res[i].total)),
									name: res[i].detectiontime
								})
							}
							var option = {};
							let titleObj = this.line.title
							titleObj.text = '工作温度'
							
							const legendObj = this.line.legend
							let tooltipObj = { trigger: 'item',formatter: '{b} : {c}'}
							tooltipObj = Object.assign(tooltipObj , this.line.tooltip?this.line.tooltip:{})
							let xAxisObj = this.line.xAxis
							xAxisObj.type = 'category'
							
							xAxisObj.data = xAxis
							
							let yAxisObj = this.line.yAxis
							yAxisObj.type = 'value'
							const gridObj = this.line.grid
							let seriesObj = {
								data: yAxis,
								type: 'line',
								areaStyle: {},
								smooth: true
							}
							seriesObj = Object.assign(seriesObj , this.line.series)
							option = {
								backgroundColor: this.line.backgroundColor,
								color: this.line.color,
								title: titleObj,
								legend: legendObj,
								grid: gridObj,
								tooltip: tooltipObj,
								xAxis: xAxisObj,
								yAxis: yAxisObj,
								series: [seriesObj]
							};
							// 使用刚指定的配置项和数据显示图表。
							operationtemperatureChart1.setOption(option);
							  //根据窗口的大小变动图表
							window.onresize = function() {
								operationtemperatureChart1.resize();
							};
						}else{
							this.$message({
								message: data.msg,
								type: "warning",
								duration: 1500,
							})
						}
					});
				})
			},

			// 统计接口
			chartDialog2() {
				this.chartVisiable2 = !this.chartVisiable2;
				this.$nextTick(()=>{

					var workingpressureChart2 = echarts.init(document.getElementById("workingpressureChart2"),'macarons');
					this.$http({
						url: `devicedetection/value/detectiontime/workingpressure/日`,
						method: "get",
					}).then(({ data }) => {
						if (data && data.code === 0) {
							let res = data.data;
							let xAxis = [];
							let yAxis = [];
							let pArray = []
							for(let i=0;i<res.length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis.push(res[i].detectiontime);
								yAxis.push(parseFloat((res[i].total)));
								pArray.push({
									value: parseFloat((res[i].total)),
									name: res[i].detectiontime
								})
							}
							var option = {};
							let titleObj = this.line.title
							titleObj.text = '工作压力'
							
							const legendObj = this.line.legend
							let tooltipObj = { trigger: 'item',formatter: '{b} : {c}'}
							tooltipObj = Object.assign(tooltipObj , this.line.tooltip?this.line.tooltip:{})
							
							let xAxisObj = this.line.xAxis
							xAxisObj.type = 'category'
							
							xAxisObj.data = xAxis
							
							let yAxisObj = this.line.yAxis
							yAxisObj.type = 'value'
							
							let seriesObj = {
								data: yAxis,
								type: 'line',
							}
							seriesObj = Object.assign(seriesObj , this.line.series)
							const gridObj = this.line.grid
							
							option = {
								backgroundColor: this.line.backgroundColor,
								color: this.line.color,
								title: titleObj,
								legend: legendObj,
								tooltip: tooltipObj,
								xAxis: xAxisObj,
								yAxis: yAxisObj,
								series: [seriesObj],
								grid: gridObj,
							};
							// 使用刚指定的配置项和数据显示图表。
							workingpressureChart2.setOption(option);
							//根据窗口的大小变动图表
							window.onresize = function() {
								workingpressureChart2.resize();
							};
						}else{
							this.$message({
								message: data.msg,
								type: "warning",
								duration: 1500,
							})
						}
					});
				})
			},

			// 统计接口
			chartDialog3() {
				this.chartVisiable3 = !this.chartVisiable3;
				this.$nextTick(()=>{

					var titleChart3 = echarts.init(document.getElementById("titleChart3"),'macarons');
					this.$http({
						url: `devicedetection/valueMul/title?yColumnNameMul=ratedvoltage,ratedfrequencyhz,ratedcurrent,ratedshortcircuitbreakingcurrentka,effectivevalueofratedshor,ratedshortcircuitclosingcurrent,ratedpeakwithstandcurrentka`,
						method: "get",
					}).then(({ data }) => {
						if (data && data.code === 0) {
							let res = data.data;
							let xAxis1 = [];
							let yAxis1 = [];
							let pArray1 = []
							for(let i=0;i<res[0].length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis1.push(res[0][i].title);
								yAxis1.push(parseFloat((res[0][i].total)));
								pArray1.push({
									value: parseFloat((res[0][i].total)),
									name: res[0][i].title
								})
							}
							let xAxis2 = [];
							let yAxis2 = [];
							let pArray2 = []
							for(let i=0;i<res[1].length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis2.push(res[1][i].title);
								yAxis2.push(parseFloat((res[1][i].total)));
								pArray2.push({
									value: parseFloat((res[1][i].total)),
									name: res[1][i].title
								})
							}
							let xAxis3 = [];
							let yAxis3 = [];
							let pArray3 = []
							for(let i=0;i<res[2].length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis3.push(res[2][i].title);
								yAxis3.push(parseFloat((res[2][i].total)));
								pArray3.push({
									value: parseFloat((res[2][i].total)),
									name: res[2][i].title
								})
							}
							let xAxis4 = [];
							let yAxis4 = [];
							let pArray4 = []
							for(let i=0;i<res[3].length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis4.push(res[3][i].title);
								yAxis4.push(parseFloat((res[3][i].total)));
								pArray4.push({
									value: parseFloat((res[3][i].total)),
									name: res[3][i].title
								})
							}
							let xAxis5 = [];
							let yAxis5 = [];
							let pArray5 = []
							for(let i=0;i<res[4].length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis5.push(res[4][i].title);
								yAxis5.push(parseFloat((res[4][i].total)));
								pArray5.push({
									value: parseFloat((res[4][i].total)),
									name: res[4][i].title
								})
							}
							let xAxis6 = [];
							let yAxis6 = [];
							let pArray6 = []
							for(let i=0;i<res[5].length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis6.push(res[5][i].title);
								yAxis6.push(parseFloat((res[5][i].total)));
								pArray6.push({
									value: parseFloat((res[5][i].total)),
									name: res[5][i].title
								})
							}
							let xAxis7 = [];
							let yAxis7 = [];
							let pArray7 = []
							for(let i=0;i<res[6].length;i++){
								if(this.boardBase&&i==this.boardBase.lineNum){
									break;
								}
								xAxis7.push(res[6][i].title);
								yAxis7.push(parseFloat((res[6][i].total)));
								pArray7.push({
									value: parseFloat((res[6][i].total)),
									name: res[6][i].title
								})
							}
							var option = {};
							let titleObj = this.line.title
							titleObj.text = '设备数据'
							const legendObj = this.line.legend
							legendObj.data = [
								'额定电压'.split('/').length>1?'额定电压'.split('/')[0]:'额定电压',
								'额定频率'.split('/').length>1?'额定频率'.split('/')[0]:'额定频率',
								'额定电流'.split('/').length>1?'额定电流'.split('/')[0]:'额定电流',
								'额定短路开断电流'.split('/').length>1?'额定短路开断电流'.split('/')[0]:'额定短路开断电流',
								'额定短路关合电流'.split('/').length>1?'额定短路关合电流'.split('/')[0]:'额定短路关合电流',
								'额定短时耐受电流有效值'.split('/').length>1?'额定短时耐受电流有效值'.split('/')[0]:'额定短时耐受电流有效值',
								'额定峰值耐受电流'.split('/').length>1?'额定峰值耐受电流'.split('/')[0]:'额定峰值耐受电流',
							]
							let xAxisObj = this.line.xAxis
							xAxisObj.type = 'category'
							
							xAxisObj.data = xAxis1
							let yAxisObj = this.line.yAxis
							yAxisObj.type = 'value'
							let seriesObj = [
								{
									data: yAxis1,
									type: '额定电压'.split('/').length>1?'额定电压'.split('/')[1]:'line',
									name: '额定电压'.split('/').length>1?'额定电压'.split('/')[0]:'额定电压',
								},
								{
									data: yAxis2,
									type: '额定频率'.split('/').length>1?'额定频率'.split('/')[1]:'line',
									name: '额定频率'.split('/').length>1?'额定频率'.split('/')[0]:'额定频率',
								},
								{
									data: yAxis3,
									type: '额定电流'.split('/').length>1?'额定电流'.split('/')[1]:'line',
									name: '额定电流'.split('/').length>1?'额定电流'.split('/')[0]:'额定电流',
								},
								{
									data: yAxis4,
									type: '额定短路开断电流'.split('/').length>1?'额定短路开断电流'.split('/')[1]:'line',
									name: '额定短路开断电流'.split('/').length>1?'额定短路开断电流'.split('/')[0]:'额定短路开断电流',
								},
								{
									data: yAxis5,
									type: '额定短路关合电流'.split('/').length>1?'额定短路关合电流'.split('/')[1]:'line',
									name: '额定短路关合电流'.split('/').length>1?'额定短路关合电流'.split('/')[0]:'额定短路关合电流',
								},
								{
									data: yAxis6,
									type: '额定短时耐受电流有效值'.split('/').length>1?'额定短时耐受电流有效值'.split('/')[1]:'line',
									name: '额定短时耐受电流有效值'.split('/').length>1?'额定短时耐受电流有效值'.split('/')[0]:'额定短时耐受电流有效值',
								},
								{
									data: yAxis7,
									type: '额定峰值耐受电流'.split('/').length>1?'额定峰值耐受电流'.split('/')[1]:'line',
									name: '额定峰值耐受电流'.split('/').length>1?'额定峰值耐受电流'.split('/')[0]:'额定峰值耐受电流',
								},
							]
							for(let i=0;i<seriesObj.length;i++){
								seriesObj[i] = Object.assign(seriesObj[i] , this.line.series)
							}
							let tooltipObj = {trigger: 'axis'}
							tooltipObj = Object.assign(tooltipObj , this.line.tooltip?this.line.tooltip:{})
							const gridObj = this.line.grid
							option = {
								backgroundColor: this.line.backgroundColor,
								color: this.line.color,
								title: titleObj,
								legend: legendObj,
								tooltip: tooltipObj,
								xAxis: xAxisObj,
								yAxis: yAxisObj,
								series: seriesObj,
								grid: gridObj
							};
							// 使用刚指定的配置项和数据显示图表。
							titleChart3.setOption(option);
							//根据窗口的大小变动图表
							window.onresize = function() {
								titleChart3.resize();
							};
						}else{
							this.$message({
								message: data.msg,
								type: "warning",
								duration: 1500,
							})
						}
					});
				})
			},

			// 统计接口
			chartDialog4() {
				this.chartVisiable4 = !this.chartVisiable4;
				this.$nextTick(()=>{

					var runningtimeChart4 = echarts.init(document.getElementById("runningtimeChart4"),'macarons');
					this.$http({
						url: `devicedetection/value/title/runningtime`,
						method: "get",
					}).then(({ data }) => {
						if (data && data.code === 0) {
							let res = data.data;
							let xAxis = [];
							let yAxis = [];
							let pArray = []
							for(let i=0;i<res.length;i++){
								if(this.boardBase&&i==this.boardBase.barNum){
									break;
								}
								xAxis.push(res[i].title);
								yAxis.push(parseFloat((res[i].total)));
								pArray.push({
									value: parseFloat((res[i].total)),
									name: res[i].title
								})
							}
							var option = {};
							let titleObj = this.bar.title
							titleObj.text = '工作时长'
							
							const legendObj = this.bar.legend
							let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
							tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
				
							let xAxisObj = this.bar.xAxis
							xAxisObj.type = 'category'
							xAxisObj.data = xAxis
							
							let yAxisObj = this.bar.yAxis
							yAxisObj.type = 'value'
				
							let seriesObj = {
								data: yAxis,
								type: 'bar',
							}
							seriesObj = Object.assign(seriesObj , this.bar.series)
							const gridObj = this.bar.grid

							option = {
								backgroundColor: this.bar.backgroundColor,
								color: this.bar.color,
								title: titleObj,
								legend: legendObj,
								tooltip: tooltipObj,
								xAxis: xAxisObj,
								yAxis: yAxisObj,
								series: [seriesObj],
								grid: gridObj
							};
							// 使用刚指定的配置项和数据显示图表。
							runningtimeChart4.setOption(option);
							//根据窗口的大小变动图表
							window.onresize = function() {
								runningtimeChart4.resize();
							};
						}else{
							this.$message({
								message: data.msg,
								type: "warning",
								duration: 1500,
							})
						}
					});
				})
			},
			// 统计接口
			chartDialog5() {
				this.chartVisiable5 = !this.chartVisiable5;
				this.$nextTick(()=>{

					var protectiongradeChart5 = echarts.init(document.getElementById("protectiongradeChart5"),'macarons');
					this.$http({
						url: "devicedetection/group/protectiongrade",
						method: "get",
					}).then(({ data }) => {
						if (data && data.code === 0) {
							let res = data.data;
							let xAxis = [];
							let yAxis = [];
							let pArray = []
							for(let i=0;i<res.length;i++){
								if(this.boardBase&&i==this.boardBase.pieNum){
									break;
								}
								xAxis.push(res[i].protectiongrade);
								yAxis.push(parseFloat((res[i].total)));
								pArray.push({
									value: parseFloat((res[i].total)),
									name: res[i].protectiongrade
								})
							}
							var option = {};
							let titleObj = this.pie.title
							titleObj.text = '防护等级'
							
							const legendObj = this.pie.legend
							let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
							tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
							
							let seriesObj = {
								type: 'pie',
								radius: '55%',
								center: ['50%', '60%'],
								data: pArray,
								emphasis: {
									itemStyle: {
										shadowBlur: 10,
										shadowOffsetX: 0,
										shadowColor: 'rgba(0, 0, 0, 0.5)'
									}
								}
							}
							seriesObj = Object.assign(seriesObj , this.pie.series)
							const gridObj = this.pie.grid
							option = {
								backgroundColor: this.pie.backgroundColor,
								color: this.pie.color,
								title: titleObj,
								legend: legendObj,
								tooltip: tooltipObj,
								series: [seriesObj],
								grid: gridObj
							};
							// 使用刚指定的配置项和数据显示图表。
							protectiongradeChart5.setOption(option);
							//根据窗口的大小变动图表
							window.onresize = function() {
								protectiongradeChart5.resize();
							};
						}else{
							this.$message({
								message: data.msg,
								type: "warning",
								duration: 1500,
							})
						}
					});
				})
			},
			// 统计接口
			chartDialog6() {
				this.chartVisiable6 = !this.chartVisiable6;
				this.$nextTick(()=>{

					var equipmentstatusChart6 = echarts.init(document.getElementById("equipmentstatusChart6"),'macarons');
					this.$http({
						url: "devicedetection/group/equipmentstatus",
						method: "get",
					}).then(({ data }) => {
						if (data && data.code === 0) {
							let res = data.data;
							let xAxis = [];
							let yAxis = [];
							let pArray = []
							for(let i=0;i<res.length;i++){
								if(this.boardBase&&i==this.boardBase.pieNum){
									break;
								}
								xAxis.push(res[i].equipmentstatus);
								yAxis.push(parseFloat((res[i].total)));
								pArray.push({
									value: parseFloat((res[i].total)),
									name: res[i].equipmentstatus
								})
							}
							var option = {};
							let titleObj = this.pie.title
							titleObj.text = '设备状态'
							
							const legendObj = this.pie.legend
							let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
							tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
							
							let seriesObj = {
								type: 'pie',
								radius: ['25%', '55%'],
								center: ['50%', '60%'],
								data: pArray,
								emphasis: {
									itemStyle: {
										shadowBlur: 10,
										shadowOffsetX: 0,
										shadowColor: 'rgba(0, 0, 0, 0.5)'
									}
								}
							}
							seriesObj = Object.assign(seriesObj , this.pie.series)
							const gridObj = this.pie.grid
							option = {
								backgroundColor: this.pie.backgroundColor,
								color: this.pie.color,
								title: titleObj,
								legend: legendObj,
								tooltip: tooltipObj,
								series: [seriesObj],
								grid: gridObj
							};
							// 使用刚指定的配置项和数据显示图表。
							equipmentstatusChart6.setOption(option);
							//根据窗口的大小变动图表
							window.onresize = function() {
								equipmentstatusChart6.resize();
							};
						}else{
							this.$message({
								message: data.msg,
								type: "warning",
								duration: 1500,
							})
						}
					});
				})
			},
			init () {
				let urlratedvoltage = 'devicedetection/remind/ratedvoltage/1';
				urlratedvoltage+=`?remindend=10`,
				
				urlratedvoltage+=`&remindstart=38`,

				this.$http({
					url: urlratedvoltage,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `工作压力预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urlratedfrequencyhz = 'devicedetection/remind/ratedfrequencyhz/1';
				urlratedfrequencyhz+=`?remindend=50`,
				
				urlratedfrequencyhz+=`&remindstart=50`,

				this.$http({
					url: urlratedfrequencyhz,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `工作温度预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urlratedcurrent = 'devicedetection/remind/ratedcurrent/1';
				urlratedcurrent+=`?remindend=100`,
				
				urlratedcurrent+=`&remindstart=200`,

				this.$http({
					url: urlratedcurrent,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `工作时长预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urlratedshortcircuitbreakingcurrentka = 'devicedetection/remind/ratedshortcircuitbreakingcurrentka/1';
				urlratedshortcircuitbreakingcurrentka+=`?remindend=20`,
				
				urlratedshortcircuitbreakingcurrentka+=`&remindstart=100`,

				this.$http({
					url: urlratedshortcircuitbreakingcurrentka,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `雷电冲击耐受电压(kV)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urleffectivevalueofratedshor = 'devicedetection/remind/effectivevalueofratedshor/1';
				urleffectivevalueofratedshor+=`?remindend=20`,
				
				urleffectivevalueofratedshor+=`&remindstart=55`,

				this.$http({
					url: urleffectivevalueofratedshor,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `工频耐受电压(kV)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urlratedshortcircuitclosingcurrent = 'devicedetection/remind/ratedshortcircuitclosingcurrent/1';
				urlratedshortcircuitclosingcurrent+=`?remindend=20`,
				
				urlratedshortcircuitclosingcurrent+=`&remindstart=50`,

				this.$http({
					url: urlratedshortcircuitclosingcurrent,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `额定峰值耐受电流(kA)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urlratedpeakwithstandcurrentka = 'devicedetection/remind/ratedpeakwithstandcurrentka/1';
				urlratedpeakwithstandcurrentka+=`?remindend=50`,
				
				urlratedpeakwithstandcurrentka+=`&remindstart=110`,

				this.$http({
					url: urlratedpeakwithstandcurrentka,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `额定短路关合电流(KA)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urlpowerfrequencywithstan = 'devicedetection/remind/powerfrequencywithstan/1';
				urlpowerfrequencywithstan+=`?remindend=45`,
				
				urlpowerfrequencywithstan+=`&remindstart=50`,

				this.$http({
					url: urlpowerfrequencywithstan,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `额定短时耐受电流有效值(kA)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urllightningimpulsewithstan = 'devicedetection/remind/lightningimpulsewithstan/1';
				urllightningimpulsewithstan+=`?remindend=80`,
				
				urllightningimpulsewithstan+=`&remindstart=85`,

				this.$http({
					url: urllightningimpulsewithstan,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `额定短路开断电流(kA)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urlrunningtime = 'devicedetection/remind/runningtime/1';
				urlrunningtime+=`?remindend=60`,
				
				urlrunningtime+=`&remindstart=60`,

				this.$http({
					url: urlrunningtime,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `额定电流(A)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urloperationtemperature = 'devicedetection/remind/operationtemperature/1';
				urloperationtemperature+=`?remindend=-10`,
				
				urloperationtemperature+=`&remindstart=40`,

				this.$http({
					url: urloperationtemperature,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `额定频率(Hz)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
				let urlworkingpressure = 'devicedetection/remind/workingpressure/1';
				urlworkingpressure+=`?remindend=100`,
				
				urlworkingpressure+=`&remindstart=100`,

				this.$http({
					url: urlworkingpressure,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code == 0 && data.count>0 ) {
						// const h = this.$createElement;
						// var name = data.data.join(',')
						// this.$msgbox({
						// 	message: h('p', null, [
						// 		h('div', { style: 'text-align: center;font-weight: bold;font-size: 20px;padding: 20px 0' }, `额定电压(kv)预警`),
						// 		h('p', null, [
						// 			h('div', { style: 'text-align: center;font-weight: bold;padding: 0 0 10px;font-size: 16px' }, `${data.count}条记录到达预警`),
						// 			h('div', { style: 'text-align: center' }, [
						// 				h('span', null, `包含：`),
						// 				h('span', { style: 'color: teal;font-weight: bold' }, `${name}`)
						// 			])
						// 		])
						// 	]),
						// 	showCancelButton: false,
						// })
					}
				});
			},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},

			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
					order: 'desc',
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: "devicedetection/page",
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id,type) {
				this.showFlag = false;
				this.addOrUpdateFlag = true;
				this.crossAddOrUpdateFlag = false;
				if(type!='info'&&type!='msg'){
					type = 'else';
				}
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id,type);
				});
			},
			importChange(){
				this.importHandler()
				this.getDataList()
			},
			importClcik() {
				this.importVis = true
			},
			importSuccess(e) {
				if(e.code==0){
					this.$message.success('上传成功');
					this.importVis = false
					
				}
			},
			importHandler() {
				this.importUrl = ''
				this.importVisiable = !this.importVisiable;
			},
			// 下载
			download(file){
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.$base.url + 'file/download?fileName=' + arr, {
					headers: {
						token: this.$storage.get('Token')
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] :'') + this.$base.name + '/file/download?fileName=' + arr, {
						headers: {
							token: this.$storage.get('Token')
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			// 删除
			async deleteHandler(id ) {
				var ids = id? [Number(id)]: this.dataListSelections.map(item => {
					return Number(item.id);
				});
				await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					await this.$http({
						url: "devicedetection/delete",
						method: "post",
						data: ids
					}).then(async ({ data }) => {
						if (data && data.code === 0) {
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
			
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},


			cleanClick() {
				this.$confirm(`是否进行数据清洗?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(()=>{
					let loading = null
					loading = Loading.service({
						target: this.$refs['roleMenuBox'],
						fullscreen: false,
						text: '数据清洗中...'
					})
					this.$http({
						url: 'devicedetection/cleanse',
						method: 'get'
					}).then(res=>{
						if(res.data&&res.data.code==0){
							if (loading) loading.close()
							this.$message({
								message: "数据清洗完成！",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.getDataList()
								}
							});
						}
					})
				})
			},
		}

	};
</script>
<style lang="scss" scoped>
	//导出excel
	.export-excel-wrapper{
		display: inline-block;
	}
	
	.center-form-pv {
		.el-date-editor.el-input {
			width: auto;
		}
	}
	
	.el-input {
		width: auto;
	}
	
	// form
	.center-form-pv .el-input {
		width: 100%;
	}
	.center-form-pv .el-input /deep/ .el-input__inner {
		border: 1px solid #BFBFBF;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.center-form-pv .el-select {
		width: 100%;
	}
	.center-form-pv .el-select /deep/ .el-input__inner {
		border: 1px solid #ddd;
		border-radius: 0px;
		padding: 0 10px;
		color: #666;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	.center-form-pv .el-date-editor {
		width: 100%;
	}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #ddd;
		border-radius: 0px;
		padding: 0 10px 0 30px;
		color: #666;
		width: 100%;
		font-size: 16px;
		height: 34px;
	}
	
	.center-form-pv .search {
		border: 0;
		cursor: pointer;
		align-self: center;
		padding: 0 10px;
		color: #fff;
		background: linear-gradient( 150deg, #59A8FF 0%, #8CEFFC 100%);
		width: auto;
		font-size: 16px;
		height: 34px;
	}
	
	.center-form-pv .search:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .add {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 10px;
		margin: 4px;
		color: #fff;
		background: #5BAAFF;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .add:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .del {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 10px;
		margin: 4px;
		color: #fff;
		background: #59b5ff;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .del:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .statis {
		border: 0px solid #ddd;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 10px;
		margin: 4px;
		color: #fff;
		background: #3bc1ff;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .statis:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .btn18 {
		border: 1px solid #5BAAFF;
		cursor: pointer;
		border-radius: 2px;
		padding: 0 10px;
		margin: 4px;
		color: #5BAAFF;
		background: #fff;
		width: auto;
		font-size: inherit;
		height: 34px;
	}
	
	.center-form-pv .actions .btn18:hover {
		opacity: 0.8;
	}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
		color: #999;
		font-weight: 500;
		width: 100%;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
		background: #F3F3F3;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
		padding: 4px 0;
		background: none;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
	}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
		padding: 0 0 0 2px;
		word-wrap: normal;
		color: #000000;
		white-space: normal;
		font-weight: 500;
		display: flex;
		vertical-align: middle;
		font-size: 16px;
		line-height: 24px;
		text-overflow: ellipsis;
		word-break: break-all;
		width: 100%;
		align-items: center;
		position: relative;
		min-width: 110px;
	}

	.el-table /deep/ .el-table__body-wrapper {
		position: relative;
	}
	.el-table /deep/ .el-table__body-wrapper tbody {
		width: 100%;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
		background: #fff;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #333;
		background: #fff;
		font-size: inherit;
		border-color: #E1E1E1;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
		.el-table /deep/ .el-table__body-wrapper tbody tr.el-table__row--striped td {
		background: #f8f8f8;
	}
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
		padding: 4px 0;
		color: #333;
		background: #f0f0f0;
		border-color: #eee;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #333;
		background: #fff;
		font-size: inherit;
		border-color: #E1E1E1;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
		padding: 0 0 0 5px;
		overflow: hidden;
		word-break: break-all;
		white-space: normal;
		font-size: inherit;
		line-height: 24px;
		text-overflow: ellipsis;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
		border: 1px solid #5BAAFF;
		cursor: pointer;
		border-radius: 5;
		padding: 0 5px;
		margin: 0 5px 5px 0;
		color: #5BAAFF;
		background: #EAFDFF;
		letter-spacing: 1;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
		border: 1px solid #5BAAFF;
		cursor: pointer;
		border-radius: 5;
		padding: 0 5px;
		margin: 0 5px 5px 0;
		color: #5BAAFF;
		background: #EAFDFF;
		letter-spacing: 1;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
		border: 1px solid #5BAAFF;
		cursor: pointer;
		border-radius: 5;
		padding: 0 5px;
		margin: 0 5px 5px 0;
		color: #5BAAFF;
		background: #EAFDFF;
		letter-spacing: 1;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
		border: 1px solid #5BAAFF;
		cursor: pointer;
		border-radius: 5;
		padding: 0 5px;
		margin: 0 5px 5px 0;
		color: #5BAAFF;
		background: #EAFDFF;
		letter-spacing: 1;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
		opacity: 0.8;
	}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
		margin: 0 10px 0 0;
		color: #666;
		font-weight: 400;
		vertical-align: top;
		font-size: inherit;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		min-width: 35px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-next {
		border: none;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #666;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		min-width: 35px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
		border: none;
		cursor: not-allowed;
		border-radius: 2px;
		padding: 0;
		margin: 0 5px;
		color: #C0C4CC;
		background: none;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: auto;
		height: 48px;
	}

	.main-content .el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
		display: flex;
		vertical-align: top;
	}

	.main-content .el-pagination /deep/ .el-pager .number {
		cursor: pointer;
		padding: 0 20px;
		color: #000000;
		background: none;
		font-weight: 400;
		display: flex;
		vertical-align: top;
		font-size: 16px;
		line-height: 100%;
		align-items: center;
		text-align: center;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
		cursor: pointer;
		padding: 0 20px;
		color: #FFFFFF;
		font-weight: 400;
		display: flex;
		vertical-align: top;
		font-size: 16px;
		line-height: 100%;
		border-radius: 5px;
		background: #5BAAFF;
		align-items: center;
		text-align: center;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
		cursor: pointer;
		padding: 0 20px;
		color: #FFFFFF;
		font-weight: 400;
		display: flex;
		vertical-align: top;
		font-size: 16px;
		line-height: 100%;
		border-radius: 5px;
		background: #5BAAFF;
		align-items: center;
		text-align: center;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #C0C4CC;
		width: 25px;
		font-size: 14px;
		line-height: 48px;
		text-align: center;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #606266;
		display: inline-block;
		vertical-align: top;
		font-size: 16px;
		line-height: 48px;
		height: 48px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 14px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #DCDFE6;
		cursor: pointer;
		padding: 0 3px;
		color: #606266;
		display: inline-block;
		font-size: 16px;
		line-height: 28px;
		border-radius: 3px;
		outline: 0;
		background: #FFF;
		width: 100%;
		text-align: center;
		height: 28px;
	}
	
	// list one
	.one .list1-view {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #157ed2;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-view:hover {
		opacity: 0.8;
	}
	
	.one .list1-edit {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #409eff;
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-edit:hover {
		opacity: 0.8;
	}
	
	.one .list1-del {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 15px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: rgba(255, 0, 0, 1);
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-del:hover {
		opacity: 0.8;
	}
	
	.one .list1-btn8 {
		border: 0;
		cursor: pointer;
		border-radius: 4px;
		padding: 0 24px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: rgba(255, 128, 0, 1);
		width: auto;
		font-size: 14px;
		height: 32px;
	}
	
	.one .list1-btn8:hover {
		opacity: 0.8;
	}
	
	.main-content .el-table .el-switch {
		display: inline-flex;
		vertical-align: middle;
		line-height: 30px;
		position: relative;
		align-items: center;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
		cursor: pointer;
		margin: 0 10px 0 0;
		color: #333;
		font-weight: 500;
		display: inline-block;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
		cursor: pointer;
		margin: 0 0 0 10px;
		color: #333;
		font-weight: 500;
		display: inline-block;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
		border: 1px solid #75c0d6;
		cursor: pointer;
		border-radius: 15px;
		margin: 0;
		background: #75c0d6;
		display: inline-block;
		width: 42px;
		box-sizing: border-box;
		transition: border-color .3s,background-color .3s;
		height: 20px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
		border-radius: 100%;
		top: 1px;
		left: 1px;
		background: #fff;
		width: 16px;
		position: absolute;
		transition: all .3s;
		height: 16px;
	}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
		margin: 0 0 0 -18px;
		left: 100%;
	}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
		cursor: pointer;
		display: inline-block;
		vertical-align: middle;
		font-size: 0;
		position: relative;
	}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
		margin: 0 3px;
		display: inline-block;
		font-size: 18px;
		position: relative;
		transition: .3s;
	}

</style>
