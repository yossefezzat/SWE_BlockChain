package VotingApp;

import java.security.*;

public class GenerateKeys {
	
	// Variables
	PrivateKey privateKey;
    PublicKey publicKey ;
	static Signature signature;
    
	// Constructor
    public GenerateKeys(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            // Initialize KeyPairGenerator.
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);

            // Generate Key Pairs, a private key and a public key.
            KeyPair keyPair = keyGen.generateKeyPair();
            privateKey = keyPair.getPrivate();
            publicKey = keyPair.getPublic();
        	
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
	}
	 
    /* ------------------------ Start Functions ------------------------------*/
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	/* ------------------------ End Functions ------------------------------*/
}