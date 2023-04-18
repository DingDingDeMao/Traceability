package com.linzhongyoushenjun.Traceability.service;

import cn.hutool.core.lang.Dict;
/**
 * @Author：林中有神君
 * @Date：2023/4/19 0:42
 * @Package：com.linzhongyoushenjun.Traceability.service
 */
public interface MyGoodsService {
    Dict changeStatus(int goodsStatus, String remark);

    Dict getStatus();

    Dict getTraceInfo();
}
