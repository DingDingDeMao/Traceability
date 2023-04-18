package com.linzhongyoushenjun.Traceability.controller;

import cn.hutool.core.lang.Dict;
import com.linzhongyoushenjun.Traceability.common.Result;
import com.linzhongyoushenjun.Traceability.constant.MessageConstant;
import com.linzhongyoushenjun.Traceability.service.MyGoodsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Resource
    private MyGoodsService myGoodsService;
    @RequestMapping("changeStatus")
    public Result changeStatus(int goodsStatus, String remark){
        try {
            Dict dict = myGoodsService.changeStatus(goodsStatus, remark);
            return new Result(true, MessageConstant.CHANGE_GOODS_STATUS_SUCCESS,dict);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.CHANGE_GOODS_STATUS_FAIL);
        }
    }

    @RequestMapping("getStatus")
    public Result getStatus(){
        try {
            Dict dict = myGoodsService.getStatus();
            return new Result(true, MessageConstant.GET_GOODS_STATUS_SUCCESS,dict);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_GOODS_STATUS_FAIL);
        }
    }

    @RequestMapping("getTraceInfo")
    public Result getTraceInfo(){
        try {
            Dict dict = myGoodsService.getTraceInfo();
            return new Result(true, MessageConstant.GET_GOODS_TRACE_INFO_SUCCESS,dict);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true, MessageConstant.GET_GOODS_TRACE_INFO_FAIL);
        }
    }
}
