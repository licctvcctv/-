<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'-'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType" class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-jiantou33"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="list-item">
					<div class="lable">用户名：</div>
					<el-input v-model="formSearch.username" placeholder="用户名" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
					<i class="el-icon-search"></i>
					查询
				</el-button>
				<el-button class="list-add-btn" v-if="btnAuth('users','新增')" type="primary" @click="add('/index/usersAdd')">
					<i class="el-icon-circle-plus-outline"></i>
					添加
				</el-button>
			</el-form>
			<div class="list">
				<!-- 样式一 -->
				<div class="list1 index-pv1">
					<div v-for="(item, index) in dataList" :key="index" @click.stop="toDetail(item)" class="list-item animation-box">
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{item.addtime.split(' ')[0]}}</span>
						</div>
					</div>
				</div>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="false"
				:layout='["total","prev","pager","next"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
		</div>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>
<script>
	export default {
		//数据集合
		data() {
			return {
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '管理员'
					}
				],
				formSearch: {
					username: '',
				},
				fenlei: [],
				feileiColumn: '',
				dataList: [],
				total: 1,
				pageSize: 12,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			await this.getFenlei();
			let fenlei = '全部'
			if(this.$route.query.homeFenlei){
				fenlei = this.$route.query.homeFenlei
			}
			this.getList(1, fenlei);
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		//方法集合
		methods: {
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
			},
			getList(page, fenlei, ref = '') {
				let params = {
					page,
					limit: this.pageSize,
				};
				let searchWhere = {};
				if (this.formSearch.username != '') searchWhere.username = '%' + this.formSearch.username + '%';
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`users/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			sortClick(type){
				if(this.sortType==type){
					if(this.sortOrder == 'desc'){
						this.sortOrder = 'asc'
					}else{
						this.sortOrder = 'desc'
					}
				}else{
					this.sortType = type
					this.sortOrder = 'desc'
				}
				this.getList(1, '全部')
			},
			curChange(page) {
				this.getList(page);
			},
			prevClick(page) {
				this.getList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1);
			},
			nextClick(page) {
				this.getList(page);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/usersDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		padding: 0;
		margin: 10px auto;
		background: #fff;
		width: 100%;
		position: relative;
		.list-form-pv {
			border-radius: 10px;
			padding: 20px 10px 10px;
			box-shadow: 0 4px 8px rgba(0,0,0,.3);
			margin: -10px auto 0;
			background: #fff;
			display: flex;
			width: 68%;
			align-items: center;
			flex-wrap: wrap;
			height: auto;
			.list-item {
				margin: 0 10px 10px;
				width: auto;
				/deep/.el-form-item__content {
					display: flex;
				}
				.lable {
					padding: 0 0px;
					white-space: nowrap;
					display: inline-block;
					width: auto;
					font-size: 13px;
					line-height: 42px;
					text-align: right;
				}
				.el-input {
					width: 100%;
				}
				.datetimerange {
					border: 1px solid #9E9E9E;
					border-radius: 8px;
					padding: 3px 10px;
					outline: none;
					background: #fff;
					width: 100%;
					justify-content: center;
				}
				.el-input /deep/ .el-input__inner {
					border: 1px solid #9E9E9E;
					border-radius: 8px;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #333;
					width: 100%;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 1px solid #9E9E9E;
					border-radius: 8px;
					padding: 0 30px;
					margin: 0;
					outline: none;
					color: #333;
					width: 100%;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				padding: 0px 25px;
				margin: 0 10px 10px 0;
				color: #fff;
				font-weight: bold;
				font-size: 15px;
				line-height: 42px;
				border-radius: 4px;
				outline: none;
				background: #0AB8C1;
				width: auto;
				height: 42px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					display: none;
					font-size: 14px;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 0;
				padding: 0px 25px;
				margin: 0 10px 10px 0;
				color: #fff;
				font-weight: bold;
				font-size: 15px;
				line-height: 42px;
				border-radius: 4px;
				outline: none;
				background: #0AB8C1;
				width: auto;
				height: 42px;
				i {
					margin: 0 10px 0 0;
					color: #fff;
					display: none;
					font-size: 14px;
				}
			}
		}
		.sort_view {
			margin: 20px auto 10px;
			width: 68%;
			.click-sort-btn {
				border: 0;
				border-radius: 8px;
				padding: 0 15px;
				margin: 0 5px;
				background: #0AB8C170;
				.icon {
					margin: 0 2px 0 0;
					color: #333;
					font-size: 14px;
					line-height: 40px;
				}
				.text {
					color: #333;
					font-size: 14px;
					line-height: 40px;
				}
			}
		}
		.list {
			margin: 0 auto 10px;
			background: #fff;
			width: 100%;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				box-shadow: 0px 4px 5px 0px rgba(0,0,0,0.3);
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, -10px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(1.01) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list1 {
				padding: 0 ;
				margin: 0 auto;
				background: #fff;
				display: flex;
				width: 68%;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					border: 0px solid #F3F3F3;
					cursor: pointer;
					padding: 10px;
					box-shadow: 0px 0px 4px 0px rgba(0,0,0,0.3);
					margin: 10px 10px 20px;
					background: #fff;
					display: flex;
					width: calc(25% - 20px);
					position: relative;
					flex-wrap: wrap;
					height: auto;
					.image {
						object-fit: cover;
						display: block;
						width: 100%;
						height: 240px;
						order: 1;
					}
					.price {
						padding: 0px 0 0;
						color: #E72C59;
						width: 100%;
						font-size: 18px;
						line-height: 2;
						order: 3;
					}
					.name {
						padding: 5px 0;
						color: #000;
						width: 100%;
						font-size: 16px;
						border-color: #F3F3F3;
						border-width: 0 0 1px;
						line-height: 1.5;
						border-style: solid;
						order: 2;
					}
					.time_item {
						padding: 5px 0;
						display: none;
						width: 100%;
						order: 8;
						.icon {
							margin: 0 5px 0 0;
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
					}
					.publisher_item {
						padding: 0 10px 0 0;
						display: none;
						order: 7;
						.icon {
							margin: 0 5px 0 0;
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
					}
					.like_item {
						padding: 0 10px 0 0;
						display: none;
						width: auto;
						justify-content: center;
						order: 5;
						.icon {
							margin: 0 5px 0 0;
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
					}
					.collect_item {
						padding: 0 10px 0 0;
						display: none;
						width: auto;
						order: 4;
						.icon {
							margin: 0 5px 0 0;
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
					}
					.view_item {
						padding: 0 10px 0 0;
						display: none;
						width: auto;
						justify-content: flex-end;
						order: 6;
						.icon {
							margin: 0 5px 0 0;
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
						.label {
							color: #666;
							display: none;
							font-size: 12px;
							line-height: 1.5;
						}
						.text {
							color: #0AB8C1;
							font-size: 14px;
							line-height: 1.5;
						}
					}
				}
			}
		}
	}
</style>
