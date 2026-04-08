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
    private RAM ram;

    public CPU(int numRegs, RAM ram)
    {
        // instruction register 10 bytes long
        instructReg = new Register(80, "Instruction Register");

        // program counter set to look at the beginning of RAM
        programCounter = new Register(8, "Program Counter");
        programCounter.loadRegister(new boolean[]{false});

        // multipurpose registers set at 8 bytes
        registers = new Register[numRegs];
        for(int step = 0; step < numRegs; step++)
        {
            registers[step] = new Register(64, "r" + step);
        }

        this.ram = ram;

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
        instructReg.loadRegister(ram.fetch(programCounter.getRegister(), 10));

        // check the first nibble of the register for main function
        switch(Wire.toHex(instructReg.getRegister(0,4)))
        {
            case "0": clock.cancel(); System.out.println("CPU has been killed"); break;
            case "3": handleExtensiveMove(); for(int step = 0; step < 10; step++){programCounter.increment();}
        }
        System.out.println(registers[0]);
    }

    private void handleExtensiveMove()
    {
        boolean[] valueBite = instructReg.getRegister(16,64);
        switch(Wire.toHex(instructReg.getRegister(4,4)))
        {
            case "0": registers[Wire.bits2Int(instructReg.getRegister(12,4))].loadRegister(valueBite); // put this value in the register
        }
    }
}
