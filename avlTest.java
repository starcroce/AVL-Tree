import java.util.Random;

public class avlTest {
	public static void main(String[] args) {
		int max = 8192;
		int num = 50;
		double time_search_total = 0.0;
		double time_insert_total = 0.0;
		double time_delete_total = 0.0;
		double space_total = 0.0;
		
		for(int i=0; i<num; i++){
			avlTree myAVL = new avlTree();
			Random randValue = new Random();
			int key = randValue.nextInt(max);
			for(int j=0; j<key; j++){
				myAVL.root = myAVL.avlInsert(myAVL.root, j);
			}
			for(int j=key+1; j<max; j++){
				myAVL.root = myAVL.avlInsert(myAVL.root, j);
			}
			myAVL.time_insert = 0;
			myAVL.avlInsert(myAVL.root, key);
			time_insert_total += myAVL.time_insert;
			myAVL.avlSearch(myAVL.root, key);
			time_search_total += myAVL.time_search;
			myAVL.avlDelete(myAVL.root, key);
			time_delete_total += myAVL.time_delete;
			space_total += myAVL.space;
		}
		
		System.out.println(time_insert_total/num);
		System.out.println(time_search_total/num);
		System.out.println(time_delete_total/num);
		System.out.println(space_total/num);
	}
}
