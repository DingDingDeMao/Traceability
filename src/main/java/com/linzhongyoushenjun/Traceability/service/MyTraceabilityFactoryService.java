package com.linzhongyoushenjun.Traceability.service;
import cn.hutool.core.lang.Dict;

/**
 * @Author：林中有神君
 * @Date：2023/4/19 0:42
 * @Package：com.linzhongyoushenjun.Traceability.service
 */
public interface MyTraceabilityFactoryService {
    Dict getGoodsGroup(String goodsName);

    Dict createTraceability(String goodsGroup);

    Dict createTraceGoods(String goodsGroup, int goodsId);

    Dict getTraceInfo(String goodsGroup, int goodsId);

    Dict changeTraceGoods(String goodsGroup, int goodsId, int goodsStatus, String remark);

    Dict getStatus(String goodsGroup, int goodsId);
}
