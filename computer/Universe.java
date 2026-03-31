package computer;

public class Universe
{
    public static void main(String[] args)
    {
        System.out.println("Starting Universe");
        RAM ram = new RAM(64);
        //ram.load(new boolean[]{false}, new boolean[]{false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, true, true, true, true, false, false, true, false, false, true, false});
        ram.load(new boolean[]{false}, Universe.hex2MachineCode("3000D792"));
        System.out.println(ram);
        /*ram.load(new boolean[]{false}, new boolean[]{true, false, false, true, true, true, true, false, false, false, false, false, false, false, true, false});
        System.out.println(ram);

        Register r0 = new Register(8, "r0");
        Register r1 = new Register(8, "r1");
        System.out.println(r0);
        System.out.println(r1);
        r0.loadRegister(ram.fetch(new boolean[]{false}, 1));
        r1.loadRegister(ram.fetch(new boolean[]{true}, 1));
        System.out.println(r0);
        System.out.println(r1);
        ram.load(r1.getRegister(), r0.getRegister());
        System.out.println(ram);*/
        CPU cpu = new CPU(4, ram);
        
    }

    private static boolean[] hex2MachineCode(String hex)
    {
        boolean[] bits = new boolean[hex.length() * 4];

        for (int i = 0; i < hex.length(); i++)
        {
            // Convert hex character to integer (0-15)
            int value = Integer.parseInt(String.valueOf(hex.charAt(i)), 16);

            // Extract bits from most significant to least significant
            bits[i * 4]     = (value & 0b1000) != 0;
            bits[i * 4 + 1] = (value & 0b0100) != 0;
            bits[i * 4 + 2] = (value & 0b0010) != 0;
            bits[i * 4 + 3] = (value & 0b0001) != 0;
        }

        return bits;
    }
}