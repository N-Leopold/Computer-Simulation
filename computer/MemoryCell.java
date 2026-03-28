package computer;

public class MemoryCell
{
    private boolean value;

    public MemoryCell()
    {
        this.value = false;
    }

    public MemoryCell(boolean value)
    {
        this.value = value;
    }

    public boolean getValue()
    {
        return value;
    }

    public void setValue(boolean value)
    {
        this.value = value;
    }

    public void setNot()
    {
        this.value = !value;
    }

    public boolean add(MemoryCell cell)
    {
        return value && cell.getValue();
    }

    public boolean xor(MemoryCell cell)
    {
        return value ^ cell.getValue();
    }

    public String toString()
    {
        return value ? "1" : "0";
    }
}