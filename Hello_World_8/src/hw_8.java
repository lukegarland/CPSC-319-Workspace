import static java.lang.System.out;

import java.util.ArrayList;


public class hw_8 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		out.println("Hello World");
		ArrayList<Long>memoCache = new ArrayList<Long>(100);
		memoCache.add(1L);
		memoCache.add(2L);
		out.println(memoCache);
		out.print(memoCache.size());

	}

}
