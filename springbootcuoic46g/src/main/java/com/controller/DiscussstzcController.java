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

import com.entity.DiscussstzcEntity;
import com.entity.view.DiscussstzcView;

import com.service.DiscussstzcService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * stzc评论表
 * 后端接口
 * @author 
 * @email 
 * @date 2025-06-08 19:56:16
 */
@RestController
@RequestMapping("/discussstzc")
public class DiscussstzcController {
    @Autowired
    private DiscussstzcService discussstzcService;
                
    
    
    
    


    
    
    
    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussstzcEntity discussstzc,
                                                                                                                                                                                        HttpServletRequest request){
                                    //设置查询条件
        EntityWrapper<DiscussstzcEntity> ew = new EntityWrapper<DiscussstzcEntity>();
                                                                                                                                                                                                                                                                                            
        
                //查询结果
        PageUtils page = discussstzcService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussstzc), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussstzcEntity discussstzc,
                                                                                                                                                                                        HttpServletRequest request){
                //设置查询条件
        EntityWrapper<DiscussstzcEntity> ew = new EntityWrapper<DiscussstzcEntity>();
                                                                                                                                                                                                                                                                                            
                //查询结果
        PageUtils page = discussstzcService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussstzc), params), params));
        Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                                            //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    
            /**
         * 列表
         */
        @RequestMapping("/lists")
        public R list( DiscussstzcEntity discussstzc){
            EntityWrapper<DiscussstzcEntity> ew = new EntityWrapper<DiscussstzcEntity>();
        ew.allEq(MPUtil.allEQMapPre( discussstzc, "discussstzc"));
        return R.ok().put("data", discussstzcService.selectListView(ew));
        }
    
    /**
    * 查询
    */
    @RequestMapping("/query")
    public R query(DiscussstzcEntity discussstzc){
            EntityWrapper< DiscussstzcEntity> ew = new EntityWrapper< DiscussstzcEntity>();
    ew.allEq(MPUtil.allEQMapPre( discussstzc, "discussstzc"));
        DiscussstzcView discussstzcView =  discussstzcService.selectView(ew);
    return R.ok("查询stzc评论表成功").put("data", discussstzcView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
            DiscussstzcEntity discussstzc = discussstzcService.selectById(id);
                                Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                                            //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussstzc,deSens);
        return R.ok().put("data", discussstzc);
    }

    /**
     * 前台详情
     */
        @IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
            DiscussstzcEntity discussstzc = discussstzcService.selectById(id);
                                Map<String, String> deSens = new HashMap<>();
                                                                                                                                                                                                                                            //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussstzc,deSens);
        return R.ok().put("data", discussstzc);
    }

    
    
    
    /**
     * 后台保存
     */
@RequestMapping("/save")
        public R save(@RequestBody DiscussstzcEntity discussstzc, HttpServletRequest request){
                                                                                                                                                                                                                                            //ValidatorUtils.validateEntity(discussstzc);
                                                                            discussstzcService.insert(discussstzc);
        return R.ok().put("data",discussstzc.getId());
    }

    /**
     * 前台保存
     */
                @RequestMapping("/add")
    public R add(@RequestBody DiscussstzcEntity discussstzc, HttpServletRequest request){
                                                                                                                                                                                                                                            //ValidatorUtils.validateEntity(discussstzc);
                                                                                    discussstzcService.insert(discussstzc);
        return R.ok().put("data",discussstzc.getId());
    }

    
    
            /**
        * 获取用户密保
        */
        @RequestMapping("/security")
        @IgnoreAuth
        public R security(@RequestParam String username){
            DiscussstzcEntity discussstzc = discussstzcService.selectOne(new EntityWrapper<DiscussstzcEntity>().eq("", username));
        return R.ok().put("data", discussstzc);
    }
    

    /**
     * 修改
     */
@RequestMapping("/update")
@Transactional
        @IgnoreAuth
        public R update(@RequestBody DiscussstzcEntity discussstzc, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussstzc);
                                                                                                                                                                                                                                                            //全部更新
            discussstzcService.updateById(discussstzc);
                        return R.ok();
    }

    

    
    
    /**
     * 删除
     */
@RequestMapping("/delete")
        public R delete(@RequestBody Long[] ids){
            discussstzcService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

                /**
         * 前台智能排序
         */
        @IgnoreAuth
        @RequestMapping("/autoSort")
        public R autoSort(@RequestParam Map<String, Object> params,DiscussstzcEntity discussstzc, HttpServletRequest request,String pre){
        EntityWrapper<DiscussstzcEntity> ew = new EntityWrapper<DiscussstzcEntity>();
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
                    params.put("sort", "clicktime");
                params.put("order", "desc");

        PageUtils page = discussstzcService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussstzc), params), params));
        return R.ok().put("data", page);
    }
    

    
    
    
    
    
            
        }
