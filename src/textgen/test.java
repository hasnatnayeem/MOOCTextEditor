package textgen;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyLinkedList<String> strList = new MyLinkedList<>();
		strList.add("Shakil");
		strList.add("Shakil");
		strList.add("Shakil");
		strList.add("Shakil");
		strList.add("Shakil");
		strList.add("Shakil");
		strList.add("Shakil");
		strList.add(4, "Nayeem");


		LLNode<String> s = strList.head.next;
		for (int i = 0; i < strList.size; i++)
		{

			System.out.println(s.data);
			s = s.next;
		}
		
		System.out.println("Removed " + strList.remove(4));

		s = strList.head.next;
		for (int i = 0; i < strList.size; i++)
		{

			System.out.println(s.data);
			s = s.next;
		}

	}

}
