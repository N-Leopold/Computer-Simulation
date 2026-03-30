package computer;

public class Universe
{
    public static void main(String[] args)
    {
        System.out.println("Starting Universe");
        RAM ram = new RAM(8);
        System.out.println(ram);
        ram.load(new boolean[]{false}, new boolean[]{true, false, false, true, true, true, true, false, false, false, false, false, false, false, true, false});
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
        System.out.println(ram);
        
    }
}