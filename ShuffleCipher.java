// Shuffle Cipher implementing MessageEncoder and MessageDecoder
public class ShuffleCipher extends Cipher implements MessageEncoder, MessageDecoder {
    private int shuffleCount;

    // Constructor to set the shuffle count
    public ShuffleCipher(int shuffleCount) {
        this.shuffleCount = shuffleCount;
    }

    // Method to define cipher type
    @Override
    public String cipherType() {
        return "ShuffleCipher";
    }

    // Helper method to shuffle a string once
    private String shuffle(String text) {
        StringBuilder shuffled = new StringBuilder();
        int mid = text.length() / 2;
        for (int i = 0; i < mid; i++) {
            shuffled.append(text.charAt(i));
            if (mid + i < text.length()) {
                shuffled.append(text.charAt(mid + i));
            }
        }
        return shuffled.toString();
    }

    // Helper method to unshuffle a string once
    private String unshuffle(String text) {
        int mid = (text.length() + 1) / 2;
        StringBuilder unshuffled = new StringBuilder();
        for (int i = 0; i < mid; i++) {
            unshuffled.append(text.charAt(i));
            if (i + mid < text.length()) {
                unshuffled.append(text.charAt(i + mid));
            }
        }
        return unshuffled.toString();
    }

    // Method to encode the message
    @Override
    public String encode(String plainText) {
        String result = plainText;
        for (int i = 0; i < shuffleCount; i++) {
            result = shuffle(result);
        }
        return result;
    }

    // Method to decode the message
    @Override
    public String decode(String cipherText) {
        String result = cipherText;
        for (int i = 0; i < shuffleCount; i++) {
            result = unshuffle(result);
        }
        return result;
    }
}
