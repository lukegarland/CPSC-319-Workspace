import java.util.ArrayList;
import java.util.Random;
public class ArgPermutations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();
		ArrayList<Integer> old = new ArrayList<Integer>();
		ArrayList<Integer> newList = new ArrayList<Integer>();
		Random rand = new Random();
		
		for(int i = 0; i< args.length; i++)
		{
			old.add(Integer.parseInt(args[i]));
		}
		while(old.size() != 0)
		{
			int elementToDelete = rand.nextInt(old.size());
			newList.add(old.get(elementToDelete));
			old.remove(elementToDelete);
		}
		long timeToComplete = System.nanoTime() - startTime;
		
		System.out.println(newList);
		System.out.println(timeToComplete/1000.0);
	}

}
