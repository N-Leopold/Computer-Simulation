package computer;

public class ByteBlock
{
    private MemoryCell[] data;

    public ByteBlock()
    {
        data = new MemoryCell[8];
        for(int step = 0; step < 8; step++)
        {
            this.data[step] = new MemoryCell(false);
        }
    }

    public ByteBlock(boolean[] data)
    {
        this.data = new MemoryCell[8];
        for(int step = 0; step < 8; step++)
        {
            this.data[step] = new MemoryCell(data[step]);
        }
    }

    public ByteBlock(boolean b0, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7)
    {
        this.data[0].setValue(b0);
        this.data[1].setValue(b1);
        this.data[2].setValue(b2);
        this.data[3].setValue(b3);
        this.data[4].setValue(b4);
        this.data[5].setValue(b5);
        this.data[6].setValue(b6);
        this.data[7].setValue(b7);
    }

    public void set(boolean[] data)
    {
        for(int step = 0; step < 8; step++)
        {
            this.data[step].setValue(data[step]);
        }
    }

    public void set(boolean b0, boolean b1, boolean b2, boolean b3, boolean b4, boolean b5, boolean b6, boolean b7)
    {
        this.data[0].setValue(b0);
        this.data[1].setValue(b1);
        this.data[2].setValue(b2);
        this.data[3].setValue(b3);
        this.data[4].setValue(b4);
        this.data[5].setValue(b5);
        this.data[6].setValue(b6);
        this.data[7].setValue(b7);
    }

    public void clear()
    {
        for(MemoryCell cell : this.data)
        {
            cell.setValue(false);
        }
    }

    public boolean[] fetchBits()
    {
        boolean[] bits = new boolean[8];
        for(int step = 0; step < 8; step++)
        {
            bits[step] = data[step].getValue();
        }
        return bits;
    }

    public String toString()
    {
        String returnable = "";
        for(MemoryCell cell : data)
        {
            returnable += cell;
        }
        return returnable;
    }

    public boolean[] fetchNibble(boolean first)
    {
        boolean[] bits = new boolean[4];
        if(first)
        {
            for(int step = 0; step < 4; step++)
            {
                bits[step] = data[step].getValue();
            } 
        }
        else
        {
            for(int step = 4; step < 8; step++)
            {
                bits[step - 4] = data[step].getValue();
            }
        }
        return bits;
    }

    public String toNibble(boolean first)
    {
        String nibble;
        if(first)
        {
            nibble = data[0].toString() + data[1].toString() + data[2].toString() + data[3].toString();
        }
        else
        {
            nibble = data[4].toString() + data[5].toString() + data[6].toString() + data[7].toString();
        }
        switch(nibble)
        {
            case "0000": return "0";
            case "0001": return "1";
            case "0010": return "2";
            case "0011": return "3";
            case "0100": return "4";
            case "0101": return "5";
            case "0110": return "6";
            case "0111": return "7";
            case "1000": return "8";
            case "1001": return "9";
            case "1010": return "A";
            case "1011": return "B";
            case "1100": return "C";
            case "1101": return "D";
            case "1110": return "E";
            case "1111": return "F";
        }
        return "YOU SHOULD NEVER SEE THIS";
    }

    public String toHex()
    {
        return this.toNibble(true) + this.toNibble(false);
    }
}
