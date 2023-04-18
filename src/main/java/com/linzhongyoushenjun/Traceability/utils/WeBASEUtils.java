package com.linzhongyoushenjun.Traceability.utils;

import cn.hutool.core.lang.Dict;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 交易请求工具类
 */
@Service
public class WeBASEUtils {
        public static Dict requsert(
                String userAddress,
                String funcName,
                List funcParam,
                String ABI,
                String contractName,
                String contractAddress) {
            JSONArray abiJSON = JSONUtil.parseArray(ABI);
            JSONObject data = JSONUtil.createObj();
            data.set("groupId", "1"); //群组ID
            data.set("user", userAddress); //用户地址
            data.set("contractName", contractName); //合约名称
            data.set("version", "");  //cns版本
            data.set("funcName", funcName);  //方法名
            data.set("funcParam", funcParam);  //方法参数
            data.set("contractAddress", contractAddress); //合约地址
            data.set("contractAbi", abiJSON);  //合约编译后生成的abi文件内容
            data.set("useAes", false);
            data.set("cnsName", ""); //cns名称
            String dataString = JSONUtil.toJsonStr(data);
            String responseBody = HttpRequest.post("http://192.168.131.162:5002/WeBASE-Front/trans/handle")
                    .header(Header.CONTENT_TYPE, "application/json")
                    .body(dataString)
                    .execute()
                    .body();
            Dict retDict = new Dict();
            retDict.set("result", responseBody);
            return retDict;
        }
    }
