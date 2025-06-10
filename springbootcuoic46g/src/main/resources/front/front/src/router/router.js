import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import stzcList from '../pages/stzc/list'
import stzcDetail from '../pages/stzc/detail'
import stzcAdd from '../pages/stzc/add'
import devicedetectionList from '../pages/devicedetection/list'
import devicedetectionDetail from '../pages/devicedetection/detail'
import devicedetectionAdd from '../pages/devicedetection/add'
import devicedetectionforecastList from '../pages/devicedetectionforecast/list'
import devicedetectionforecastDetail from '../pages/devicedetectionforecast/detail'
import devicedetectionforecastAdd from '../pages/devicedetectionforecast/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import discussstzcList from '../pages/discussstzc/list'
import discussstzcDetail from '../pages/discussstzc/detail'
import discussstzcAdd from '../pages/discussstzc/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'stzc',
					component: stzcList
				},
				{
					path: 'stzcDetail',
					component: stzcDetail
				},
				{
					path: 'stzcAdd',
					component: stzcAdd
				},
				{
					path: 'devicedetection',
					component: devicedetectionList
				},
				{
					path: 'devicedetectionDetail',
					component: devicedetectionDetail
				},
				{
					path: 'devicedetectionAdd',
					component: devicedetectionAdd
				},
				{
					path: 'devicedetectionforecast',
					component: devicedetectionforecastList
				},
				{
					path: 'devicedetectionforecastDetail',
					component: devicedetectionforecastDetail
				},
				{
					path: 'devicedetectionforecastAdd',
					component: devicedetectionforecastAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'discussstzc',
					component: discussstzcList
				},
				{
					path: 'discussstzcDetail',
					component: discussstzcDetail
				},
				{
					path: 'discussstzcAdd',
					component: discussstzcAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
