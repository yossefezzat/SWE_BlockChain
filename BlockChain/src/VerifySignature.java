package VotingApp;

import java.security.*;

public class VerifySignature {
	
	// Variables
	static PublicKey publicKey;
	static byte [] signature;
	static String data;
	
	// Constructor
	public VerifySignature(PublicKey pub, String msg, byte [] sig){
		signature = sig;
		publicKey = pub;
		data = msg;
	}
	 
	/* ---------------------------------- Start Function -------------------------------------*/
	public boolean verification(){
		boolean verifies = false;
		try {
			
			// Signature object generating
			Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
			sig.initVerify(publicKey);
			
			// Get Data Ready
			byte[] buffer = new byte[1024];
			buffer = data.getBytes();
			sig.update(buffer, 0, data.length());
			
			// Verfification Step	
			verifies = sig.verify(signature);
			
		} catch ( NoSuchAlgorithmException | NoSuchProviderException  | InvalidKeyException | SignatureException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return verifies;
	}
	
	/* ---------------------------------- End Function -------------------------------------*/

}
