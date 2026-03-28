package computer;

public class Universe
{
    public static void main(String[] args)
    {
        System.out.println("Starting Universe");
        Register test = new Register(4, "tester");
        test.loadRegister(new boolean[]{true, true, false, true});
        System.out.println(test);
        Register tree = new Register(3, "tree");
        tree.loadRegister(test.getRegister(2));
        System.out.println(tree);
        Register inter = new Register(4, "inter");
        for(int step = 0; step < 20; step++)
        {
            System.out.println(inter);
            inter.decrement();
        }
    }
}