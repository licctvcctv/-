                                                                                                                                                                package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import com.utils.ValidatorUtils;
import com.utils.DeSensUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.utils.UserBasedCollaborativeFiltering;

import com.entity.StzcEntity;
import com.entity.view.StzcView;

import com.service.StzcService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 电气设备
 * 后端接口
 * @author 
 * @email 
 * @date 2025-06-08 19:56:15
 */
@RestController
@RequestMapping("/stzc")
public class StzcController {
    @Autowired
    private StzcService stzcService;
                
            @Autowired
        private StoreupService storeupService;
    
    
    
    


    
    
    
    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,StzcEntity stzc,
                                                                                                                                                        HttpServletRequest request){
                                    //设置查询条件
        EntityWrapper<StzcEntity> ew = new EntityWrapper<StzcEntity>();
                                                                                                                                                                                                                                            
        
                //查询结果
        PageUtils page = stzcService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, stzc), params), params));
        Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }

    /**
     * 前台列表
     */
        @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,StzcEntity stzc,
                                                                                                                                                        HttpServletRequest request){
                //设置查询条件
        EntityWrapper<StzcEntity> ew = new EntityWrapper<StzcEntity>();
                                                                                                                                                                                                                                            
                //查询结果
        PageUtils page = stzcService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, stzc), params), params));
        Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    
            /**
         * 列表
         */
        @RequestMapping("/lists")
        public R list( StzcEntity stzc){
            EntityWrapper<StzcEntity> ew = new EntityWrapper<StzcEntity>();
        ew.allEq(MPUtil.allEQMapPre( stzc, "stzc"));
        return R.ok().put("data", stzcService.selectListView(ew));
        }
    
    /**
    * 查询
    */
    @RequestMapping("/query")
    public R query(StzcEntity stzc){
            EntityWrapper< StzcEntity> ew = new EntityWrapper< StzcEntity>();
    ew.allEq(MPUtil.allEQMapPre( stzc, "stzc"));
        StzcView stzcView =  stzcService.selectView(ew);
    return R.ok("查询电气设备成功").put("data", stzcView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
            StzcEntity stzc = stzcService.selectById(id);
                    if(null==stzc.getClicknum()){
            stzc.setClicknum(0);
        }
            stzc.setClicknum(stzc.getClicknum()+1);
                                        stzcService.updateById(stzc);
            stzc = stzcService.selectView(new EntityWrapper<StzcEntity>().eq("id", id));
                Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(stzc,deSens);
        return R.ok().put("data", stzc);
    }

    /**
     * 前台详情
     */
        @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
            StzcEntity stzc = stzcService.selectById(id);
                    if(null==stzc.getClicknum()){
            stzc.setClicknum(0);
        }
            stzc.setClicknum(stzc.getClicknum()+1);
                                        stzcService.updateById(stzc);
            stzc = stzcService.selectView(new EntityWrapper<StzcEntity>().eq("id", id));
                Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(stzc,deSens);
        return R.ok().put("data", stzc);
    }

    
    
    
    /**
     * 后台保存
     */
@RequestMapping("/save")
        public R save(@RequestBody StzcEntity stzc, HttpServletRequest request){
                                                                                                                                                                                                                        //ValidatorUtils.validateEntity(stzc);
                                                                            stzcService.insert(stzc);
        return R.ok().put("data",stzc.getId());
    }

    /**
     * 前台保存
     */
                @RequestMapping("/add")
    public R add(@RequestBody StzcEntity stzc, HttpServletRequest request){
                                                                                                                                                                                                                        //ValidatorUtils.validateEntity(stzc);
                                                                                    stzcService.insert(stzc);
        return R.ok().put("data",stzc.getId());
    }

    
    
    

    /**
     * 修改
     */
@RequestMapping("/update")
@Transactional
                    public R update(@RequestBody StzcEntity stzc, HttpServletRequest request){
        //ValidatorUtils.validateEntity(stzc);
                                                                                                                                                                                                                                        //全部更新
            stzcService.updateById(stzc);
                        return R.ok();
    }

    

    
    
    /**
     * 删除
     */
@RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids){
            stzcService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

                /**
         * 前台智能排序
         */
        @IgnoreAuth
        @RequestMapping("/autoSort")
        public R autoSort(@RequestParam Map<String, Object> params,StzcEntity stzc, HttpServletRequest request,String pre){
        EntityWrapper<StzcEntity> ew = new EntityWrapper<StzcEntity>();
                                                                                                                                                                                                                        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
        Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();
            String newKey = entry.getKey();
            if (pre.endsWith(".")) {
                newMap.put(pre + newKey, entry.getValue());
            } else if (StringUtils.isEmpty(pre)) {
                newMap.put(newKey, entry.getValue());
            } else {
                newMap.put(pre + "." + newKey, entry.getValue());
            }
        }
                    params.put("sort", "clicknum");
                params.put("order", "desc");

        PageUtils page = stzcService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, stzc), params), params));
        return R.ok().put("data", page);
    }
    

            /**
         * 协同算法（基于用户收藏的协同算法）
         */
        @RequestMapping("/autoSort2")
        public R autoSort2(@RequestParam Map<String, Object> params,StzcEntity stzc, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
                    List<StoreupEntity> storeups = storeupService.selectList(new EntityWrapper<StoreupEntity>().eq("type", 1).eq("tablename", "stzc"));
                // 创建协同过滤对象
        UserBasedCollaborativeFiltering filter = new UserBasedCollaborativeFiltering();

        // 为指定用户推荐物品
        String targetUser = userId;
        int numRecommendations = limit;
        List<String> recommendations = filter.recommendItems(storeups, "userid", "refid", userId, numRecommendations);

        // 输出推荐结果
        System.out.println("Recommendations for " + targetUser + ":");
        for (String item : recommendations) {
            System.out.println(item);
        }

        EntityWrapper<StzcEntity> ew = new EntityWrapper<StzcEntity>();
        ew.in("id", String.join(",", recommendations));
        if(recommendations!=null && recommendations.size()>0 && recommendations.size()>0) {
            ew.last("order by FIELD(id, "+"'"+String.join("','", recommendations)+"'"+")");
        }

        // 根据协同结果查询结果并返回
        PageUtils page = stzcService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, stzc), params), params));;
        List<StzcEntity> pageList = (List<StzcEntity>)page.getList();
        if(recommendations!=null && recommendations.size()>0 && pageList.size()<limit) {
            int toAddNum = limit-pageList.size();
            ew = new EntityWrapper<StzcEntity>();
            ew.notIn("id", recommendations);
            ew.orderBy("id", false);
            ew.last("limit "+toAddNum);
            pageList.addAll(stzcService.selectList(ew));
        } else if(pageList.size()>limit) {
            pageList = pageList.subList(0, limit);
        }
        page.setList(pageList);

        return R.ok().put("data", page);
    }
    
    
    
    
    
            
        }
