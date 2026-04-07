package computer;

public class Wire
{
    public static int bits2Int(boolean[] bits)
    {
        int power = 0;
        int result = 0;
        for(int step = bits.length-1; step >= 0; step--)
        {
            if(bits[step])
            {
                result += Math.pow(2,power);
            }
            power++;
        }
        return result;
    }

    public static String toHex(boolean[] bits)
    {
        int numMissingBits = (4 - (bits.length % 4)) % 4;
        boolean[] holdNibbles = new boolean[bits.length + numMissingBits];
        for(int step = 0; step < numMissingBits; step++)
        {
            holdNibbles[step] = false;
        }
        for(int step = numMissingBits; step < holdNibbles.length; step++)
        {
            holdNibbles[step] = bits[step - numMissingBits];
        }

        String hex = "";
        int nibble;
        for(int step = 0; step < holdNibbles.length; step += 4)
        {
            nibble = 0;

            if (holdNibbles[step]) nibble += 8;
            if (holdNibbles[step+1]) nibble += 4;
            if (holdNibbles[step+2]) nibble += 2;
            if (holdNibbles[step+3]) nibble += 1;

            hex += Integer.toHexString(nibble).toUpperCase();
        }

        return hex;
    }
}