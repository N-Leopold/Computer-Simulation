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
        for(int step = 0; step < 8; step++)
        {
            this.data[step].setValue(data[step]);
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
}
