package com.springboot.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.web.entities.Book;
import com.springboot.web.services.BookServvice;

//@Controller
@RestController //for restApi
public class BookController {
	
	//@RequestMapping(value="/books", method=RequestMethod.GET) -> not needed with restController
	//@ResponseBody // means exact data should go //not needed with restController
	@Autowired
	private BookServvice bookServvice;
	//Get all books handler
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		
		List<Book> list =  this.bookServvice.getAllBooks();
		if(list.size() <= 0) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));
		//return book;
		
		//now in order to return in json we need a class book and then its object
		
		//return "This is testing book first";
	}
	//get single book handler
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book =  bookServvice.getBookById(id);
		
		if(book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	//new book handler
	@PostMapping("/books")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		
		Book b = null;
		try {
			b = this.bookServvice.addBook(book);
			System.out.print("book is " + book);
			return ResponseEntity.of(Optional.of(book));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
		
	}
	
	//delete book handler
	@DeleteMapping("/books/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId) {
		
		try {
			this.bookServvice.deleteBook(bookId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();		
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
		
	}
	
	//update book handler
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookId) {
		
		try {
			this.bookServvice.updateBook(book , bookId);
			return ResponseEntity.of(Optional.of(book));
			
		}catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		
		
	}
	
	
}