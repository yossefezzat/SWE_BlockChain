package VotingApp;

import java.security.*;

public class SignatureGenerator {
    
	// Variables
	PrivateKey priv ;
    String data;
     
    // Constructor
	public SignatureGenerator(PrivateKey privateKey, String msg) {
		priv = privateKey;
		data = msg;
	}
	
	/* ---------------------------------- Start Function -------------------------------------*/
	public byte [] signData(){
		
		byte[] realSig = null;
		
		try {
			
			// Initialize Signature
	        Signature sign = Signature.getInstance("SHA1withDSA", "SUN");
	        sign.initSign(priv);
	        
	        // Update Data
			byte[] buffer = new byte[1024];
			buffer = data.getBytes();
			sign.update(buffer, 0, data.length());
	        
	        // Sign This Data 
			realSig = sign.sign();

	      } catch (Exception e) {
	        System.err.println("Caught exception " + e.toString());
	      }
		
        return realSig;
	}
	/* ---------------------------------- End Function -------------------------------------*/
}
