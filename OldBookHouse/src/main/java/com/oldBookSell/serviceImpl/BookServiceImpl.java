package com.oldBookSell.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.BookDTO;
import com.oldBookSell.model.Book;
import com.oldBookSell.repository.BookRepository;
import com.oldBookSell.service.BookService;

/**
 * This is BookServiceImpl implements an application that
 * simply calls BookService interface methods
 * @author Kundan,Praveen
 * @version 1.0
 * @since 2020-05-18
 */

@Service
public class BookServiceImpl implements BookService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	/**
	 * This method is used to save book informarion in book table
	 * @param bookDTOObj this is the parameter of saveBook method
	 */
	
	@Override
	public void saveBook(BookDTO bookDTOObj) {
		LOGGER.info("BookService saveBook method is calling....");
		Book bookObj=new Book();
		
		bookObj.setAmount(bookDTOObj.getAmount());
		bookObj.setAuthors(bookDTOObj.getAuthors());
		bookObj.setBookName(bookDTOObj.getBookName());
		bookObj.setBookStatus(bookDTOObj.getBookStatus());
		bookObj.setCategories(bookDTOObj.getCategories());
		bookObj.setCurrencyCode(bookDTOObj.getCurrencyCode());
		bookObj.setDescription(bookDTOObj.getDescription());
		bookObj.setIsbnNo1(bookDTOObj.getIsbnNo1());
		bookObj.setIsbnNo2(bookDTOObj.getIsbnNo2());
		bookObj.setIsbnType10(bookDTOObj.getIsbnType10());
		bookObj.setIsbnType13(bookDTOObj.getIsbnType13());
		bookObj.setPublishedDate(bookDTOObj.getPublishedDate());
		bookObj.setPublisher(bookDTOObj.getPublisher());
		bookObj.setQuantity(bookDTOObj.getQuantity());
		bookObj.setSmallThumbnail(bookDTOObj.getSmallThumbnail());
		bookObj.setThumbnail(bookDTOObj.getThumbnail());
		
		//This code for useful for book Tabel find the the unique book and update quantity
		Book uniqueBook=bookRepository.findByBookNameOrAuthor(bookDTOObj.getBookName(),bookDTOObj.getAuthors());
		try {	
			if(uniqueBook == null) {
				bookRepository.save(bookObj);
			}else {
				bookObj.setQuantity(uniqueBook.getQuantity()+1);
				bookObj.setAmount(uniqueBook.getAmount());
				bookObj.setBookId(uniqueBook.getBookId());
				bookRepository.save(bookObj);
				LOGGER.info("Book information save in book table....");
			}
		}catch(Exception exception) {
			exception.printStackTrace();
		}
	}
	
	/**
	 * This method is used to get the the list of book after scroll
	 * @param min this is the first parameter of findBooks method
	 * @param max this is the second parameter of findBooks method
	 * @return List<Book> ths returns list of book
	 */
	
	@Override
	public List<Book> findBooks(int min, int max) {
		LOGGER.info("BookService findBooks method is calling....");
		LOGGER.info("In BookService findBooks Min="+min);
		LOGGER.info("In BookService findBooks Max="+max);
		return bookRepository.findBooks(min, max);
	}
	
	/**
	 * This method is used to upadte the price of book
	 * @param arr this is the parameter of updateBookPrice method
	 * @return Book this returns book information
	 */
	
	@Override
	public Book updateBookPrice(int bookId, int price) {
		LOGGER.info("BookService updateBookPrice method is calling....");
		Optional<Book> bookObj=bookRepository.findById(bookId);
		LOGGER.info("In BookService findBooks method amount is "+bookObj.get().getAmount());
		bookObj.get().setAmount(price);
		return bookRepository.save(bookObj.get());
	}
	
	/**
	 * This method is used to get book by author name
	 * @param author this is the parameter of findBookByAutho methodr
	 * @return List<Book> this returns list of book
	 */
	
	@Override
	public List<Book> findBookByAuthor(String author){
		LOGGER.info("BookService findBookByAuthor method is calling....");
		LOGGER.info("In BookService findBookByAuthor Author Name="+author);
		return bookRepository.findBookByAuthor(author);
	}
	
	/**
	 * This method is used to search book by title or author name or publisher or isbn number
	 * @param bookName this is the parameter of findBookByNameAuthorAndIsbn method
	 * @return List<Book> this returns list of books
	 */
	
	@Override
	public List<Book> findBookByNameAuthorAndIsbn(String bookName) {
		LOGGER.info("BookRequestService findBooksByNameAuthorAndIsbn method is calling....");
		return bookRepository.findBookByNameAuthorAndIsbn(bookName);
	}
	
	/**
	 * This method is used to get book by category
	 * @param category this is the parameter of findBookByCategory method
	 * @return List<Book> this returns list of book
	 */
	
	@Override
	public List<Book> findBookByCategory(String category){
		LOGGER.info("BookRequestService findBookByCategory method is calling....");
		return bookRepository.findBookByCategory(category);
	}
	
	/**
	 * This method is used to get book by publisher name
	 * @param publisher this is the paramter of findBookByPublisher method
	 * @return List<Book> this returns list of book
	 */
	
	@Override
	public List<Book> findBookByPublisher(String publisher){
		LOGGER.info("BookService findBookByPublisher method is calling....");
		return bookRepository.findBookByPublisher(publisher);
	}
	
	/**
	 * This method is used to find all distinct available  book catogory
	 * @return Iterable<Object> this returns list of book by catogory
	 */
	
	@Override
	public Iterable<Object> findAllCatogory(){
		LOGGER.info("BookService findAllCatogory method is calling....");
		return bookRepository.findAllCatogory();
	}
	
	/**
	 * This method is used to get a particular Book details
	 * @param bookId this is the parameter of findBookById method
	 * @return Optional<Book> this returns a particular book information
	 */
	
	@Override
	public Optional<Book> findBookById(int bookId) {
		LOGGER.info("BookService findBookById method is calling.....");
		return bookRepository.findById(bookId);
	}
	
	/**
	 * This method is used to find all book
	 * @return List<Book> this returns list of books
	 */
	
	@Override
	public List<Book> getAllBook() {
		LOGGER.info("BookService getAllBook method is calling.....");
		return bookRepository.getAllBook();
	}
	
	/**
	 * This method is used to fin all book for updated
	 * @return List<Book> this returns list of books
	 */
	
	@Override
	public List<Book> getAllBookForUpdate() {
		LOGGER.info("BookService getAllBookForUpdate method is calling.....");
		return bookRepository.getAllBookForUpdate();
	}
	
	/**
	 * This method is used to get total number of a particular book
	 * @param bookId this is the parameter of getQuantity method
	 * @return int this returns number of a particular book
	 */
	
	@Override
	public int getQuantity(int bookId) {
		LOGGER.info("BookService getQuantity method is calling.....");
		return bookRepository.getQuantity(bookId);
	}
	
	/**
	 * This method is used to reduce number of a particular book from cart
	 * @param bookId this is the first parameter of minusQuantity method
	 * @param quantity this is the second parameter
	 */
	
	@Override
	public void minusQuantity(int bookId, int quantity) {
		LOGGER.info("BookService minusQuantity method is calling.....");
		Optional<Book> bookRequest =bookRepository.findByBookId(bookId);
		Book bookObj=new Book();
		bookObj=bookRequest.get();
		bookObj.setQuantity(bookObj.getQuantity()-quantity);
		LOGGER.info(" In BookService getAllBookForUpdate method book quantity "+bookObj.getQuantity());
		bookRepository.save(bookObj);
	}
	
	/**
	 * This method is used to find all book for sell
	 * @return List<Book> this returns list of books
	 */
	
	@Override
	public List<Book> findAllBookForSell() {
		LOGGER.info("BookService findAllBookForSell method is calling.....");
		return bookRepository.findAllBookForSell();
	}
}
