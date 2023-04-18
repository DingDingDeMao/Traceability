import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionEncoder;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Address;
import org.fisco.bcos.sdk.abi.datatypes.Event;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.Utf8String;
import org.fisco.bcos.sdk.abi.datatypes.generated.Int16;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint64;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple3;
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
public class Traceability extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b506040516020806115c0833981018060405281019080805190602001909291905050508060008160001916905550506115728061004e6000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633cca0e1d14610067578063839d051f146100de578063ba98d74114610168578063c81ee1cc146101b9575b600080fd5b34801561007357600080fd5b5061009c600480360381019080803567ffffffffffffffff169060200190929190505050610230565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b3480156100ea57600080fd5b50610166600480360381019080803567ffffffffffffffff169060200190929190803560010b9060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610425565b005b34801561017457600080fd5b5061019d600480360381019080803567ffffffffffffffff16906020019092919050505061061a565b604051808260010b60010b815260200191505060405180910390f35b3480156101c557600080fd5b506101ee600480360381019080803567ffffffffffffffff1690602001909291905050506107b8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b600080600160008467ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff161515156102e0576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260108152602001807f6973207265616c6c79206578697374730000000000000000000000000000000081525060200191505060405180910390fd5b60018060008567ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160146101000a81548160ff0219169083151502179055508261032b6108b8565b808267ffffffffffffffff1667ffffffffffffffff168152602001915050604051809103906000f080158015610365573d6000803e3d6000fd5b5090507f698cdfdcdf22c014830aa833c47ed6668672398159ebea6ed5d826734b97e4b783604051808267ffffffffffffffff1667ffffffffffffffff16815260200191505060405180910390a180600160008567ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555080915050919050565b600160008467ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff1615156104d1576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600a8152602001807f6e6f74206578697374730000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160008467ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1663c6dc99e483836040518363ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808360010b60010b815260200180602001828103825283818151815260200191508051906020019080838360005b838110156105b0578082015181840152602081019050610595565b50505050905090810190601f1680156105dd5780820380516001836020036101000a031916815260200191505b509350505050600060405180830381600087803b1580156105fd57600080fd5b505af1158015610611573d6000803e3d6000fd5b50505050505050565b6000600160008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff1615156106c8576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600a8152602001807f6e6f74206578697374730000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16634e69d5606040518163ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401602060405180830381600087803b15801561077657600080fd5b505af115801561078a573d6000803e3d6000fd5b505050506040513d60208110156107a057600080fd5b81019080805190602001909291905050509050919050565b6000600160008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160149054906101000a900460ff161515610866576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252600a8152602001807f6e6f74206578697374730000000000000000000000000000000000000000000081525060200191505060405180910390fd5b600160008367ffffffffffffffff1667ffffffffffffffff16815260200190815260200160002060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff169050919050565b604051610c7e806108c983390190560060806040523480156200001157600080fd5b5060405160208062000c7e83398101806040526200003391908101906200029c565b806000806101000a81548167ffffffffffffffff021916908367ffffffffffffffff16021790555060016080604051908101604052803273ffffffffffffffffffffffffffffffffffffffff168152602001600060010b81526020014281526020016040805190810160405280600681526020017f63726561746500000000000000000000000000000000000000000000000000008152508152509080600181540180825580915050906001820390600052602060002090600302016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548161ffff021916908360010b61ffff1602179055506040820151816001015560608201518160020190805190602001906200018e929190620001d7565b505050507fab7a8a9304245c885855f1bef2fc37dcdeed967745bdd93e4624bb7aa6c0bb2e32600042604051620001c89392919062000332565b60405180910390a150620003e3565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106200021a57805160ff19168380011785556200024b565b828001600101855582156200024b579182015b828111156200024a5782518255916020019190600101906200022d565b5b5090506200025a91906200025e565b5090565b6200028391905b808211156200027f57600081600090555060010162000265565b5090565b90565b6000620002948251620003bb565b905092915050565b600060208284031215620002af57600080fd5b6000620002bf8482850162000286565b91505092915050565b620002d38162000384565b82525050565b620002e481620003cf565b82525050565b6000600682527f63726561746500000000000000000000000000000000000000000000000000006020830152604082019050919050565b6200032c81620003b1565b82525050565b6000608082019050620003496000830186620002c8565b620003586020830185620002d9565b62000367604083018462000321565b81810360608301526200037a81620002ea565b9050949350505050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008160010b9050919050565b6000819050919050565b600067ffffffffffffffff82169050919050565b6000620003dc82620003a4565b9050919050565b61088b80620003f36000396000f300608060405260043610610057576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680634e69d5601461005c578063c6dc99e414610087578063f0d984e4146100b0575b600080fd5b34801561006857600080fd5b506100716100db565b60405161007e919061070b565b60405180910390f35b34801561009357600080fd5b506100ae60048036036100a991908101906104df565b6100f1565b005b3480156100bc57600080fd5b506100c5610251565b6040516100d291906106e9565b60405180910390f35b60008060089054906101000a900460010b905090565b81600060086101000a81548161ffff021916908360010b61ffff16021790555060016080604051908101604052803273ffffffffffffffffffffffffffffffffffffffff1681526020018460010b8152602001428152602001838152509080600181540180825580915050906001820390600052602060002090600302016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160000160146101000a81548161ffff021916908360010b61ffff16021790555060408201518160010155606082015181600201908051906020019061020c9291906103d0565b505050507fab7a8a9304245c885855f1bef2fc37dcdeed967745bdd93e4624bb7aa6c0bb2e32834284604051610245949392919061069d565b60405180910390a15050565b60606001805480602002602001604051908101604052809291908181526020016000905b828210156103c75783829060005260206000209060030201608060405190810160405290816000820160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020016000820160","149054906101000a900460010b60010b60010b815260200160018201548152602001600282018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103af5780601f10610384576101008083540402835291602001916103af565b820191906000526020600020905b81548152906001019060200180831161039257829003601f168201915b50505050508152505081526020019060010190610275565b50505050905090565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061041157805160ff191683800117855561043f565b8280016001018555821561043f579182015b8281111561043e578251825591602001919060010190610423565b5b50905061044c9190610450565b5090565b61047291905b8082111561046e576000816000905550600101610456565b5090565b90565b600061048182356107f1565b905092915050565b600082601f830112151561049c57600080fd5b81356104af6104aa82610753565b610726565b915080825260208301602083018583830111156104cb57600080fd5b6104d68382846107fe565b50505092915050565b600080604083850312156104f257600080fd5b600061050085828601610475565b925050602083013567ffffffffffffffff81111561051d57600080fd5b61052985828601610489565b9150509250929050565b61053c816107ba565b82525050565b600061054d8261078c565b808452602084019350836020820285016105668561077f565b60005b8481101561059f57838303885261058183835161062b565b925061058c826107ad565b9150602088019750600181019050610569565b508196508694505050505092915050565b6105b9816107da565b82525050565b60006105ca826107a2565b8084526105de81602086016020860161080d565b6105e781610840565b602085010191505092915050565b600061060082610797565b80845261061481602086016020860161080d565b61061d81610840565b602085010191505092915050565b60006080830160008301516106436000860182610533565b50602083015161065660208601826105b0565b506040830151610669604086018261068e565b506060830151848203606086015261068182826105f5565b9150508091505092915050565b610697816107e7565b82525050565b60006080820190506106b26000830187610533565b6106bf60208301866105b0565b6106cc604083018561068e565b81810360608301526106de81846105bf565b905095945050505050565b600060208201905081810360008301526107038184610542565b905092915050565b600060208201905061072060008301846105b0565b92915050565b6000604051905081810181811067ffffffffffffffff8211171561074957600080fd5b8060405250919050565b600067ffffffffffffffff82111561076a57600080fd5b601f19601f8301169050602081019050919050565b6000602082019050919050565b600081519050919050565b600081519050919050565b600081519050919050565b6000602082019050919050565b600073ffffffffffffffffffffffffffffffffffffffff82169050919050565b60008160010b9050919050565b6000819050919050565b60008160010b9050919050565b82818337600083830152505050565b60005b8381101561082b578082015181840152602081019050610810565b8381111561083a576000848401525b50505050565b6000601f19601f83011690509190505600a265627a7a72305820bffeab989961c27f1e9249a574fe38c1ae1395f82add3cfaeb6f4fc0ec6c53dc6c6578706572696d656e74616cf50037a165627a7a723058208f01748cd48dc600f0fc28c41e3b3b6a02337512997604b9815099628f3ee25a0029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"createGoods\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"goodsId\",\"type\":\"uint64\"},{\"name\":\"goodsStatus\",\"type\":\"int16\"},{\"name\":\"remark\",\"type\":\"string\"}],\"name\":\"changeGoodsStatus\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"getStatus\",\"outputs\":[{\"name\":\"\",\"type\":\"int16\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"getGoods\",\"outputs\":[{\"name\":\"\",\"type\":\"address\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"name\":\"goodsTp\",\"type\":\"bytes32\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"goodsId\",\"type\":\"uint64\"}],\"name\":\"newGoodsEvent\",\"type\":\"event\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_CREATEGOODS = "createGoods";

    public static final String FUNC_CHANGEGOODSSTATUS = "changeGoodsStatus";

    public static final String FUNC_GETSTATUS = "getStatus";

    public static final String FUNC_GETGOODS = "getGoods";

    public static final Event NEWGOODSEVENT_EVENT = new Event("newGoodsEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
    ;

    protected Traceability(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt createGoods(BigInteger goodsId) {
        final Function function = new Function(
                FUNC_CREATEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void createGoods(BigInteger goodsId, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForCreateGoods(BigInteger goodsId) {
        final Function function = new Function(
                FUNC_CREATEGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getCreateGoodsInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEGOODS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple1<String> getCreateGoodsOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_CREATEGOODS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<String>(

                (String) results.get(0).getValue()
                );
    }

    public TransactionReceipt changeGoodsStatus(BigInteger goodsId, BigInteger goodsStatus, String remark) {
        final Function function = new Function(
                FUNC_CHANGEGOODSSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int16(goodsStatus), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

    public void changeGoodsStatus(BigInteger goodsId, BigInteger goodsStatus, String remark, TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CHANGEGOODSSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int16(goodsStatus), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String getSignedTransactionForChangeGoodsStatus(BigInteger goodsId, BigInteger goodsStatus, String remark) {
        final Function function = new Function(
                FUNC_CHANGEGOODSSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId), 
                new org.fisco.bcos.sdk.abi.datatypes.generated.Int16(goodsStatus), 
                new org.fisco.bcos.sdk.abi.datatypes.Utf8String(remark)), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple3<BigInteger, BigInteger, String> getChangeGoodsStatusInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CHANGEGOODSSTATUS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint64>() {}, new TypeReference<Int16>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, BigInteger, String>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public BigInteger getStatus(BigInteger goodsId) throws ContractException {
        final Function function = new Function(FUNC_GETSTATUS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Int16>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public String getGoods(BigInteger goodsId) throws ContractException {
        final Function function = new Function(FUNC_GETGOODS, 
                Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Uint64(goodsId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public List<NewGoodsEventEventResponse> getNewGoodsEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWGOODSEVENT_EVENT, transactionReceipt);
        ArrayList<NewGoodsEventEventResponse> responses = new ArrayList<NewGoodsEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewGoodsEventEventResponse typedResponse = new NewGoodsEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.goodsId = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void subscribeNewGoodsEventEvent(String fromBlock, String toBlock, List<String> otherTopics, EventCallback callback) {
        String topic0 = eventEncoder.encode(NEWGOODSEVENT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,fromBlock,toBlock,otherTopics,callback);
    }

    public void subscribeNewGoodsEventEvent(EventCallback callback) {
        String topic0 = eventEncoder.encode(NEWGOODSEVENT_EVENT);
        subscribeEvent(ABI,BINARY,topic0,callback);
    }

    public static Traceability load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Traceability(contractAddress, client, credential);
    }

    public static Traceability deploy(Client client, CryptoKeyPair credential, byte[] goodsTp) throws ContractException {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.fisco.bcos.sdk.abi.datatypes.generated.Bytes32(goodsTp)));
        return deploy(Traceability.class, client, credential, getBinary(client.getCryptoSuite()), encodedConstructor);
    }

    public static class NewGoodsEventEventResponse {
        public TransactionReceipt.Logs log;

        public BigInteger goodsId;
    }
}
