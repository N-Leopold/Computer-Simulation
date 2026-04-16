package computer;

public class MMU
{
    private RAM ram;
    private Register programStart;
    private Register programLength;

    public MMU(RAM ram)
    {
        this.ram = ram;
        this.programStart = new Register(16, "Program Start");
        this.programLength = new Register(16, "Program Length");
        // for now, manually boot
        this.programStart.loadRegister(new boolean[]{false});
        this.programLength.loadRegister(new boolean[]{true, false, false, false, false, false, false});
    }

    public boolean[] fetch(boolean[] location, int length)
    {
        // if the call was in bounds
        if(Wire.bits2Int(location) + length <= Wire.bits2Int(programLength.getRegister()))
        {
            return ram.fetch(Wire.bits2Int(programStart.getRegister()) + Wire.bits2Int(location), length);
        }
        // TODO: handle invalid memory call
        return new boolean[]{};
    }

    public void load(boolean[] location, boolean[] bits)
    {
        // if the load is in bounds
        if(Wire.bits2Int(location) + bits.length <= Wire.bits2Int(programLength.getRegister()))
        {
            ram.load(Wire.bits2Int(programStart.getRegister()) + Wire.bits2Int(location), bits);
        }
        // TODO: handle invalid memory load
    }
}