package com.erfanlibrary.api.unit.service;

import com.erfanlibrary.api.model.Book;
import com.erfanlibrary.api.model.User;
import com.erfanlibrary.api.repository.BookRepository;
import com.erfanlibrary.api.repository.UserBookRepository;
import com.erfanlibrary.api.repository.UserRepository;
import com.erfanlibrary.api.service.LibraryManagementService;
import com.erfanlibrary.api.util.message.book.BookExceptionMessage;
import com.erfanlibrary.api.validator.BookValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryManagementServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    BookValidator bookValidator;

    @Mock
    private UserBookRepository userBookRepository;

    @InjectMocks
    private LibraryManagementService libraryManagementService;


//    fail scenario first
//    intent is clear
//    one assert per test
//    F.I.R.S.T applied
    @Test
    public void testBorrowBookThrowExceptionWhenBookNotFound() {
        // Arrange
        int bookId = 1;
        int customerId = 1;
        when(userRepository.findById(customerId)).thenReturn(Optional.of(new User()));

        // Mock the behavior of the book repository to return an empty Optional
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act & Assert
        try {
            libraryManagementService.borrowBook(bookId, customerId);
        } catch (Exception e) {
            assertEquals(BookExceptionMessage.BOOK_NOT_FOUND, e.getMessage());
        }
    }

//    testing one aspect of the code
//    intent is clear
//    one assert per test
    @Test
    public void testDecreaseAvailableCountOnBorrowBook() {
        User user = new User();

//        creating user with id = 1.
        user.setId(1);
        user.setFullName("John Doe");
        user.setEmail("john.doe@gmail.com");
        user.setPhoneNumber("1234567890");
        user.setPassword("password");
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));

        // creating book availableCount = 5.
        Book book = new Book();
        book.setId(1);
        book.setTitle("Clean Code");
        book.setAvailableCount(5);

        when(bookRepository.findById(1)).thenReturn(Optional.of(book));

        // Act: borrow the book.
        libraryManagementService.borrowBook(1,1);

        // Assert: availableCount should be decreased by one.
        assertEquals(4, book.getAvailableCount());

    }


}
