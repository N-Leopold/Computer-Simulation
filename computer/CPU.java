package computer;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class CPU
{
    private Register[] registers;
    private Register instructReg;
    private Register programCounter;
    private Timer clock;
    int cycle;
    private RAM ram;

    public CPU(int numRegs, RAM ram)
    {
        instructReg = new Register(80, "Instruction Register");
        programCounter = new Register(8, "Program Counter");
        programCounter.loadRegister(new boolean[]{false});

        // multipurpose registers
        registers = new Register[numRegs];
        for(int step = 0; step < numRegs; step++)
        {
            registers[step] = new Register(64, "r" + step);
        }

        this.ram = ram;

        cycle = 0;
        clock = new Timer();
        clock.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                switch(cycle)
                {
                    case 0: fetch(); break;
                    case 1: decode(); break;
                    case 2: execute(); break;
                    case 3: writeBack(); break;
                }
                cycle++;
                cycle = cycle%4;
            }
        }, 0, 5000);
    }

    private void fetch()
    {
        System.out.println("Fetching");
        instructReg.loadRegister(ram.fetch(programCounter.getRegister(), 10));
        System.out.println(instructReg);
    }

    private void decode()
    {
        ByteBlock bite = new ByteBlock(Arrays.copyOfRange(instructReg.getRegister(), 0, 8));
        switch(bite.toNibble(true))
        {
            case "0": clock.cancel(); System.out.println("CPU has been killed"); break;
            case "3": handleExtensiveMove(bite); for(int step = 0; step < 10; step++){programCounter.increment();}
        }
        System.out.println(registers[0]);
    }

    private void execute()
    {

    }

    private void writeBack()
    {

    }

    private void handleExtensiveMove(ByteBlock bite)
    {
        ByteBlock regBite = new ByteBlock(Arrays.copyOfRange(instructReg.getRegister(), 8, 16));
        boolean[] valueBite = Arrays.copyOfRange(instructReg.getRegister(), 16, 80);
        switch(bite.toNibble(false))
        {
            case "0": registers[ram.bits2Int(regBite.fetchNibble(false))].loadRegister(valueBite); // put this value in the register
        }
    }
}
