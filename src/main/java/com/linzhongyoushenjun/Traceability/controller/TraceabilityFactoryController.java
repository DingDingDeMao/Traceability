package com.linzhongyoushenjun.Traceability.controller;

import cn.hutool.core.lang.Dict;
import com.linzhongyoushenjun.Traceability.common.Result;
import com.linzhongyoushenjun.Traceability.constant.MessageConstant;
import com.linzhongyoushenjun.Traceability.service.MyTraceabilityFactoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/TraceabilityFactory")
public class TraceabilityFactoryController {

    @Resource
    private MyTraceabilityFactoryService myTraceabilityFactoryService;

    /**
     * 获取商品hash值
     *
     * @param goodsName 商品名称
     */
    @GetMapping("/getGoodsGroup")
    public Result getGoodsGroup(String goodsName) {
        try {
            Dict goodsGroup = myTraceabilityFactoryService.getGoodsGroup(goodsName);
            return new Result(true, MessageConstant.GET_HASH_SUCCESS, goodsGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_HASH_FAIL);
        }
    }

    /**
     * 产品溯源创建
     *
     * @param goodsGroup 商品hash值
     */
    @RequestMapping("/createTraceability")
    public Result createTraceability(String goodsGroup) {
        try {
            Dict traceability = myTraceabilityFactoryService.createTraceability(goodsGroup);
            return new Result(true, MessageConstant.CREATETRACEABILITY_SUCCESS, traceability);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.CREATETRACEABILITY_FAIL);
        }
    }

    /**
     * 产品创建
     *
     * @param goodsGroup 商品hash值
     * @param goodsId    商品id
     */
    @RequestMapping("/createTraceGoods")
    public Result createTraceGoods(String goodsGroup, int goodsId) {
        try {
            Dict traceGoods = myTraceabilityFactoryService.createTraceGoods(goodsGroup, goodsId);
            return new Result(true, MessageConstant.CREATETRACEGOODS_SUCCESS, traceGoods);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.CREATETRACEGOODS_FAIL);
        }
    }

    /**
     * 获取溯源过程
     *
     * @param goodsGroup 商品hash值
     * @param goodsId    商品id
     */
    @RequestMapping("getTraceInfo")
    public Result getTraceInfo(String goodsGroup, int goodsId) {
        try {
            Dict traceInfo = myTraceabilityFactoryService.getTraceInfo(goodsGroup, goodsId);
            return new Result(true, MessageConstant.GET_TRACE_INFO_SUCCESS, traceInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_TRACE_INFO__FAIL);
        }
    }
    /**
     * 改变状态追踪
     *
     * @param goodsGroup  商品hash值
     * @param goodsId     商品id
     * @param goodsStatus 更改产品的状态
     * @param remark      当前标志
     */
    @RequestMapping("changeTraceGoods")
    public Result changeTraceGoods(String goodsGroup, int goodsId, int goodsStatus, String remark) {
        try {
            Dict traceGoods = myTraceabilityFactoryService.changeTraceGoods(goodsGroup, goodsId, goodsStatus, remark);
            return new Result(true, MessageConstant.CHANGETRACEGOODS_SUCCESS, traceGoods);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.CHANGETRACEGOODS_FAIL);
        }
    }

    /**
     * 查看商品状态
     *
     * @param goodsGroup 商品hash值
     * @param goodsId    商品id
     */
    @RequestMapping("getStatus")
    public Result getStatus(String goodsGroup, int goodsId) {
        try {
            Dict status = myTraceabilityFactoryService.getStatus(goodsGroup, goodsId);
            return new Result(true,MessageConstant.GETSTATUS_SUCCESS,status);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GETSTATUS_FAIL);
        }
    }
}
