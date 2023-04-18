package com.linzhongyoushenjun.Traceability.service.Impl;
import cn.hutool.core.lang.Dict;
import com.linzhongyoushenjun.Traceability.service.MyTraceabilityFactoryService;
import com.linzhongyoushenjun.Traceability.utils.WeBASEUtils;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
/**
 * @Author：林中有神君
 * @Date：2023/4/19 0:42
 * @Package：com.linzhongyoushenjun.Traceability.service
 */
@Service
public class TraceabilityFactoryServiceImpl implements MyTraceabilityFactoryService {
    public static final String  userAddress = "0xb58979a88e336e6b03c17241ccc6fa5085ed8f12";
    public static final String ABI=com.linzhongyoushenjun.Traceability.utils.IOUtil.readResourceAsString("abi/TraceabilityFactory.abi") ;
    public static final String contractName = "TraceabilityFactory";
    public static final String contractAddress = "0xb86079ade5852016817202dbf1b711121e6ed29f";

    @Override
    public Dict getGoodsGroup(String goodsName) {
        String funcName= "getGoodsGroup";
        ArrayList<String> funcParam = new ArrayList<>();
        funcParam.add(goodsName);
        return WeBASEUtils.requsert(userAddress, funcName, funcParam, ABI, contractName, contractAddress);
    }

    @Override
    public Dict createTraceability(String goodsGroup) {
        String funcName= "createTraceability";
        ArrayList<String> funcParam = new ArrayList<>();
        funcParam.add(goodsGroup);
        return WeBASEUtils.requsert(userAddress, funcName, funcParam, ABI, contractName, contractAddress);
    }

    @Override
    public Dict createTraceGoods(String goodsGroup, int goodsId) {
        String funcName= "createTraceGoods";
        ArrayList<Object> funcParam = new ArrayList<>();
        funcParam.add(goodsGroup);
        funcParam.add(goodsId);
        return WeBASEUtils.requsert(userAddress, funcName, funcParam, ABI, contractName, contractAddress);
    }

    @Override
    public Dict getTraceInfo(String goodsGroup, int goodsId) {
        String funcName= "getTraceInfo";
        ArrayList<Object> funcParam = new ArrayList<>();
        funcParam.add(goodsGroup);
        funcParam.add(goodsId);
        return WeBASEUtils.requsert(userAddress, funcName, funcParam, ABI, contractName, contractAddress);
    }

    @Override
    public Dict changeTraceGoods(String goodsGroup, int goodsId, int goodsStatus, String remark) {
        String funcName= "changeTraceGoods";
        ArrayList<Object> funcParam = new ArrayList<>();
        funcParam.add(goodsGroup);
        funcParam.add(goodsId);
        funcParam.add(goodsStatus);
        funcParam.add(remark);
        return WeBASEUtils.requsert(userAddress, funcName, funcParam, ABI, contractName, contractAddress);
    }

    @Override
    public Dict getStatus(String goodsGroup, int goodsId) {
        String funcName= "getStatus";
        ArrayList<Object> funcParam = new ArrayList<>();
        funcParam.add(goodsGroup);
        funcParam.add(goodsId);
        return WeBASEUtils.requsert(userAddress, funcName, funcParam, ABI, contractName, contractAddress);
    }
}
