package misc;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Hasher {
	private static String salt = "B[3#@fdZd$1";
	private static int iterations = 1000;
	private static int keylength = 256;
	
    private static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;
        } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }
	
	public static String createHash(String to_hash) throws UnsupportedEncodingException{
		String hashed_val=null;
		char[] to_hash_chars = to_hash.toCharArray();
		byte[] salt_bytes = salt.getBytes();
		
		byte[] hashed_bytes = hashPassword(to_hash_chars, salt_bytes, iterations, keylength);
		hashed_val = Hex.encodeHexString(hashed_bytes);
		return hashed_val;
	}
    
    public static boolean verifyPassword(String to_check, String stored_password) throws UnsupportedEncodingException {
    	String hashed_pass = createHash(to_check);
    	return hashed_pass.equals(stored_password);
    }
	
}