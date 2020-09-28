package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import model.Book;
//simulated book server
//a map of books: the key is the type id and the value: list of books

public class BookServer  implements Iterable <Book>, Serializable {
	private static final long serialVersionUID = 1L;

	private Map <Integer, List<Book>> books;
	
	private List<Book> selected;
	
	public BookServer () {
		//contains the books on the selective type
		selected = new ArrayList<Book>();
		books = new TreeMap <Integer, List<Book>>();
		
		//adding books in fiction 

		List<Book> list = new ArrayList<Book>();
		list.add(new Book(0,"Title 0.1", "Author 0.1", "Excerpt content 0.1............. "));
		list.add(new Book(1,"Title 0.2", "Author 0.2", "Excerpt content 0.2............. "));
		list.add(new Book(2,"Title 0.3", "Author 0.3", "Excerpt content 0.3............. "));
		//Associates the specified value with the specified key(genre-fiction) in this map
		books.put(0, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(3,"Title 1.1", "Author 1.1", "Excerpt content 1.1............. "));
		list.add(new Book(4,"Title 1.2", "Author 1.2", "Excerpt content 1.2............. "));
		list.add(new Book(5,"Title 1.3", "Author 1.3", "Excerpt content 1.3............. "));
		books.put(1, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(6,"Title 2.1", "Author 2.1", "Excerpt content 2.1............. "));
		list.add(new Book(7,"Title 2.2", "Author 2.2", "Excerpt content 2.2............. "));
		list.add(new Book(8,"Title 2.3", "Author 2.3", "Excerpt content 2.3............. "));
		books.put(2, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(9,"Title 3.1", "Author 3.1", "Excerpt content 3.1............. "));
		list.add(new Book(10,"Title 3.2", "Author 3.2", "Excerpt content 3.2............. "));
		list.add(new Book(11,"Title 3.3", "Author 3.3", "Excerpt content 3.3............. "));
		books.put(3, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(12,"Title 4.1", "Author 4.1", "Excerpt content 4.1............. "));
		list.add(new Book(13,"Title 4.2", "Author 4.2", "Excerpt content 4.2............. "));
		books.put(4, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(14,"Title 5.1", "Author 5.1", "Excerpt content 5.1............. "));
		list.add(new Book(15,"Title 5.2", "Author 5.2", "Excerpt content 5.2............. "));
		books.put(5, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(16,"Title 6.1", "Author 6.1", "Excerpt content 6.1............. "));
		list.add(new Book(17,"Title 6.2", "Author 6.2", "Excerpt content 6.2............. "));
		books.put(6, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(18,"Title 7.1", "Author 7.1", "Excerpt content 7.1............. "));
		list.add(new Book(19,"Title 7.2", "Author 7.2", "Excerpt content 7.2............. "));
		books.put(7, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(20,"Title 8.1", "Author 8.1", "Excerpt content 8.1............. "));
		list.add(new Book(21,"Title 8.2", "Author 8.2", "Excerpt content 8.2............. "));
		books.put(8, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(22,"Title 9.1", "Author 9.1", "Excerpt content 9.1............. "));
		list.add(new Book(23,"Title 9.2", "Author 9.2", "Excerpt content 9.2............. "));
		books.put(9, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(24,"Title 10.1", "Author 10.1", "Excerpt content 10.1............. "));
		list.add(new Book(25,"Title 10.2", "Author 10.2", "Excerpt content 10.2............. "));
		books.put(10, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(27,"Title 11.1", "Author 11.1", "Excerpt content 11.1............. "));
		list.add(new Book(28,"Title 1.2", "Author 11.2", "Excerpt content 11.2............. "));
		list.add(new Book(29,"Title 1.3", "Author 11.3", "Excerpt content 11.3............. "));
		books.put(11, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(30,"Title 12.1", "Author 12.1", "Excerpt content 12.1............. "));
		list.add(new Book(31,"Title 12.2", "Author 12.2", "Excerpt content 12.2............. "));
		list.add(new Book(32,"Title 12.3", "Author 12.3", "Excerpt content 12.3............. "));
		books.put(12, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(33,"Title 13.1", "Author 13.1", "Excerpt content 13.1............. "));
		list.add(new Book(34,"Title 13.2", "Author 13.2", "Excerpt content 13.2............. "));
		list.add(new Book(35,"Title 13.3", "Author 13.3", "Excerpt content 13.3............. "));
		books.put(13, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(36,"Title 14.1", "Author 14.1", "Excerpt content 14.1............. "));
		list.add(new Book(37,"Title 14.2", "Author 14.2", "Excerpt content 14.2............. "));
		books.put(14, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(38,"Title 15.1", "Author 15.1", "Excerpt content 15.1............. "));
		list.add(new Book(39,"Title 15.2", "Author 15.2", "Excerpt content 15.2............. "));
		books.put(15, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(40,"Title 16.1", "Author 16.1", "Excerpt content 16.1............. "));
		list.add(new Book(41,"Title 16.2", "Author 16.2", "Excerpt content 16.2............. "));
		books.put(16, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(42,"Title 17.1", "Author 17.1", "Excerpt content 17.1............. "));
		list.add(new Book(43,"Title 17.2", "Author 17.2", "Excerpt content 17.2............. "));
		books.put(17, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(44,"Title 18.1", "Author 18.1", "Excerpt content 18.1............. "));
		list.add(new Book(45,"Title 18.2", "Author 18.2", "Excerpt content 18.2............. "));
		books.put(18, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(46,"Title 19.1", "Author 19.1", "Excerpt content 19.1............. "));
		list.add(new Book(47,"Title 19.2", "Author 19.2", "Excerpt content 19.2............. "));
		books.put(19, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(48,"Title 20.1", "Author 20.1", "Excerpt content 20.1............. "));
		list.add(new Book(49,"Title 20.2", "Author 20.2", "Excerpt content 20.2............. "));
		books.put(20, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(50,"Title 21.1", "Author 21.1", "Excerpt content 21.1............. "));
		list.add(new Book(51,"Title 21.2", "Author 21.2", "Excerpt content 21.2............. "));
		books.put(21, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(52,"Title 22.1", "Author 22.1", "Excerpt content 22.1............. "));
		list.add(new Book(53,"Title 22.2", "Author 22.2", "Excerpt content 22.2............. "));
		books.put(22, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(54,"Title 23.1", "Author 23.1", "Excerpt content 23.1............. "));
		list.add(new Book(55,"Title 23.2", "Author 23.2", "Excerpt content 23.2............. "));
		books.put(23, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(56,"Title 24.1", "Author 24.1", "Excerpt content 24.1............. "));
		list.add(new Book(57,"Title 24.2", "Author 24.2", "Excerpt content 24.2............. "));
		books.put(24, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(58,"Title 25.1", "Author 25.1", "Excerpt content 25.1............. "));
		list.add(new Book(59,"Title 25.2", "Author 25.2", "Excerpt content 25.2............. "));
		books.put(25, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(60,"Title 26.1", "Author 26.1", "Excerpt content 26.1............. "));
		list.add(new Book(61,"Title 26.2", "Author 26.2", "Excerpt content 26.2............. "));
		books.put(26, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(62,"Title 27.1", "Author 27.1", "Excerpt content 27.1............. "));
		list.add(new Book(63,"Title 27.2", "Author 27.2", "Excerpt content 27.2............. "));
		books.put(27, list);
		
		
		////////adding books in nonfiction 
		
		list = new ArrayList<Book>();
		list.add(new Book(100, "Title 50.1", "Author 50.1", "Excerpt content 50.1............. "));
		list.add(new Book(101, "Title 50.2", "Author 50.2", "Excerpt content 50.2............. "));
		list.add(new Book(102,"Title 50.3", "Author 50.3", "Excerpt content 50.3............. "));
		list.add(new Book(103,"Title 50.4", "Author 50.4", "Excerpt content 50.4............. "));
		list.add(new Book(104,"Title 50.5", "Author 50.5", "Excerpt content 50.5............. "));
		//Associates the specified value with the specified key in this map
		books.put(50, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(105,"Title 51.1", "Author 51.1", "Excerpt content 51.1............. "));
		list.add(new Book(106,"Title 51.2", "Author 51.2", "Excerpt content 51.2............. "));
		list.add(new Book(106,"Title 51.3", "Author 51.3", "Excerpt content 51.3............. "));
		list.add(new Book(107,"Title 51.4", "Author 51.4", "Excerpt content 51.4............. "));
		list.add(new Book(108,"Title 51.5", "Author 51.5", "Excerpt content 51.5............. "));
		books.put(51, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(109,"Title 52.1", "Author 52.1", "Excerpt content 52.1............. "));
		list.add(new Book(110,"Title 52.2", "Author 52.2", "Excerpt content 52.2............. "));
		list.add(new Book(111,"Title 52.3", "Author 52.3", "Excerpt content 52.3............. "));
		books.put(52, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(112,"Title 53.1", "Author 53.1", "Excerpt content 53.1............. "));
		list.add(new Book(113,"Title 53.2", "Author 53.2", "Excerpt content 53.2............. "));
		list.add(new Book(114,"Title 53.3", "Author 53.3", "Excerpt content 53.3............. "));
		books.put(53, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(115,"Title 54.2", "Author 54.2", "Excerpt content 54.2............. "));
		list.add(new Book(116,"Title 54.3", "Author 54.3", "Excerpt content 54.3............. "));
		books.put(54, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(117,"Title 55.1", "Author 55.1", "Excerpt content 55.1............. "));
		list.add(new Book(118,"Title 55.2", "Author 55.2", "Excerpt content 55.2............. "));
		list.add(new Book(119,"Title 55.3", "Author 55.3", "Excerpt content 55.3............. "));
		books.put(55, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(123,"Title 56.1", "Author 56.1", "Excerpt content 56.1............. "));
		list.add(new Book(121,"Title 56.2", "Author 56.2", "Excerpt content 56.2............. "));
		list.add(new Book(122,"Title 56.3", "Author 56.3", "Excerpt content 56.3............. "));
		books.put(56, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(123,"Title 57.2", "Author 57.2", "Excerpt content 57.2............. "));
		list.add(new Book(124,"Title 57.3", "Author 57.3", "Excerpt content 57.3............. "));
		books.put(57, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(125,"Title 58.2", "Author 58.2", "Excerpt content 58.2............. "));
		list.add(new Book(126,"Title 58.3", "Author 58.3", "Excerpt content 58.3............. "));
		books.put(58, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(127,"Title 59.2", "Author 59.2", "Excerpt content 59.2............. "));
		list.add(new Book(128,"Title 59.3", "Author 59.3", "Excerpt content 59.3............. "));
		books.put(59, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(129,"Title 60.2", "Author 60.2", "Excerpt content 60.2............. "));
		list.add(new Book(130,"Title 60.3", "Author 60.3", "Excerpt content 60.3............. "));
		books.put(60, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(131,"Title 61.2", "Author 61.2", "Excerpt content 61.2............. "));
		list.add(new Book(132,"Title 61.3", "Author 61.3", "Excerpt content 61.3............. "));
		books.put(61, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(133,"Title 62.2", "Author 62.2", "Excerpt content 62.2............. "));
		list.add(new Book(134,"Title 62.3", "Author 62.3", "Excerpt content 62.3............. "));
		books.put(62, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(135,"Title 63.2", "Author 63.2", "Excerpt content 63.2............. "));
		list.add(new Book(136,"Title 63.3", "Author 63.3", "Excerpt content 63.3............. "));
		books.put(63, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(137,"Title 64.2", "Author 64.2", "Excerpt content 64.2............. "));
		list.add(new Book(138,"Title 64.3", "Author 64.3", "Excerpt content 64.3............. "));
		books.put(64, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(139,"Title 65.2", "Author 65.2", "Excerpt content 65.2............. "));
		list.add(new Book(140,"Title 65.3", "Author 65.3", "Excerpt content 65.3............. "));
		books.put(65, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(141,"Title 66.2", "Author 66.2", "Excerpt content 66.2............. "));
		list.add(new Book(142,"Title 66.3", "Author 66.3", "Excerpt content 66.3............. "));
		books.put(66, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(143,"Title 67.2", "Author 67.2", "Excerpt content 67.2............. "));
		list.add(new Book(144,"Title 67.3", "Author 67.3", "Excerpt content 67.3............. "));
		books.put(67, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(145,"Title 68.2", "Author 68.2", "Excerpt content 68.2............. "));
		list.add(new Book(146,"Title 68.3", "Author 68.3", "Excerpt content 68.3............. "));
		books.put(68, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(147,"Title 69.1", "Author 69.1", "Excerpt content 69.1............. "));
		list.add(new Book(148,"Title 69.2", "Author 69.2", "Excerpt content 69.2............. "));
		list.add(new Book(149,"Title 69.3", "Author 69.3", "Excerpt content 69.3............. "));
		books.put(69, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(150,"Title 70.2", "Author 70.2", "Excerpt content 70.2............. "));
		list.add(new Book(151,"Title 70.3", "Author 70.3", "Excerpt content 70.3............. "));
		books.put(70, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(152,"Title 71.2", "Author 71.2", "Excerpt content 71.2............. "));
		list.add(new Book(153,"Title 71.3", "Author 71.3", "Excerpt content 71.3............. "));
		books.put(71, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(154,"Title 72.2", "Author 72.2", "Excerpt content 72.2............. "));
		list.add(new Book(155,"Title 72.3", "Author 72.3", "Excerpt content 72.3............. "));
		books.put(72, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(156,"Title 73.2", "Author 73.2", "Excerpt content 73.2............. "));
		list.add(new Book(157,"Title 73.3", "Author 73.3", "Excerpt content 73.3............. "));
		books.put(73, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(158,"Title 74.2", "Author 74.2", "Excerpt content 74.2............. "));
		list.add(new Book(159,"Title 74.3", "Author 74.3", "Excerpt content 74.3............. "));
		books.put(74, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(160,"Title 75.2", "Author 75.2", "Excerpt content 75.2............. "));
		list.add(new Book(161,"Title 75.3", "Author 75.3", "Excerpt content 75.3............. "));
		books.put(75, list);
		
		list = new ArrayList<Book>();
		list.add(new Book(162,"Title 76.2", "Author 76.2", "Excerpt content 76.2............. "));
		list.add(new Book(163,"Title 76.3", "Author 76.3", "Excerpt content 76.3............. "));
		books.put(76, list);
	}
	
	//for each Int id the set will get all the books on the map and adding them to the list of selected books
	public void setSelectedTypes(Set<Integer> types) {
		
		selected.clear();
		
		for(Integer id: types) {
			if(books.containsKey(id)) {
				selected.addAll(books.get(id));
			}
		}
	}
	
	public int getBookCount() {
		return selected.size();
	}

	@Override
	public Iterator<Book> iterator() {
		return new BookIterator(selected);
	}
}

class BookIterator implements Iterator{
	
	private Iterator<Book> iterator;
	
	public BookIterator(List<Book> books) {
		iterator = books.iterator();
	}

	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public Object next() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
		return iterator.next();
	}
	
	@Override
	public void remove() {
		iterator.remove();
	}	
}
