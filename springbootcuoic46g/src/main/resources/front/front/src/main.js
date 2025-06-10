import Vue from 'vue'
import VueRouter from 'vue-router'
import VueResource from 'vue-resource'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router/router'
import BaiduMap from 'vue-baidu-map'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import '@/assets/css/iconfont.css'
import config from './config/config'
import validate from './common/validate'

import {
	isAuth,
	getCurDateTime,
	getCurDate,
	isBackAuth,
} from './common/system'
import App from './App.vue'
import Breadcrumb from '@/components/Breadcrumb'
import FileUpload from '@/components/FileUpload'
import Editor from "@/components/Editor";
import aplayer from 'vue-aplayer';
import store from './store'
import { encryptDes,decryptDes,encryptAes,decryptAes } from '@/common/des.js'
import VueLuckyCanvas from '@lucky-canvas/vue'
Vue.use(VueLuckyCanvas)
Vue.config.productionTip = false;

Vue.prototype.$config = config;
Vue.prototype.$validate = validate;
Vue.prototype.isAuth = isAuth;
Vue.prototype.isBackAuth = isBackAuth;
Vue.prototype.getCurDateTime = getCurDateTime;
Vue.prototype.getCurDate = getCurDate;
Vue.prototype.encryptDes = encryptDes
Vue.prototype.decryptDes = decryptDes
Vue.prototype.encryptAes = encryptAes
Vue.prototype.decryptAes = decryptAes

Vue.use(VueRouter);
Vue.use(VueResource);
Vue.use(ElementUI);
Vue.use(BaiduMap, {});
Vue.use(VueQuillEditor);

Vue.component('Breadcrumb', Breadcrumb);
Vue.component('file-upload', FileUpload);
Vue.component('editor', Editor);
Vue.component('aplayer', aplayer);

Vue.http.options.root = config.name;
Vue.http.headers.common['Token'] = localStorage.getItem('frontToken');
Vue.http.interceptors.push(function(request, next) {
	next((response) => {
		if (response.data.code == 401 || response.data.code == 403) {
			// this.$message.error('请先登录')
			setTimeout(()=>{
				this.$router.replace('/login').catch(err => {});
				localStorage.clear()
			},1000)
		} else {
			return response;
		}
	});
});

router.afterEach((to, from) => {
	if (from.path == '/login') {
		Vue.http.headers.common['Token'] = localStorage.getItem('frontToken');
	}
})

new Vue({
	render: h => h(App),
	router,
	store,
}).$mount('#app')