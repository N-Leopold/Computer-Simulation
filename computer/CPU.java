package computer;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class CPU
{
    // native to the CPU
    private Register[] registers;
    private Register instructReg;
    private Register programCounter;

    // out-of-universe neccesities
    private Timer clock;

    // connection to RAM
    private MMU mmu;

    public CPU(int numRegs, MMU mmu)
    {
        // instruction register 10 bytes long
        instructReg = new Register(80, "Instruction Register");
        
        // program counter tracks where in a program we are
        programCounter = new Register(16, "Program Counter");
        // TODO: this should not be manual
        programCounter.loadRegister(new boolean[]{false});

        // multipurpose registers set at 8 bytes
        registers = new Register[numRegs];
        for(int step = 0; step < numRegs; step++)
        {
            registers[step] = new Register(64, "r" + step);
        }

        this.mmu = mmu;

        // set a clock and run it
        clock = new Timer();
        clock.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                execute();
            }
        }, 0, 5000);
    }

    private void execute()
    {
        
        // fetch
        instructReg.loadRegister(mmu.fetch(programCounter.getRegister(), 10));

        // check the first nibble of the register for main function
        switch(Wire.toHex(instructReg.getRegister(0,4)))
        {
            case "0": clock.cancel(); System.out.println("CPU has been killed"); break;
            case "1": handleMoves();break;
        }
        
        System.out.println(programCounter);
        System.out.println(instructReg);
        System.out.println(registers[0]);
        System.out.println(registers[1]);
    }

    private void handleMoves()
    {
        // boolean[] valueBite = instructReg.getRegister(16,64);

        // 1# xy ## (##)
        int numBytesX = Wire.bits2Int(instructReg.getRegister(8,4));
        int regPosY = Wire.bits2Int(instructReg.getRegister(12,4));

        switch(Wire.toHex(instructReg.getRegister(4,4)))
        {
            // case "0": registers[Wire.bits2Int(instructReg.getRegister(12,4))].loadRegister(valueBite); // put this value in the register
            // MOV reg -> reg
            case "0": registers[Wire.bits2Int(instructReg.getRegister(20,4))].loadRegister(registers[regPosY].getRegister(numBytesX*8)); programCounter.increment(3); break;
            // STO reg -> RAM
            case "1": mmu.load(registers[Wire.bits2Int(instructReg.getRegister(20,4))].getRegister(numBytesX*8),registers[regPosY].getRegister(numBytesX*8)); programCounter.increment(3); break;
            case "4": registers[regPosY].loadRegister(mmu.fetch(instructReg.getRegister(16,16),numBytesX));programCounter.increment(4);break;
        }
    }
}
