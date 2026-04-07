package computer;

public class Register
{
    private int size; // number of bits held by the register
    private String name; // name for the register, for dev
    private MemoryCell[] data; // array of memory cells

    public Register(int length, String name)
    {
        this.size = length;
        this.name = name;
        data = new MemoryCell[length];
        for(int step = 0; step < length; step++)
        {
            data[step] = new MemoryCell(false);
        }
    }
    
    public Register(int length)
    {
        this(length, "Undefined Register");
    }

    public boolean[] getRegister(int start, int length)
    {
        int actualLength = Math.min(length, size-start);
        boolean[] registerData = new boolean[actualLength];
        for(int step = 0; step < actualLength; step++)
        {
            registerData[step] = this.data[start++].getValue();
        }
        return registerData;
    }

    public boolean[] getRegister()
    {
        return getRegister(0, this.size);
    }

    public void loadRegister(boolean[] data)
    {
        int inputIterator = data.length - 1;
        int registerIterator = size - 1;
        int maxDataCopy = Math.min(size, data.length);
        for(int step = 0; step < maxDataCopy; step++)
        {
            this.data[registerIterator].setValue(data[inputIterator]);
            registerIterator--;
            inputIterator--;
        }
    }

    public void increment()
    {
        carry: for(int step = size - 1; step >= 0; step--)
        {
            data[step].setNot();
            if(data[step].getValue())
            {
                break carry;
            }
        }
    }

    public void decrement()
    {
        carry: for(int step = size - 1; step >= 0; step--)
        {
            data[step].setNot();
            if(!data[step].getValue())
            {
                break carry;
            }
        }
    }

    public String toString()
    {
        String toReturn = "Register " + name + ": ";
        for(MemoryCell cell : data)
        {
            toReturn += cell;
        }
        return toReturn;
    }
}
