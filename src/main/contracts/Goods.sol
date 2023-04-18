pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

contract Goods{
    struct TraceData{
        //操作人地址
        address addr;     //Operator address
        //商品状态
        int16 status;     //goods status
        //操作时间
        uint timestamp;   //Operator time
        //数据摘要
        string remark;    //Digested Data
    }
     //商品编号
    uint64 _goodsId; 
    //当前商品状态
    int16 _status;   //current status
    //存储追溯数据的数组
    TraceData[] _traceData;
    //定义状态改变事件
    event newStatus( address addr, int16 status, uint timestamp, string remark);
    //构造函数，默认调用此方法为增加商品
    constructor(uint64 goodsId) public{
        _goodsId = goodsId;
        _traceData.push(TraceData({addr:tx.origin, status:0, timestamp:now, remark:"create"}));
        emit newStatus(tx.origin, 0, now, "create");
    }
    //改变商品状态
    function changeStatus(int16 goodsStatus, string memory remark) public {
        _status = goodsStatus;
         _traceData.push(TraceData({addr:tx.origin, status:goodsStatus, timestamp:now, remark:remark}));
          emit newStatus(tx.origin, goodsStatus, now, remark);
    }
     //获取商品状态
    function getStatus()public view returns(int16){
        return _status;
    }
    //获取商品追溯信息
    function getTraceInfo()public view returns(TraceData[] memory _data){
        return _traceData;
    }
}
