package com.linzhongyoushenjun.Traceability;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.linzhongyoushenjun.Traceability.config.RabbitConfig;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.crypto.keypair.ECDSAKeyPair;
import org.fisco.bcos.sdk.crypto.keypair.SM2KeyPair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoPkey {

    @Test
    public void keyGeneration() throws Exception {
        //ECDSA key generation
        CryptoKeyPair ecdsaKeyPair = new ECDSAKeyPair().generateKeyPair();
        System.out.println("ecdsa private key :" + ecdsaKeyPair.getHexPrivateKey());
        System.out.println("ecdsa public key :" + ecdsaKeyPair.getHexPublicKey());
        System.out.println("ecdsa address :" + ecdsaKeyPair.getAddress());
        //SM2 key generation
        CryptoKeyPair sm2KeyPair = new SM2KeyPair().generateKeyPair();
        System.out.println("sm2 private key :" + sm2KeyPair.getHexPrivateKey());
        System.out.println("sm2 public key :" + sm2KeyPair.getHexPublicKey());
        System.out.println("sm2 address :" + sm2KeyPair.getAddress());
    }


    @Resource
    RabbitTemplate rabbitTemplate;

    //获取出块事件的订阅信息列表
    @Test
    public void publishTest() {
//        String msg="hello rabbitmq!publishTest";
//        http://localhost:5002/WeBASE-Front/event/newBlockEvent/{groupId}/{appId}
        int groupId = 1;
//        String appId = "001";
        String responseBody = HttpRequest.get("http://192.168.131.162:5002/WeBASE-Front/event/newBlockEvent/list/"+groupId+"")
                .header(Header.CONTENT_TYPE, "application/json")
                .execute()
                .body();
        System.out.println("订阅消息responseBody = " + responseBody);
        rabbitTemplate.convertAndSend(RabbitConfig.WEBASE_FRONT_EXCHAGE, "", responseBody);

    }

    @Test
    public void getEvent() {
//        String msg="hello rabbitmq!publishTest";
//        http://localhost:5002/WeBASE-Front/event/newBlockEvent/{groupId}/{appId}
        int groupId = 1;
        String appId = "uKAVqxJ2";
        String responseBody = HttpRequest.get("http://192.168.131.162:5002/WeBASE-Front/event/newBlockEvent/"+groupId+'/'+appId+"")
                .header(Header.CONTENT_TYPE, "application/json")
                .execute()
                .body();
        System.out.println("订阅消息responseBody = " + responseBody);
        rabbitTemplate.convertAndSend(RabbitConfig.WEBASE_FRONT_EXCHAGE, "", responseBody);

    }
//    http://localhost:5002/WeBASE-Front/event/eventLogs/list
    @Test
    public void EventLog() {
    //        String msg="hello rabbitmq!publishTest";
    //        http://localhost:5002/WeBASE-Front/event/newBlockEvent/{groupId}/{appId}
//        所属群组 	groupId 	Integer 		是
//        2 	合约地址 	contractAddress 	String 		是 	已部署合约
//        3 	合约ABI 	contractAbi 	List 		是
//        2 	Topic参数 	topics 	EventTopicParam 		是 	EventTopicParam包含{String eventName,IndexedParamType indexed1,IndexedParamType indexed2,IndexedParamType indexed3},其中IndexedParamType包含{String type,String value}。eventName为包含参数类型的event名，如SetEvent(uint256,string)，IndexedParamType中type为indexed参数的类型，value为eventlog需要过滤的参数值
//        2 	开始区块 	fromBlock 	Integer 		是 	始块高
//        2 	末区块 	toBlock 	Integer
//        int groupId = 1;
//        String contractAddress = "0xb86079ade5852016817202dbf1b711121e6ed29f";
//        ArrayList<Object> list = new ArrayList<>();
//        list.add("[{\"constant\":false,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"},{\"name\":\"goodsId\",\"type\":\"uint64\"},{\"name\":\"goodsStatus\",\"type\":\"int16\"},{\"name\":\"remark\",\"type\":\"string\"}],\"name\":\"changeTraceGoods\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"}],\"name\":\"getGoodsGroup\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"pure\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"}],\"name\":\"createTraceability\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"},{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"getTraceInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"},{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"createTraceGoods\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"},{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"getStatus\",\"outputs\":[{\"name\":\"\",\"type\":\"int16\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"goodsGroup\",\"type\":\"bytes32\"}],\"name\":\"newTraceEvent\",\"type\":\"event\"}]");
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put("eventName","newTraceEvent(bytes32 goodsGroup)");
//        map.put("IndexedParamType","")
//        String responseBody = HttpRequest.get("http://192.168.131.162:5002/WeBASE-Front/event/newBlockEvent/"+groupId+'/'+appId+"")
//                .header(Header.CONTENT_TYPE, "application/json")
//                .execute()
//                .body();
//        System.out.println("订阅消息responseBody = " + responseBody);
//        rabbitTemplate.convertAndSend(RabbitConfig.WEBASE_FRONT_EXCHAGE, "", responseBody);

    }
}
