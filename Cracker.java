import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

//CS645
//Project 1
//Jaron Ge (jzg2)
//Jack Fredericks (jf455)

public class Cracker {

	public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		
		try {
		MessageDigest digest = MessageDigest.getInstance("MD5");

		ArrayList<String> shadow = readFile("C://users/jaron/645/shadow");
		ArrayList<String> commonPass = readFile("C://users/jaron/645/common-passwords.txt");
		
		String strShadow[] = shadow.toArray(new String[shadow.size()]);
		String strCommonPass[] = commonPass.toArray(new String[commonPass.size()]);
		
		for (int i = 0; i < strShadow.length; i++) { 
			String user = strShadow[i].substring(0, 5);
			//System.out.println(user);
			String salt = strShadow[i].substring(9, 17);
			//System.out.println(salt);
			String hash = strShadow[i].substring(18, 40);
			//System.out.println(hash);
			for(int j = 0; j < strCommonPass.length; j++) {
				String pw = strCommonPass[j];
				//System.out.println(pw);
				String crypto = MD5Shadow.crypt(pw, salt);
				//System.out.println(crypto);
				
				if (crypto.equals(hash)) {
					System.out.println(user + ":" + strCommonPass[j]);
				}
			
			}
		}
		}
		catch (IOException e) {
			System.out.println("error1");
		}
		catch (NoSuchAlgorithmException e) {
			System.out.println("error");
		}
	}
	
	public static ArrayList<String> readFile(String file) throws IOException {
		
		ArrayList<String> result = new ArrayList<>();
		 
		try (Scanner s = new Scanner(new FileReader(file))) {
		    while (s.hasNext()) {
		        result.add(s.nextLine());
		    }
		    return result;
		}
	}
	
	public static String toHex(byte[] bytes)
    {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "X", bi);
    }


	
}

	
