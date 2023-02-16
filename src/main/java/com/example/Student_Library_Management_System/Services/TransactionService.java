package com.example.Student_Library_Management_System.Services;


import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    BookService bookService;
    CardService cardService;
    public  String issueBook(IssueBookRequestDto issueBookRequestDto){

        int bookid= issueBookRequestDto.getBookId();
        int cardid=issueBookRequestDto.getCardId();
        if(bookService.isValidBook(bookid) && cardService.isValidCard(cardid)){
            bookService.issueBook(bookid, cardid);
            return "Book issued successfully!";
        }
        else{
            return "Book cannot be issued at this moment.";
        }
    }

}
