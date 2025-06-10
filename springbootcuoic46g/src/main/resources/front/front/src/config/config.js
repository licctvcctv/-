export default {
	baseUrl: 'http://localhost:8080/springbootcuoic46g/',
	name: '/springbootcuoic46g',
	indexNav: [
		{
			name: '电气设备',
			url: '/index/stzc',
		},
		{
			name: '公告信息',
			url: '/index/news'
		},
	],
	cateList: [
		{
			name: '公告信息',
			refTable: 'newstype',
			refColumn: 'typename',
		},
	]
}
