pragma solidity ^0.4.25;
pragma experimental ABIEncoderV2;

import "./Traceability.sol";
//本合约支持产品操作。包含产品类hash的创建，产品溯源创建，产品示例的创建，改变状态追踪，获取当前状态，获取溯源产品地址
contract TraceabilityFactory{
    struct GoodsTrace{
        Traceability trace;
        bool valid;
    }
    // 声明映射，将货物类别与GoodsTrace结构体对应
    mapping(bytes32=>GoodsTrace) private _goodsCategory;
    //用于通知新的溯源商品类别已创建
    event newTraceEvent(bytes32 goodsGroup);
	
    //Create traceability commodity category
    function createTraceability(bytes32  goodsGroup) public returns(Traceability) {
        // 判断货物类别是否已经存在
        require(!_goodsCategory[goodsGroup].valid,"The trademark already exists" );
         // 创建Traceability合约实例
        Traceability category = new Traceability(goodsGroup);
        // 将货物类别标记为已验证
        _goodsCategory[goodsGroup].valid = true;
        // 将Traceability合约实例与货物类别进行对应
        _goodsCategory[goodsGroup].trace = category;
        // 触发newTraceEvent事件，通知新的溯源商品类别已创建
        emit newTraceEvent(goodsGroup);
        // 返回Traceability合约实例
        return category;
    }
    // 根据所属产品组的goodsGroup，创建并初始化溯源合约，返回Traceability合约
    function getTraceability(bytes32  goodsGroup) private view returns(Traceability) {
         // 判断货物类别是否存在
       require(_goodsCategory[goodsGroup].valid,"The trademark has not exists" );
        // 返回Traceability合约实例
       return _goodsCategory[goodsGroup].trace;
    }
    // 根据所属产品组的goodsGroup，以及当前产品ID，创建并初始化本产品，返回产品地址  
    function createTraceGoods(bytes32  goodsGroup, uint64 goodsId) public returns(Goods) {
         // 获取对应货物类别的Traceability合约实例
        Traceability category = getTraceability(goodsGroup);
        // 调用Traceability合约中的createGoods函数创建溯源商品
        return category.createGoods(goodsId);
    }
    
    //根据所属产品组的goodsGroup，以及当前产品ID，更改产品的状态，和当前标志
    function changeTraceGoods(bytes32  goodsGroup, uint64 goodsId, int16 goodsStatus, string memory remark) public {
        // 获取对应货物类别的Traceability合约实例
        Traceability category = getTraceability(goodsGroup);
         // 调用Traceability合约中的changeGoodsStatus函数更改商品状态
        category.changeGoodsStatus(goodsId, goodsStatus, remark);
    }
    
    //根据所属产品组的goodsGroup，以及当前产品ID，获取当前产品的状态
    function getStatus(bytes32 goodsGroup, uint64 goodsId) public view returns(int16) {
          // 获取对应货物类别的Traceability合约实例
        Traceability category = getTraceability(goodsGroup);
         // 调用Traceability合约中的getStatus函数查询商品当前状态
        return category.getStatus(goodsId);
    }
    
    // 根据所属产品组的goodsGroup，以及当前产品ID，获取当前产品的地址
    // function getTraceInfo(bytes32 goodsGroup, uint64 goodsId) public view returns(Goods.TraceData[]) {
    //     // 获取对应货物类别的Traceability合约实例
    //     Traceability category = getTraceability(goodsGroup);
    //     // 调用Traceability合约中的getGoods函数查询商品的整个溯源过程
    //     return category.getGoods(goodsId).getTraceInfo();
    // }
      function getTraceInfo(bytes32 goodsGroup, uint64 goodsId)public view returns(Goods){
         Traceability category = getTraceability(goodsGroup);
         return category.getGoods(goodsId);
    }
    //根据产品名创建产品组hash值，返回bytes32类型的goodsGroup
    function getGoodsGroup(string memory name) public pure returns (bytes32) {
        return keccak256(abi.encode(name));
    }
}