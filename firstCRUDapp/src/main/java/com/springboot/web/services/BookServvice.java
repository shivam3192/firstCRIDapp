package com.springboot.web.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springboot.web.entities.Book;
@Component
public class BookServvice {
	
	//Create a fake server
	
	private static List<Book> list = new ArrayList<>();
	
	static {
		list.add(new Book(1,"java","abc"));
		list.add(new Book(2,"python","def"));
		list.add(new Book(3,"c","xyz"));
		
	}
	
	//get all books
	public List<Book> getAllBooks(){
		return list;
	}
	
	//get single book by id
	public Book getBookById(int id) {
		Book book = null;
		try {		
			book = list.stream().filter(e->e.getId()==id).findFirst().get();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	//adding the book
	public Book addBook(Book b) {
		list.add(b);
		return b;
	}
	//deleting the book
	public void deleteBook(int bId) {
		
		list = list.stream().filter(e->e.getId()!=bId).collect(Collectors.toList());
		
		
	}
	//update the book
	public void updateBook(Book book , int bookId) {
		list = list.stream().map(b->{
			if(b.getId() ==bookId) {
				b.setTitle(book.getTitle());
				b.setAuthor(b.getAuthor());
			}
			return b;
		}).collect(Collectors.toList());
		
	}
	
}