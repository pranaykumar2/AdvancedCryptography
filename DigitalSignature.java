import java.security.*;
import java.util.Base64;

public class DigitalSignature {

    private static final String RSA = "RSA";
    private static final String SIGNING_ALGORITHM = "SHA256withRSA";

    public static KeyPair generateRSAKeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(2048, secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] createDigitalSignature(byte[] input, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(input);
        return signature.sign();
    }

    public static boolean verifyDigitalSignature(byte[] input, byte[] signatureToVerify, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(input);
        return signature.verify(signatureToVerify);
    }

    public static void main(String[] args) throws Exception {
        String input = "This is a simple test message for digital signature.";
        KeyPair keyPair = generateRSAKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        byte[] signature = createDigitalSignature(input.getBytes(), privateKey);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

        boolean isVerified = verifyDigitalSignature(input.getBytes(), signature, publicKey);
        System.out.println("Verification: " + isVerified);
    }
}
