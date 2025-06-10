<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="名称" prop="title" >
					<el-input v-model="ruleForm.title" placeholder="名称" clearable  :readonly="ro.title"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="名称" prop="title" >
					<el-input v-model="ruleForm.title" placeholder="名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="额定电压(kv)" prop="ratedvoltage" >
					<el-input-number v-model="ruleForm.ratedvoltage" placeholder="额定电压(kv)" :disabled="ro.ratedvoltage"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="额定电压(kv)" prop="ratedvoltage" >
					<el-input v-model="ruleForm.ratedvoltage" placeholder="额定电压(kv)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="额定频率(Hz)" prop="ratedfrequencyhz" >
					<el-input-number v-model="ruleForm.ratedfrequencyhz" placeholder="额定频率(Hz)" :disabled="ro.ratedfrequencyhz"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="额定频率(Hz)" prop="ratedfrequencyhz" >
					<el-input v-model="ruleForm.ratedfrequencyhz" placeholder="额定频率(Hz)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="额定电流(A)" prop="ratedcurrent" >
					<el-input-number v-model="ruleForm.ratedcurrent" placeholder="额定电流(A)" :disabled="ro.ratedcurrent"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="额定电流(A)" prop="ratedcurrent" >
					<el-input v-model="ruleForm.ratedcurrent" placeholder="额定电流(A)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="额定短路开断电流(kA)" prop="ratedshortcircuitbreakingcurrentka" >
					<el-input-number v-model="ruleForm.ratedshortcircuitbreakingcurrentka" placeholder="额定短路开断电流(kA)" :disabled="ro.ratedshortcircuitbreakingcurrentka"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="额定短路开断电流(kA)" prop="ratedshortcircuitbreakingcurrentka" >
					<el-input v-model="ruleForm.ratedshortcircuitbreakingcurrentka" placeholder="额定短路开断电流(kA)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="额定短时耐受电流有效值(kA)" prop="effectivevalueofratedshor" >
					<el-input-number v-model="ruleForm.effectivevalueofratedshor" placeholder="额定短时耐受电流有效值(kA)" :disabled="ro.effectivevalueofratedshor"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="额定短时耐受电流有效值(kA)" prop="effectivevalueofratedshor" >
					<el-input v-model="ruleForm.effectivevalueofratedshor" placeholder="额定短时耐受电流有效值(kA)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="额定短路关合电流(KA)" prop="ratedshortcircuitclosingcurrent" >
					<el-input-number v-model="ruleForm.ratedshortcircuitclosingcurrent" placeholder="额定短路关合电流(KA)" :disabled="ro.ratedshortcircuitclosingcurrent"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="额定短路关合电流(KA)" prop="ratedshortcircuitclosingcurrent" >
					<el-input v-model="ruleForm.ratedshortcircuitclosingcurrent" placeholder="额定短路关合电流(KA)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="额定峰值耐受电流(kA)" prop="ratedpeakwithstandcurrentka" >
					<el-input-number v-model="ruleForm.ratedpeakwithstandcurrentka" placeholder="额定峰值耐受电流(kA)" :disabled="ro.ratedpeakwithstandcurrentka"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="额定峰值耐受电流(kA)" prop="ratedpeakwithstandcurrentka" >
					<el-input v-model="ruleForm.ratedpeakwithstandcurrentka" placeholder="额定峰值耐受电流(kA)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="工频耐受电压(kV)" prop="powerfrequencywithstan" >
					<el-input-number v-model="ruleForm.powerfrequencywithstan" placeholder="工频耐受电压(kV)" :disabled="ro.powerfrequencywithstan"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="工频耐受电压(kV)" prop="powerfrequencywithstan" >
					<el-input v-model="ruleForm.powerfrequencywithstan" placeholder="工频耐受电压(kV)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="雷电冲击耐受电压(kV)" prop="lightningimpulsewithstan" >
					<el-input-number v-model="ruleForm.lightningimpulsewithstan" placeholder="雷电冲击耐受电压(kV)" :disabled="ro.lightningimpulsewithstan"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="雷电冲击耐受电压(kV)" prop="lightningimpulsewithstan" >
					<el-input v-model="ruleForm.lightningimpulsewithstan" placeholder="雷电冲击耐受电压(kV)" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="防护等级" prop="protectiongrade" >
					<el-input v-model="ruleForm.protectiongrade" placeholder="防护等级" clearable  :readonly="ro.protectiongrade"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="防护等级" prop="protectiongrade" >
					<el-input v-model="ruleForm.protectiongrade" placeholder="防护等级" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="设备状态" prop="equipmentstatus" >
					<el-input v-model="ruleForm.equipmentstatus" placeholder="设备状态" clearable  :readonly="ro.equipmentstatus"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="设备状态" prop="equipmentstatus" >
					<el-input v-model="ruleForm.equipmentstatus" placeholder="设备状态" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="工作时长" prop="runningtime" >
					<el-input-number v-model="ruleForm.runningtime" placeholder="工作时长" :disabled="ro.runningtime"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="工作时长" prop="runningtime" >
					<el-input v-model="ruleForm.runningtime" placeholder="工作时长" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="工作温度" prop="operationtemperature" >
					<el-input-number v-model="ruleForm.operationtemperature" placeholder="工作温度" :disabled="ro.operationtemperature"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="工作温度" prop="operationtemperature" >
					<el-input v-model="ruleForm.operationtemperature" placeholder="工作温度" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="工作压力" prop="workingpressure" >
					<el-input-number v-model="ruleForm.workingpressure" placeholder="工作压力" :disabled="ro.workingpressure"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="工作压力" prop="workingpressure" >
					<el-input v-model="ruleForm.workingpressure" placeholder="工作压力" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="检测时间" prop="detectiontime" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.detectiontime" 
						type="datetime"
						:readonly="ro.detectiontime"
						placeholder="检测时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.detectiontime" label="检测时间" prop="detectiontime" >
					<el-input v-model="ruleForm.detectiontime" placeholder="检测时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="账号" prop="zhanghao" >
					<el-input v-model="ruleForm.zhanghao" placeholder="账号" clearable  :readonly="ro.zhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="账号" prop="zhanghao" >
					<el-input v-model="ruleForm.zhanghao" placeholder="账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="设备标准" prop="shebeibiaozhun" >
					<el-input v-model="ruleForm.shebeibiaozhun" placeholder="设备标准" clearable  :readonly="ro.shebeibiaozhun"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="设备标准" prop="shebeibiaozhun" >
					<el-input v-model="ruleForm.shebeibiaozhun" placeholder="设备标准" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.imgurl" label="图片" prop="imgurl" >
					<file-upload
						tip="点击上传图片"
						action="file/upload"
						:limit="3"
						:multiple="true"
						:fileUrls="ruleForm.imgurl?ruleForm.imgurl:''"
						@change="imgurlUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.imgurl" label="图片" prop="imgurl" >
					<img v-if="ruleForm.imgurl.substring(0,4)=='http'&&ruleForm.imgurl.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.imgurl" width="100" height="100">
					<img v-else-if="ruleForm.imgurl.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.imgurl.split(',')[0]" width="100" height="100">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.imgurl.split(',')" :src="$base.url+item" width="100" height="100">
				</el-form-item>
			</template>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-xihuan"></span>
					提交
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-xihuan"></span>
					返回
				</el-button>
			</el-form-item>
		</el-form>
    

	</div>
</template>
<script>
	import { 
		isNumber,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					title : false,
					ratedvoltage : false,
					ratedfrequencyhz : false,
					ratedcurrent : false,
					ratedshortcircuitbreakingcurrentka : false,
					effectivevalueofratedshor : false,
					ratedshortcircuitclosingcurrent : false,
					ratedpeakwithstandcurrentka : false,
					powerfrequencywithstan : false,
					lightningimpulsewithstan : false,
					protectiongrade : false,
					equipmentstatus : false,
					runningtime : false,
					operationtemperature : false,
					workingpressure : false,
					detectiontime : false,
					zhanghao : false,
					shebeibiaozhun : false,
					imgurl : false,
				},
			
				ruleForm: {
					title: '',
					ratedvoltage: '',
					ratedfrequencyhz: '',
					ratedcurrent: '',
					ratedshortcircuitbreakingcurrentka: '',
					effectivevalueofratedshor: '',
					ratedshortcircuitclosingcurrent: '',
					ratedpeakwithstandcurrentka: '',
					powerfrequencywithstan: '',
					lightningimpulsewithstan: '',
					protectiongrade: '',
					equipmentstatus: '',
					runningtime: '',
					operationtemperature: '',
					workingpressure: '',
					detectiontime: '',
					zhanghao: '',
					shebeibiaozhun: '',
					imgurl: '',
				},

				rules: {
					title: [
					],
					ratedvoltage: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					ratedfrequencyhz: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					ratedcurrent: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					ratedshortcircuitbreakingcurrentka: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					effectivevalueofratedshor: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					ratedshortcircuitclosingcurrent: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					ratedpeakwithstandcurrentka: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					powerfrequencywithstan: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					lightningimpulsewithstan: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					protectiongrade: [
					],
					equipmentstatus: [
					],
					runningtime: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					operationtemperature: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					workingpressure: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					detectiontime: [
					],
					zhanghao: [
					],
					shebeibiaozhun: [
					],
					imgurl: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {



		},
		components: {
		},
		created() {
		},
		methods: {
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='title'){
							this.ruleForm.title = obj[o];
							this.ro.title = true;
							continue;
						}
						if(o=='ratedvoltage'){
							this.ruleForm.ratedvoltage = obj[o];
							this.ro.ratedvoltage = true;
							continue;
						}
						if(o=='ratedfrequencyhz'){
							this.ruleForm.ratedfrequencyhz = obj[o];
							this.ro.ratedfrequencyhz = true;
							continue;
						}
						if(o=='ratedcurrent'){
							this.ruleForm.ratedcurrent = obj[o];
							this.ro.ratedcurrent = true;
							continue;
						}
						if(o=='ratedshortcircuitbreakingcurrentka'){
							this.ruleForm.ratedshortcircuitbreakingcurrentka = obj[o];
							this.ro.ratedshortcircuitbreakingcurrentka = true;
							continue;
						}
						if(o=='effectivevalueofratedshor'){
							this.ruleForm.effectivevalueofratedshor = obj[o];
							this.ro.effectivevalueofratedshor = true;
							continue;
						}
						if(o=='ratedshortcircuitclosingcurrent'){
							this.ruleForm.ratedshortcircuitclosingcurrent = obj[o];
							this.ro.ratedshortcircuitclosingcurrent = true;
							continue;
						}
						if(o=='ratedpeakwithstandcurrentka'){
							this.ruleForm.ratedpeakwithstandcurrentka = obj[o];
							this.ro.ratedpeakwithstandcurrentka = true;
							continue;
						}
						if(o=='powerfrequencywithstan'){
							this.ruleForm.powerfrequencywithstan = obj[o];
							this.ro.powerfrequencywithstan = true;
							continue;
						}
						if(o=='lightningimpulsewithstan'){
							this.ruleForm.lightningimpulsewithstan = obj[o];
							this.ro.lightningimpulsewithstan = true;
							continue;
						}
						if(o=='protectiongrade'){
							this.ruleForm.protectiongrade = obj[o];
							this.ro.protectiongrade = true;
							continue;
						}
						if(o=='equipmentstatus'){
							this.ruleForm.equipmentstatus = obj[o];
							this.ro.equipmentstatus = true;
							continue;
						}
						if(o=='runningtime'){
							this.ruleForm.runningtime = obj[o];
							this.ro.runningtime = true;
							continue;
						}
						if(o=='operationtemperature'){
							this.ruleForm.operationtemperature = obj[o];
							this.ro.operationtemperature = true;
							continue;
						}
						if(o=='workingpressure'){
							this.ruleForm.workingpressure = obj[o];
							this.ro.workingpressure = true;
							continue;
						}
						if(o=='detectiontime'){
							this.ruleForm.detectiontime = obj[o];
							this.ro.detectiontime = true;
							continue;
						}
						if(o=='zhanghao'){
							this.ruleForm.zhanghao = obj[o];
							this.ro.zhanghao = true;
							continue;
						}
						if(o=='shebeibiaozhun'){
							this.ruleForm.shebeibiaozhun = obj[o];
							this.ro.shebeibiaozhun = true;
							continue;
						}
						if(o=='imgurl'){
							this.ruleForm.imgurl = obj[o];
							this.ro.imgurl = true;
							continue;
						}
					}
				}
				// 获取用户信息
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.zhanghao!=''&&json.zhanghao) || json.zhanghao==0) && this.$storage.get("role")!="管理员"){
							this.ruleForm.zhanghao = json.zhanghao
							this.ro.zhanghao = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			info(id) {
				this.$http({
					url: `devicedetection/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.imgurl!=null) {
						this.ruleForm.imgurl = this.ruleForm.imgurl.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							
							await this.$http({
								url: `devicedetection/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.devicedetectionCrossAddOrUpdateFlag = false;
											this.parent.search();
											this.parent.contentStyleChange();
										}
									});
								} else {
									this.$message.error(data.msg);
								}
							});
						}
					});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.devicedetectionCrossAddOrUpdateFlag = false;
				this.parent.contentStyleChange();
			},
			imgurlUploadChange(fileUrls) {
				this.ruleForm.imgurl = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 30px;
	}
	.add-update-preview {
		padding: 40px 80px 80px 0;
		margin: 0 0 0 10px;
		background: #FFFFFF;
		display: flex;
		border-color: #eee;
		border-width: 0px 0 0;
		border-style: solid;
		flex-wrap: wrap;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		border: 0px solid #eee;
		padding: 0;
		margin: 0 0 26px 0;
		display: inline-block;
		width: 100%;
	}
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 10px 0 0;
		color: #9E9E9E;
		font-weight: 400;
		width: 180px;
		font-size: 14px;
		line-height: 40px;
		text-align: right;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 180px;
	}
	.add-update-preview .el-form-item span.text {
		border: 1px solid #E8E8E8;
		padding: 0 10px;
		color: #333;
		word-break: break-all;
		background: none;
		font-weight: 500;
		display: inline-block;
		font-size: 16px;
		min-height: 200px;
		line-height: 40px;
		min-width: 100%;
	}
	
	.add-update-preview .el-input {
		width: 100%;
	}
	.add-update-preview .el-input /deep/ .el-input__inner {
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
	}
	.add-update-preview .el-select /deep/ .el-input__inner {
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 30px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 30px;
		color: #666;
		width: 100%;
		font-size: 16px;
		min-width: 50%;
		height: 40px;
	}
	.add-update-preview .viewBtn {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 0px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		padding: 0 15px;
		margin: 0 20px 0 0;
		color: #666;
		background: #fff;
		width: auto;
		font-size: 15px;
		line-height: 34px;
		height: 34px;
		.iconfont {
			margin: 0 2px;
			color: #666;
			font-size: 16px;
			height: 34px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0;
		cursor: not-allowed;
		border-radius: 4px;
		padding: 0 0px;
		margin: 0 20px 0 0;
		outline: none;
		color: #999;
		background: none;
		width: auto;
		font-size: 16px;
		line-height: 40px;
		height: 40px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 34px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .upload .upload-img {
		border: 1px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
		border: 1px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
		border: 1px solid #ccc;
		cursor: pointer;
		border-radius: 0px;
		color: #666;
		background: #fff;
		width: 90px;
		font-size: 24px;
		line-height: 60px;
		text-align: center;
		height: 60px;
	}
	.add-update-preview /deep/ .el-upload__tip {
		color: #666;
		font-size: 15px;
	}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
		border: 1px solid #ccc;
		border-radius: 0px;
		padding: 0 12px;
		color: #666;
		word-break: break-all;
		width: 100%;
		font-size: 16px;
		min-height: 200px;
		line-height: 24px;
		min-width: 100%;
		height: auto;
	}
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
				border: 1px solid #ccc;
				border-radius: 0px;
				padding: 0 12px;
				color: #666;
				word-break: break-all;
				width: 100%;
				font-size: 16px;
				min-height: 200px;
				line-height: 24px;
				min-width: 100%;
				height: auto;
			}
	.add-update-preview .el-form-item.btn {
		padding: 0;
		margin: 20px 0 0;
		.btn1 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #5BAAFF;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #60DFE4;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 34px;
			}
		}
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #60E495;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #C3E460;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #ccc;
			cursor: pointer;
			border-radius: 4px;
			padding: 0 10px;
			margin: 0 10px 0 0;
			color: #fff;
			background: #E4B860;
			width: auto;
			font-size: 16px;
			min-width: 110px;
			height: 40px;
			.iconfont {
				margin: 0 2px;
				color: #fff;
				display: none;
				font-size: 14px;
				height: 40px;
			}
		}
		.btn5:hover {
			opacity: 0.8;
		}
	}
</style>
