package computer;

public class RAM
{
    private int size;
    private ByteBlock[] ram;

    public RAM(int size)
    {
        this.size = size;
        ram = new ByteBlock[this.size];
        for(int step = 0; step < this.size; step++)
        {
            ram[step] = new ByteBlock();
        }
    }

    /**
     * Fetch returns the bits in memory starting at the position represented by the location and going for the number of bytes that the length specifies.
     * Does not do any bounds checking at all.
     * @param location
     * @param length
     * @return
     */
    public boolean[] fetch(boolean[] location, int length)
    {
        boolean[] bits = new boolean[8*length];
        boolean[] hold8Bits = new boolean[8];
        int pos = bits2Int(location);
        int iterator = 0;
        for(int step = 0; step < length; step++)
        {
            hold8Bits = this.ram[pos + step].fetchBits();
            for(int bitStep = 0; bitStep < 8; bitStep++)
            {
                bits[iterator] = hold8Bits[bitStep];
                iterator++;
            }
        }
        return bits;
    }

    public void load(boolean[] location, boolean[] bits)
    {
        if((bits.length % 8) != 0)
        {
            System.out.println("Must supply byte-sized data.");
        }
        else
        {
            int bitPos = 0;
            int bytePos = bits2Int(location);
            boolean[] bite = new boolean[8];
            for(int step = 0; step < bits.length; step++)
            {
                bite[bitPos] = bits[step];
                bitPos++;
                if(bitPos == 8)
                {
                    bitPos = 0;
                    ram[bytePos].set(bite);
                    bytePos++;
                }
            }
        }
    }

    public int bits2Int(boolean[] bits)
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

    public String toString()
    {
        int countBlock = 0;
        String returnable = "~~~ RAM ~~~\n";
        String binary = "";
        for(ByteBlock block : ram)
        {
            returnable += block.toHex() + " ";
            binary += block + " ";
            countBlock++;
            if(countBlock % 4 == 0)
            {
                returnable += " ->  " + binary + "\n";
                binary = "";
            }
        }
        returnable += "~~~ End RAM ~~~";
        return returnable;
    }
    
}
