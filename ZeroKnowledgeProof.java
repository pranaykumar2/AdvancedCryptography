import java.util.Random;

class ZeroKnowledgeProof {

    private int secretNumber;

    public ZeroKnowledgeProof(int secretNumber) {
        this.secretNumber = secretNumber;
    }

    public int commitment() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);

        return secretNumber + randomNumber;
    }

    public int reveal(int commitment, int randomNumber) {
        return commitment - randomNumber;
    }

    public boolean verify(int commitment, int randomNumber, int revealedNumber) {
        return (revealedNumber + randomNumber) == commitment;
    }

    public static void main(String[] args) {
        ZeroKnowledgeProof zkp = new ZeroKnowledgeProof(5);

        int commitment = zkp.commitment();
        System.out.println("Commitment: " + commitment);

        int revealedNumber = zkp.reveal(commitment, 3);
        System.out.println("Revealed Number: " + revealedNumber);

        boolean isVerified = zkp.verify(commitment, 3, revealedNumber);
        System.out.println("Is Verified: " + isVerified);
    }
}
