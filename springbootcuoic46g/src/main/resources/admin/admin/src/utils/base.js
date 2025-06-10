const base = {
    get() {
        return {
            url : "http://localhost:8080/springbootcuoic46g/",
            name: "springbootcuoic46g",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springbootcuoic46g/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于大数据的电气设备故障检测"
        } 
    }
}
export default base
