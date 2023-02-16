package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.Models.Author;
import com.example.Student_Library_Management_System.Models.Book;
import com.example.Student_Library_Management_System.Models.Card;
import com.example.Student_Library_Management_System.Models.Transactions;
import com.example.Student_Library_Management_System.Repositories.AuthorRepository;
import com.example.Student_Library_Management_System.Repositories.BookRepository;
import com.example.Student_Library_Management_System.Repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    CardRepository cardRepository;

    public String addBook(BookRequestDto bookRequestDto){

        //I want to get the AuthorEntity ???
        int authorId  = bookRequestDto.getAuthorId();

        //Now I will be fetching the authorEntity

        Author author = authorRepository.findById(authorId).get();


        //Convertor
        //We have created this Entity so that we can save it into the DB.
        Book book = new Book();

        //Basic attributes are being from Dto to the Entity Layer
        book.setGenre(bookRequestDto.getGenre());
        book.setIssued(false);
        book.setName(bookRequestDto.getName());
        book.setPages(bookRequestDto.getPages());


        //Setting the foreign key attr in the child class :
        book.setAuthor(author);


        //We need to upate the listOfBooks written in the parent cla

        List<Book> currentBooksWritten = author.getBooksWritten();
        currentBooksWritten.add(book);


        //Now the book is to be saved, but also author is alsoooo to be saved.

        //Why do we need to again save (updating) the author ?? bcz
        //bcz the author Entity has been updated....we need to resave/update it.

        authorRepository.save(author); //Date was modified

        //.save function works both as save function and as update function


        //bookRepo.save is not required : bcz it will be autocalled by cascading
        //effect

        return "Book Added successfully";

    }
    public boolean isValidBook(int bookId){
        // Check if the book with the given id exists in the database
       Book book = bookRepository.findById(bookId).get();
        if(book == null){
            return false;
        }
        return true;
    }
    /*public void issueBook(int bookId, int cardId){
        // Get the book and card entities from the database
        Book book = bookRepository.findById(bookId).get();
        Card card = cardRepository.findById(cardId).get();

        // Update the book's borrowerId to the card's id and set the issue date to today's date
        Transactions transactions=
        book.setBorrowerId(cardId);
        book.setIssueDate(LocalDate.now());

        // Add the book to the card's list of borrowed books
        card.getBorrowedBooks().add(book);

        // Update the book and card entities in the database
        bookRepository.save(book);
        cardRepository.save(card);
    }*/

}
