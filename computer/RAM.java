package computer;

public class RAM
{
    private int size; // bytes
    private MemoryCell[] ram;

    public RAM(int size)
    {
        this.size = size;
        ram = new MemoryCell[this.size * 8];
        for(int step = 0; step < this.size * 8; step++)
        {
            ram[step] = new MemoryCell(false);
        }
    }

    /**
     * Fetch is meant for register communication
     * @param location byte address in RAM
     * @param length number of bytes to pull
     * @return
     */
    public boolean[] fetch(int location, int length)
    {
        boolean[] bits = new boolean[length*8];
        for(int step = 0; step < length*8; step++)
        {
           bits[step] = this.ram[location*8 + step].getValue();
        }
        return bits;
    }

    public void load(int location, boolean[] bits)
    {
        for(int step = 0; step < bits.length; step++)
        {
            ram[location*8 + step].setValue(bits[step]);
        }
    }

    public String toString()
    {
        int iterateByte = 0;
        int numBytes1Line = 0;
        String returnable = "~~~ RAM ~~~\n";
        String binary = "";
        String hex = "";
        boolean[] holdByte = new boolean[8];
        boolean done = true;
        for(MemoryCell cell : ram)
        {
            done = false;
            binary += cell.toString();
            holdByte[iterateByte] = cell.getValue();
            iterateByte++;

            if(iterateByte == 8)
            {
                binary += " ";
                hex += Wire.toHex(holdByte) + " ";
                numBytes1Line++;
                iterateByte = 0;
            }

            if(numBytes1Line == 4)
            {
                returnable += hex + " ->  " + binary + "\n";
                hex = "";
                binary = "";
                numBytes1Line = 0;
                done = true;
            }
        }
        if(!done)
        {
            returnable += hex + " ->  " + binary + "\n";
        }
        returnable += "~~~ End RAM ~~~";
        return returnable;
    }
    
}
