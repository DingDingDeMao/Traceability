package com.linzhongyoushenjun.Traceability.service.Impl;

import cn.hutool.core.lang.Dict;
import com.linzhongyoushenjun.Traceability.service.MyGoodsService;
import com.linzhongyoushenjun.Traceability.utils.WeBASEUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/**
 * @Author：林中有神君
 * @Date：2023/4/19 0:42
 * @Package：com.linzhongyoushenjun.Traceability.service
 */
@Service
public class MyGoodsServiceImpl implements MyGoodsService {
    public static final String  userAddress = "0xb58979a88e336e6b03c17241ccc6fa5085ed8f12";
    public static final String ABI=com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("abi/Goods.abi") ;
    public static final String contractName = "Goods";
    public static final String contractAddress = "0xd5c6f53f9e0f70997da3c40d2d430d930c938cfe";
    @Override
    public Dict changeStatus(int goodsStatus, String remark) {
        String funcName= "changeStatus";
        ArrayList<Object> funcParam = new ArrayList<>();
        funcParam.add(goodsStatus);
        funcParam.add(remark);
        return WeBASEUtils.requsert(userAddress, funcName, funcParam, ABI, contractName, contractAddress);
    }

    @Override
    public Dict getStatus() {
        String funcName= "getStatus";
        return WeBASEUtils.requsert(userAddress, funcName, null, ABI, contractName, contractAddress);
    }

    @Override
    public Dict getTraceInfo() {
        String funcName= "getTraceInfo";
        return WeBASEUtils.requsert(userAddress, funcName, null, ABI, contractName, contractAddress);
    }
}
