import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class BlockChain {

		private ArrayList<Block> blockChain;
	
	/* --------------------- START CONSTRUCTOR ------------------------------*/
	
		BlockChain(){ 
			blockChain = new ArrayList<Block>();
			blockChain.add( Create_Genisis_BLock() );
			
	}
	
	/* --------------------- START FUNCTIONS ------------------------------*/

		
		private Block Create_Genisis_BLock(){
			
			return new Block(0 , "genusis block" , System.currentTimeMillis() , "0");
		}
		
		public void add_new_block(String data) {
			Block latest_block = get_latest_block();
			Block new_block =new Block(get_latest_block().getIndex() + 1 , data  , System.currentTimeMillis() , get_latest_block().getHash() );
			blockChain.add(new_block);
		}
		
		private Block  get_latest_block() {
			int Chain_Size = blockChain.size()-1;
			return blockChain.get(Chain_Size);
		}
		public void print() {
			for(int i=0 ; i < blockChain.size() ; i++) {
				System.out.println(blockChain.get(i).getData() + "\n" + blockChain.get(i).getHash() + "\n" + blockChain.get(i).getPreHash() + "\n" +blockChain.get(i).getTimeStamp());
				System.out.println("***********************************************************************************");
			}
		}

	
	
	/* ---------------------------------- END CLASS-----------------------------------*/
	
	
		
		public static void main ( String args[]){
			
			BlockChain obj = new BlockChain();
			obj.add_new_block("ezzat1");
			
			obj.add_new_block("ezzat2");
			
			obj.add_new_block("ezzat3");
			
			obj.print();
		
		
	}
}


