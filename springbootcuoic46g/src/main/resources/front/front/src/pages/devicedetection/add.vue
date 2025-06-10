<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="150px"
			>
			<el-form-item class="add-item" label="名称" prop="title">
				<el-input v-model="ruleForm.title" 
					placeholder="名称" clearable :disabled=" false  ||ro.title"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="额定电压(kv)" prop="ratedvoltage">
				<el-input-number v-model="ruleForm.ratedvoltage" placeholder="额定电压(kv)" :disabled=" false ||ro.ratedvoltage"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="额定频率(Hz)" prop="ratedfrequencyhz">
				<el-input-number v-model="ruleForm.ratedfrequencyhz" placeholder="额定频率(Hz)" :disabled=" false ||ro.ratedfrequencyhz"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="额定电流(A)" prop="ratedcurrent">
				<el-input-number v-model="ruleForm.ratedcurrent" placeholder="额定电流(A)" :disabled=" false ||ro.ratedcurrent"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="额定短路开断电流(kA)" prop="ratedshortcircuitbreakingcurrentka">
				<el-input-number v-model="ruleForm.ratedshortcircuitbreakingcurrentka" placeholder="额定短路开断电流(kA)" :disabled=" false ||ro.ratedshortcircuitbreakingcurrentka"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="额定短时耐受电流有效值(kA)" prop="effectivevalueofratedshor">
				<el-input-number v-model="ruleForm.effectivevalueofratedshor" placeholder="额定短时耐受电流有效值(kA)" :disabled=" false ||ro.effectivevalueofratedshor"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="额定短路关合电流(KA)" prop="ratedshortcircuitclosingcurrent">
				<el-input-number v-model="ruleForm.ratedshortcircuitclosingcurrent" placeholder="额定短路关合电流(KA)" :disabled=" false ||ro.ratedshortcircuitclosingcurrent"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="额定峰值耐受电流(kA)" prop="ratedpeakwithstandcurrentka">
				<el-input-number v-model="ruleForm.ratedpeakwithstandcurrentka" placeholder="额定峰值耐受电流(kA)" :disabled=" false ||ro.ratedpeakwithstandcurrentka"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="工频耐受电压(kV)" prop="powerfrequencywithstan">
				<el-input-number v-model="ruleForm.powerfrequencywithstan" placeholder="工频耐受电压(kV)" :disabled=" false ||ro.powerfrequencywithstan"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="雷电冲击耐受电压(kV)" prop="lightningimpulsewithstan">
				<el-input-number v-model="ruleForm.lightningimpulsewithstan" placeholder="雷电冲击耐受电压(kV)" :disabled=" false ||ro.lightningimpulsewithstan"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="防护等级" prop="protectiongrade">
				<el-input v-model="ruleForm.protectiongrade" 
					placeholder="防护等级" clearable :disabled=" false  ||ro.protectiongrade"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="设备状态" prop="equipmentstatus">
				<el-input v-model="ruleForm.equipmentstatus" 
					placeholder="设备状态" clearable :disabled=" false  ||ro.equipmentstatus"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="工作时长" prop="runningtime">
				<el-input-number v-model="ruleForm.runningtime" placeholder="工作时长" :disabled=" false ||ro.runningtime"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="工作温度" prop="operationtemperature">
				<el-input-number v-model="ruleForm.operationtemperature" placeholder="工作温度" :disabled=" false ||ro.operationtemperature"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="工作压力" prop="workingpressure">
				<el-input-number v-model="ruleForm.workingpressure" placeholder="工作压力" :disabled=" false ||ro.workingpressure"></el-input-number>
			</el-form-item>
			<el-form-item class="add-item" label="检测时间" prop="detectiontime">
				<el-date-picker
					:disabled=" false  ||ro.detectiontime"
					value-format="yyyy-MM-dd HH:mm:ss"
					v-model="ruleForm.detectiontime" 
					type="datetime"
					placeholder="检测时间">
				</el-date-picker>
			</el-form-item>
			<el-form-item class="add-item" label="账号" prop="zhanghao">
				<el-input v-model="ruleForm.zhanghao" 
					placeholder="账号" clearable :disabled=" false  ||ro.zhanghao"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="设备标准" prop="shebeibiaozhun">
				<el-input v-model="ruleForm.shebeibiaozhun" 
					placeholder="设备标准" clearable :disabled=" false  ||ro.shebeibiaozhun"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="图片" v-if="type!='cross' || (type=='cross' && !ro.imgurl)" prop="imgurl">
				<file-upload
					tip="点击上传图片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:fileUrls="ruleForm.imgurl?ruleForm.imgurl:''"
					@change="imgurlUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="图片" prop="imgurl">
				<img v-if="ruleForm.imgurl.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.imgurl.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.imgurl.split(',')" :src="baseUrl+item">
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont icon-kaitongfuwu"></span>
					<span class="text">确定</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu1"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
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
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
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
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					ratedfrequencyhz: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					ratedcurrent: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					ratedshortcircuitbreakingcurrentka: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					effectivevalueofratedshor: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					ratedshortcircuitclosingcurrent: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					ratedpeakwithstandcurrentka: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					powerfrequencywithstan: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					lightningimpulsewithstan: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					protectiongrade: [
					],
					equipmentstatus: [
					],
					runningtime: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					operationtemperature: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
					],
					workingpressure: [
						{ validator: this.$validate.isNumber, trigger: 'blur' },
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
				centerType: false,
			};
		},
		computed: {



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
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
							this.ruleForm.imgurl = obj[o]?obj[o].split(",")[0]:'';
							this.ro.imgurl = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.zhanghao!=''&&json.zhanghao) || json.zhanghao==0){
							this.ruleForm.zhanghao = json.zhanghao;
							this.ro.zhanghao = true;
						}
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			info() {
				this.$http.get(`devicedetection/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(!this.ruleForm.id) {
					delete this.ruleForm.userid
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}


						await this.$http.post(`devicedetection/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
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
				this.$router.go(-1);
			},
			imgurlUploadChange(fileUrls) {
				this.ruleForm.imgurl = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 20px 16%;
		margin: 0px auto;
		background: #F2F3F7;
		width: 100%;
		position: relative;
		.add-update-form {
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				padding: 10px;
				margin: 0 0 10px;
				background: none;
				display: inline-block;
				width: 48%;
				/deep/ .el-form-item__label {
					padding: 0 10px 0 0;
					color: #666;
					font-weight: 500;
					width: 150px;
					font-size: 14px;
					line-height: 40px;
					text-align: center;
				}
				/deep/ .el-form-item__content {
					margin-left: 150px;
				}
				.el-input {
					width: 100%;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 80%;
					font-size: 15px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 0;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 80%;
					font-size: 15px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 80%;
					font-size: 15px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 0;
					padding: 0 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 80%;
					font-size: 15px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: 80%;
				}
				.el-select /deep/ .el-input__inner {
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 0 10px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 15px;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 0;
					padding: 0 10px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 100%;
					font-size: 15px;
					height: 40px;
				}
				.el-date-editor {
					width: 80%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 100%;
					font-size: 15px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 0;
					padding: 0 10px 0 30px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 100%;
					font-size: 15px;
					height: 40px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 1px dashed #999;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px dashed #999;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px dashed #999;
					cursor: pointer;
					border-radius: 6px;
					color: #999;
					width: 100px;
					font-size: 32px;
					line-height: 100px;
					text-align: center;
					height: 100px;
				}
				/deep/ .el-upload__tip {
					color: #838fa1;
					display: none;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 1px solid #E2E3E5;
					border-radius: 0;
					padding: 12px;
					box-shadow: 0 0 0px rgba(64, 158, 255, .5);
					outline: none;
					color: #000;
					width: 80%;
					font-size: 14px;
					height: 120px;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 1px solid #E2E3E5;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 12px;
					box-shadow: 0 0 0px rgba(85, 85, 127, 0.5);
					outline: none;
					color: #000;
					background: #f8f8f8;
					width: 80%;
					font-size: 14px;
					height: 120px;
				}
				/deep/ .el-input__inner::placeholder {
					color: #999;
					font-size: 14px;
				}
				/deep/ textarea::placeholder {
					color: #999;
					font-size: 14px;
				}
				.editor {
					background-color: #fff;
					border-radius: 0;
					padding: 0;
					box-shadow: 0 0 0px rgba(75,223,201,.5);
					margin: 0;
					width: 80%;
					border-color: #ccc;
					border-width: 0;
					border-style: solid;
					height: auto;
				}
				.upload-img {
					width: 100px;
					height: 100px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 10px;
					margin: 0;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #5AC3B2;
					width: auto;
					height: 30px;
				}
				.viewBtn:hover {
					color: #fff;
					background: #5AC3B290;
				}
				.unviewBtn {
					border: 0;
					cursor: not-allowed;
					padding: 0 10px;
					margin: 0;
					color: #ccc;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #858585;
					width: auto;
					height: 30px;
				}
				.unviewBtn:hover {
					color: #ccc;
					background: #858585;
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 0;
				.submitBtn {
					border: 0;
					cursor: pointer;
					border-radius: 4px;
					padding: 0 15px;
					margin: 0 20px 0 0;
					outline: none;
					background: #5AC3B2;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 30px;
					height: 30px;
					.icon {
						color: rgba(255, 255, 255, 1);
						display: none;
					}
					.text {
						color: rgba(255, 255, 255, 1);
					}
				}
				.submitBtn:hover {
					background: #5AC3B290;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn {
					border: 0px solid rgba(64, 158, 255, 1);
					cursor: pointer;
					padding: 0 15px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 14px;
					line-height: 30px;
					border-radius: 4px;
					outline: none;
					background: #858585;
					width: auto;
					text-align: center;
					min-width: 100px;
					height: 30px;
					.icon {
						color: #fff;
						display: none;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn:hover {
					background: #85858590;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
