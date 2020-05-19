package com.oldBookSell.service;

import java.util.List;
import java.util.Optional;

import com.oldBookSell.dto.BookDTO;
import com.oldBookSell.model.Book;

public interface BookService {

	public void saveBook(BookDTO bookDTOObj);

	public List<Book> findBooks(int min, int max);
	
	public Book updateBookPrice(int bookId, int price);
	
	public Iterable<Object> findAllCatogory();
	
	public List<Book> findBookByNameAuthorAndIsbn(String searchType);
	
	public List<Book> findBookByAuthor(String author);
	
	public List<Book> findBookByPublisher(String publisher);
	
	public List<Book> findBookByCategory(String category);
	
	public Optional<Book> findBookById(int id);
	
	public List<Book> getAllBook();

	public List<Book> getAllBookForUpdate();
	
	public int getQuantity(int bookId);
	
	public void minusQuantity(int bookId, int quantity);
	
	public List<Book> findAllBookForSell();

}
