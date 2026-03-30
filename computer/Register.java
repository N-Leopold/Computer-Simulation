package computer;

public class Register
{
    private int size;
    private String name;
    private MemoryCell[] data;

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

    public boolean[] getRegister(int numCells)
    {
        int length = Math.min(numCells, size);
        int holdSize = size - 1;
        boolean[] registerData = new boolean[length];
        for(int step = length-1; step >= 0; step--)
        {
            registerData[step] = this.data[holdSize].getValue();
            holdSize--;
        }
        return registerData;
    }

    public boolean[] getRegister()
    {
        return getRegister(this.size);
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
