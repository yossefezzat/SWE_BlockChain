import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Block {
	
	private String hash, preHash, data;
	private long timeStamp;
	private int index;
	

	
	/* --------------------- START CONSTRUCTORS ------------------------------*/
	
	Block()  // Default
	{
		this.hash = "";
		this.preHash="";
		this.data = "";
		this.timeStamp = 0 ; 
		
	}
	
	Block(int INDEX ,String DATA , long TIME , String PRE_HASH)
	{
		this.index = INDEX;
		this.data = DATA;
		this.preHash = PRE_HASH ;
		this.timeStamp = TIME;
		this.hash = this.generateHash();
	}
	
	
	/* --------------------- START SETTER & GETTER ------------------------------*/
	
	public String getPreHash() {
		return preHash;
	}

	public void setPreHash(String preHash) {
		this.preHash = preHash;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getHash() {
		return hash;
	}


	public void setHash(String hash) {
		this.hash = hash;
	}
	
	/* --------------------- START FUNCTIONS ------------------------------*/
			
	
	private static String bytesToHex(byte[] hash) {    // Making hash decimal 
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1) 
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}


	private String generateHash(){
		MessageDigest digester = null;
		try {
			digester = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String originalString = this.hash + this.getPreHash() + this.getData();
		byte[] encodedHash = digester.digest(originalString.getBytes(StandardCharsets.UTF_8));		
		hash = bytesToHex(encodedHash);
		return hash;
	}
	/*

	public void test (){
		System.out.println("time: ");
		Scanner scan = new Scanner(System.in);
		timeStamp = scan.nextInt();
		scan.nextLine();
		System.out.println("data: ");
		data = scan.nextLine();
		System.out.println("Pre Hash: ");
		preHash = scan.nextLine();
		generateHash();
		System.out.println("Hash: " + hash);		
	}
	*/
}
