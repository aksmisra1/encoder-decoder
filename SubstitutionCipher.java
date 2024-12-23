// Substitution Cipher implementing MessageEncoder and MessageDecoder
public class SubstitutionCipher extends Cipher implements MessageEncoder, MessageDecoder {
    private int shift;

    // Constructor to set the shift value
    public SubstitutionCipher(int shift) {
        this.shift = shift;
    }

    // Method to define cipher type
    @Override
    public String cipherType() {
        return "SubstitutionCipher";
    }

    // Method to encode the message
    @Override
    public String encode(String plainText) {
        StringBuilder encoded = new StringBuilder();
        for (char ch : plainText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base + shift) % 26 + base);
            }
            encoded.append(ch);
        }
        return encoded.toString();
    }

    // Method to decode the message
    @Override
    public String decode(String cipherText) {
        StringBuilder decoded = new StringBuilder();
        for (char ch : cipherText.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) ((ch - base - shift + 26) % 26 + base);
            }
            decoded.append(ch);
        }
        return decoded.toString();
    }
}
