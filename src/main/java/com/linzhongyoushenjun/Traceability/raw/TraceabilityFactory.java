import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int16;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint64;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple4;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.eventsub.EventCallback;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class TraceabilityFactory extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b50612312806100206000396000f300608060405260043610610078576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063718b108e1461007d5780637fc6e299146100a6578063c36c60bc146100e3578063e10fcc1c14610120578063e4d8420f1461015d578063ec0a49681461019a575b600080fd5b34801561008957600080fd5b506100a4600480360361009f9190810190610891565b6101d7565b005b3480156100b257600080fd5b506100cd60048036036100c8919081019061095e565b610276565b6040516100da9190610a8e565b60405180910390f35b3480156100ef57600080fd5b5061010a6004803603610105919081019061082c565b610301565b6040516101179190610ac4565b60405180910390f35b34801561012c57600080fd5b5061014760048036036101429190810190610855565b610475565b6040516101549190610aa9565b60405180910390f35b34801561016957600080fd5b50610184600480360361017f9190810190610855565b610533565b6040516101919190610aa9565b60405180910390f35b3480156101a657600080fd5b506101c160048036036101bc9190810190610855565b6105f1565b6040516101ce9190610adf565b60405180910390f35b60006101e2856106af565b90508073ffffffffffffffffffffffffffffffffffffffff1663839d051f8585856040518463ffffffff167c010000000000000000000000000000000000000000000000000000000002815260040161023d93929190610b77565b600060405180830381600087803b15801561025757600080fd5b505af115801561026b573d6000803e3d6000fd5b505050505050505050565b6000816040516020016102899190610afa565b6040516020818303038152906040526040518082805190602001908083835b6020831015156102cd57805182526020820191506020810190506020830392506102a8565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390209050919050565b600080600080846000191660001916815260200190815260200160002060000160149054906101000a900460ff16151515610371576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161036890610b3c565b60405180910390fd5b8261037a610762565b6103849190610a8e565b604051809103906000f0801580156103a0573d6000803e3d6000fd5b5090506001600080856000191660001916815260200190815260200160002060000160146101000a81548160ff02191690831515021790555080600080856000191660001916815260200190815260200160002060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055507f3d91d669381a724a278f7cd0fb4fd0c28267d23ed94cccf1896ceb2a4ee96700836040516104649190610a8e565b60405180910390a180915050919050565b600080610481846106af565b90508073ffffffffffffffffffffffffffffffffffffffff1663c81ee1cc846040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016104d89190610b5c565b602060405180830381600087803b1580156104f257600080fd5b505af1158015610506573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525061052a919081019061090c565b91505092915050565b60008061053f846106af565b90508073ffffffffffffffffffffffffffffffffffffffff16633cca0e1d846040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016105969190610b5c565b602060405180830381600087803b1580156105b057600080fd5b505af11580156105c4573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506105e8919081019061090c565b91505092915050565b6000806105fd846106af565b90508073ffffffffffffffffffffffffffffffffffffffff1663ba98d741846040518263ffffffff167c01000000000000000000000000000000000000000000000000000000000281526004016106549190610b5c565b602060405180830381600087803b15801561066e57600080fd5b505af1158015610682573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052506106a69190810190610935565b91505092915050565b6000806000836000191660001916815260200190815260200160002060000160149054906101000a900460ff16151561071d576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040161071490610b1c565b60405180910390fd5b600080836000191660001916815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b6040516115c080610d1983390190565b600061077e8235610c64565b905092915050565b60006107928251610c6e565b905092915050565b60006107a68235610c80565b905092915050565b60006107ba8251610c80565b905092915050565b600082601f83011215156107d557600080fd5b81356107e86107e382610be2565b610bb5565b9150808252602083016020830185838301111561080457600080fd5b61080f838284610cc5565b50505092915050565b60006108248235610c8d565b905092915050565b60006020828403121561083e57600080fd5b600061084c84828501610772565b91505092915050565b6000806040838503121561086857600080fd5b600061087685828601610772565b925050602061088785828601610818565b9150509250929050565b600080600080608085870312156108a757600080fd5b60006108b587828801610772565b94505060206108c687828801610818565b93505060406108d78782880161079a565b925050606085013567ffffffffffffffff8111156108f457600080fd5b610900878288016107c2565b91505092959194509250565b60006020828403121561091e57600080fd5b600061092c84828501610786565b91505092915050565b60006020828403121561094757600080fd5b6000610955848285016107ae565b91505092915050565b60006020828403121561097057600080fd5b600082013567ffffffffffffffff81111561098a57600080fd5b610996848285016107c2565b91505092915050565b6109a881610c39565b82525050565b6109b781610ca1565b82525050565b6109c681610cb3565b82525050565b6109d581610c43565b82525050565b60006109e682610c0e565b8084526109fa816020860160208601610cd4565b610a0381610d07565b602085010191505092915050565b6000601c82527f5468652074726164656d61726b20686173206e6f7420657869737473000000006020830152604082019050919050565b6000601c82527f5468652074726164656d61726b20616c726561647920657869737473000000006020830152604082019050919050565b610a8881610c50565b82525050565b6000602082019050610aa3600083018461099f565b92915050565b6000602082019050610abe60008301846109ae565b92915050565b6000602082019050610ad960008301846109bd565b92915050565b6000602082019050610af460008301846109cc565b92915050565b60006020820190508181036000830152610b1481846109db565b905092915050565b60006020820190508181036000830152610b3581610a11565b9050919050565b60006020820190508181036000830152610b5581610a48565b9050919050565b6000602082019050610b716000830184610a7f565b92915050565b6000606082019050610b8c6000830186610a7f565b610b9960208301856109cc565b8181036040830152610bab81846109db565b9050949350505050565b6000604051905081810181811067ffffffffffffffff82111715610bd857600080fd5b8060405250919050565b600067ffffffffffffffff821115610bf957600080fd5b601f19601f8301169050602081019050919050565b600081519050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b6000819050919050565b60008160010b9050919050565b600067ffffffffffffffff82169050919050565b6000819050919050565b6000610c7982610c19565b9050919050565b60008160010b9050919050565b600067ffffffffffffffff82169050919050565b6000610cac82610c19565b9050919050565b6000610cbe82610c19565b9050919050565b82818337600083830152505050565b60005b83811015610cf2578082015181840152602081019050610cd7565b83811115610d01576000848401525b50505050565b6000601f19601f83011690509190505600608060405234801561001057600080fd5b506040516020806115c0833981018060405281019080805190602001909291905050508060008160001916905550506115728061004e6000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633cca0e1d14610067578063839d051f146100de578063ba98d74114610168578063c81ee1cc146101b9575b600080fd5b34801561007357600080fd5b5061009c600480360381019080803567ffffffffffffffff169060200190929190505050610230565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100ea57600080fd5b50610166600480360381019080803567ffffffffffffffff169060200190929190803560010b9060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610425565b005b34801561017457600080fd5b5061019d600480360381019080803567ffffffffffffffff16906020019092919050505061061a565b604051808260010b60010b815260200191505060405180910390f35b3480156101c557600080fd5b506101ee600480360381019080803567ffffffffffffffff1690602001909291905050506107b8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600080600160008467ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff161515156102e0576040517f08c3","79a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260108152602001807f6973207265616c6c79206578697374730000000000000000000000000000000081525060200191505060405180910390fd5b60018060008567ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160146101000a81548160ff0219169083151502179055508261032b6108b8565b808267ffffffffffffffff1667ffffffffffffffff168152602001915050604051809103906000f080158015610365573d6000803e3d6000fd5b5090507f698cdfdcdf22c014830aa833c47ed6668672398159ebea6ed5d826734b97e4b783604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390a180600160008567ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080915050919050565b600160008467ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff1615156104d1576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600a8152602001807f6e6f74206578697374730000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160008467ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c6dc99e483836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360010b60010b815260200180602001828103825283818151815260200191508051906020019080838360005b838110156105b0578082015181840152602081019050610595565b50505050905090810190601f1680156105dd5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156105fd57600080fd5b505af1158015610611573d6000803e3d6000fd5b50505050505050565b6000600160008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff1615156106c8576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600a8152602001807f6e6f74206578697374730000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634e69d5606040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561077657600080fd5b505af115801561078a573d6000803e3d6000fd5b505050506040513d60208110156107a057600080fd5b81019080805190602001909291905050509050919050565b6000600160008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff161515610866576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600a8152602001807f6e6f74206578697374730000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b604051610c7e806108c983390190560060806040523480156200001157600080fd5b5060405160208062000c7e83398101806040526200003391908101906200029c565b806000806101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555060016080604051908101604052803273ffffffffffffffffffffffffffffffffffffffff168152602001600060010b81526020014281526020016040805190810160405280600681526020017f63726561746500000000000000000000000000000000000000000000000000008152508152509080600181540180825580915050906001820390600052602060002090600302016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548161ffff021916908360010b61ffff1602179055506040820151816001015560608201518160020190805190602001906200018e929190620001d7565b505050507fab7a8a9304245c885855f1bef2fc37dcdeed967745bdd93e4624bb7aa6c0bb2e32600042604051620001c89392919062000332565b60405180910390a150620003e3565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200021a57805160ff19168380011785556200024b565b828001600101855582156200024b579182015b828111156200024a5782518255916020019190600101906200022d565b5b5090506200025a91906200025e565b5090565b6200028391905b808211156200027f57600081600090555060010162000265565b5090565b90565b6000620002948251620003bb565b905092915050565b600060208284031215620002af57600080fd5b6000620002bf8482850162000286565b91505092915050565b620002d38162000384565b82525050565b620002e481620003cf565b82525050565b6000600682527f63726561746500000000000000000000000000000000000000000000000000006020830152604082019050919050565b6200032c81620003b1565b82525050565b6000608082019050620003496000830186620002c8565b620003586020830185620002d9565b62000367604083018462000321565b81810360608301526200037a81620002ea565b9050949350505050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008160010b9050919050565b6000819050919050565b600067ffffffffffffffff82169050919050565b6000620003dc82620003a4565b9050919050565b61088b80620003f36000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680634e69d5601461005c578063c6dc99e414610087578063f0d984e4146100b0575b600080fd5b34801561006857600080fd5b506100716100db565b60405161007e919061070b565b60405180910390f35b34801561009357600080fd5b506100ae60048036036100a991908101906104df565b6100f1565b005b3480156100bc57600080fd5b506100c5610251565b6040516100d291906106e9565b60405180910390f35b60008060089054906101000a900460010b905090565b81600060086101000a81548161ffff021916908360010b61ffff16021790555060016080604051908101604052803273ffffffffffffffffffffffffffffffffffffffff1681526020018460010b8152602001428152602001838152509080600181540180825580915050906001820390600052602060002090600302016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548161ffff021916908360010b61ffff16021790555060408201518160010155606082015181600201908051906020019061020c9291906103d0565b505050507fab7a8a9304245c885855f1bef2fc37dcdeed967745bdd93e4624bb7aa6c0bb2e32834284604051610245949392919061069d565b60405180910390a15050565b60606001805480602002602001604051908101604052809291908181526020016000905b828210156103c75783829060005260206000209060030201608060405190810160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160149054906101000a900460010b60010b60010b815260200160018201548152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103af5780601f10610384576101008083540402835291602001916103af565b820191906000526020600020905b81548152906001019060200180831161039257829003601f168201915b50505050508152505081526020019060010190610275565b50505050905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061041157805160ff191683800117855561043f565b8280016001018555821561043f579182015b8281111561043e578251825591602001919060010190610423565b5b50905061044c9190610450565b5090565b61047291905b8082111561046e576000816000905550600101610456565b5090565b90565b600061048182356107f1565b905092915050565b600082601f830112151561049c57600080fd5b81356104af6104aa82610753565b610726565b915080825260208301602083018583830111156104cb57600080fd5b6104d68382846107fe565b50505092915050565b600080604083850312156104f257600080fd5b600061050085828601610475565b925050602083013567ffffffffffffffff81111561051d57600080fd5b61052985828601610489565b9150509250929050565b61053c816107ba565b82525050565b600061054d8261078c565b808452602084019350836020820285016105668561077f565b60005b8481101561059f57838303885261058183835161062b565b925061058c826107ad565b9150602088019750600181019050610569565b508196508694505050505092915050565b6105b9816107da565b825250","50565b60006105ca826107a2565b8084526105de81602086016020860161080d565b6105e781610840565b602085010191505092915050565b600061060082610797565b80845261061481602086016020860161080d565b61061d81610840565b602085010191505092915050565b60006080830160008301516106436000860182610533565b50602083015161065660208601826105b0565b506040830151610669604086018261068e565b506060830151848203606086015261068182826105f5565b9150508091505092915050565b610697816107e7565b82525050565b60006080820190506106b26000830187610533565b6106bf60208301866105b0565b6106cc604083018561068e565b81810360608301526106de81846105bf565b905095945050505050565b600060208201905081810360008301526107038184610542565b905092915050565b600060208201905061072060008301846105b0565b92915050565b6000604051905081810181811067ffffffffffffffff8211171561074957600080fd5b8060405250919050565b600067ffffffffffffffff82111561076a57600080fd5b601f19601f8301169050602081019050919050565b6000602082019050919050565b600081519050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008160010b9050919050565b6000819050919050565b60008160010b9050919050565b82818337600083830152505050565b60005b8381101561082b578082015181840152602081019050610810565b8381111561083a576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820bffeab989961c27f1e9249a574fe38c1ae1395f82add3cfaeb6f4fc0ec6c53dc6c6578706572696d656e74616cf50037a165627a7a723058208f01748cd48dc600f0fc28c41e3b3b6a02337512997604b9815099628f3ee25a0029a265627a7a723058203f2d6fcb6abe30df9f47f95ee149b58f10a503228a4efc34b652db3838b1abde6c6578706572696d656e74616cf50037"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"},{\"name\":\"goodsId\",\"type\":\"uint64\"},{\"name\":\"goodsStatus\",\"type\":\"int16\"},{\"name\":\"remark\",\"type\":\"string\"}],\"name\":\"changeTraceGoods\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"name\",\"type\":\"string\"}],\"name\":\"getGoodsGroup\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"pure\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"}],\"name\":\"createTraceability\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"},{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"getTraceInfo\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"},{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"createTraceGoods\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"goodsGroup\",\"type\":\"bytes32\"},{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"getStatus\",\"outputs\":[{\"name\":\"\",\"type\":\"int16\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"goodsGroup\",\"type\":\"bytes32\"}],\"name\":\"newTraceEvent\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CHANGETRACEGOODS = "changeTraceGoods";

    public static final String FUNC_GETGOODSGROUP = "getGoodsGroup";

    public static final String FUNC_CREATETRACEABILITY = "createTraceability";

    public static final String FUNC_GETTRACEINFO = "getTraceInfo";

    public static final String FUNC_CREATETRACEGOODS = "createTraceGoods";

    public static final String FUNC_GETSTATUS = "getStatus";

    public static final Event NEWTRACEEVENT_EVENT = new Event("newTraceEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
    ;

    protected TraceabilityFactory(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt changeTraceGoods(byte[] goodsGroup, BigInteger goodsId, BigInteger goodsStatus, String remark) {
        final Function function = new Function(
                FUNC_CHANGETRACEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int16(goodsStatus), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void changeTraceGoods(byte[] goodsGroup, BigInteger goodsId, BigInteger goodsStatus, String remark, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CHANGETRACEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int16(goodsStatus), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForChangeTraceGoods(byte[] goodsGroup, BigInteger goodsId, BigInteger goodsStatus, String remark) {
        final Function function = new Function(
                FUNC_CHANGETRACEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int16(goodsStatus), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple4<byte[], BigInteger, BigInteger, String> getChangeTraceGoodsInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHANGETRACEGOODS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}, new TypeReference<Int16>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple4<byte[], BigInteger, BigInteger, String>(

                (byte[]) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue(), 
                (String) results.get(3).getValue()
                );
    }

    public byte[] getGoodsGroup(String name) throws ContractException {
        final Function function = new Function(FUNC_GETGOODSGROUP, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.Utf8String(name)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallWithSingleValueReturn(function, byte[].class);
    }

    public TransactionReceipt createTraceability(byte[] goodsGroup) {
        final Function function = new Function(
                FUNC_CREATETRACEABILITY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void createTraceability(byte[] goodsGroup, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATETRACEABILITY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateTraceability(byte[] goodsGroup) {
        final Function function = new Function(
                FUNC_CREATETRACEABILITY, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<byte[]> getCreateTraceabilityInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATETRACEABILITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<byte[]>(

                (byte[]) results.get(0).getValue()
                );
    }

    public Tuple1<String> getCreateTraceabilityOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATETRACEABILITY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public String getTraceInfo(byte[] goodsGroup, BigInteger goodsId) throws ContractException {
        final Function function = new Function(FUNC_GETTRACEINFO, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public TransactionReceipt createTraceGoods(byte[] goodsGroup, BigInteger goodsId) {
        final Function function = new Function(
                FUNC_CREATETRACEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void createTraceGoods(byte[] goodsGroup, BigInteger goodsId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATETRACEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateTraceGoods(byte[] goodsGroup, BigInteger goodsId) {
        final Function function = new Function(
                FUNC_CREATETRACEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple2<byte[], BigInteger> getCreateTraceGoodsInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATETRACEGOODS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}, new TypeReference<Uint64>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<byte[], BigInteger>(

                (byte[]) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue()
                );
    }

    public Tuple1<String> getCreateTraceGoodsOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATETRACEGOODS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public BigInteger getStatus(byte[] goodsGroup, BigInteger goodsId) throws ContractException {
        final Function function = new Function(FUNC_GETSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsGroup), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int16>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public List<NewTraceEventEventResponse> getNewTraceEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWTRACEEVENT_EVENT, transactionReceipt);
        ArrayList<NewTraceEventEventResponse> responses = new ArrayList<NewTraceEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewTraceEventEventResponse typedResponse = new NewTraceEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.goodsGroup = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeNewTraceEventEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(NEWTRACEEVENT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeNewTraceEventEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(NEWTRACEEVENT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static TraceabilityFactory load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new TraceabilityFactory(contractAddress, client, credential);
    }

    public static TraceabilityFactory deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(TraceabilityFactory.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }

    public static class NewTraceEventEventResponse {
        public TransactionReceipt.Logs log;

        public byte[] goodsGroup;
    }
}
