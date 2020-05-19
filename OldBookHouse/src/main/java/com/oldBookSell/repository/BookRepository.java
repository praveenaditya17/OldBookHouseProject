package com.oldBookSell.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oldBookSell.model.Book;

@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, Integer>{
	
	@Query(value="select * from book where amount!=0 and book_name=?1 or authors=?2",nativeQuery = true)
	Book findByBookNameOrAuthor(String bookName, String authors);
	
	@Query(value = "select * from book where book_status='sell' and amount!=0 and quantity!=0 limit ?1,?2",nativeQuery=true)
	List<Book> findBooks(int min, int max);
	
	@Query(value="select * from book where amount<=0",nativeQuery = true)
	List<Book> getAllBook();
	
	@Query(value="select * from book where amount>0",nativeQuery = true)
	List<Book> getAllBookForUpdate();
	
	@Query(value="select * from book  where amount!=0 and book_name like %?1% or authors like %?1% or isbn_no1 like %?1% or isbn_no2 like %?1%",nativeQuery = true)
	List<Book> findBookByNameAuthorAndIsbn(String searchType);
	
	@Query(value="select * from book where amount!=0 and categories like %?1% or book_name like %?1%",nativeQuery=true)
	List<Book> findBookByCategory(String category);
	
	@Query(value="select quantity from book where book_id=?1",nativeQuery = true)
	int getQuantity(int bookId);
	
	@Query(value="select * from book where amount!=0 and authors like %?1%",nativeQuery=true)
	List<Book> findBookByAuthor(String author);
	
	@Query(value="select * from book where amount!=0 and publisher like %?1%",nativeQuery=true)
	List<Book> findBookByPublisher(String publisher);
	
	@Query(value="select distinct categories from book where amount!=0",nativeQuery=true)
	Iterable<Object> findAllCatogory();
	
	Optional<Book> findByBookId(int bookId);
	
	@Query(value="select * from book where amount!=0 and quantity!=0",nativeQuery = true)
	List<Book> findAllBookForSell();
}
