package library.returnbook;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.Assert.*;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.*;

import library.entities.ICalendar;
import library.entities.ILibrary;
import library.entities.helpers.BookHelper;
import library.entities.helpers.CalendarFileHelper;
import library.entities.helpers.LibraryFileHelper;
import library.entities.helpers.LoanHelper;
import library.entities.helpers.PatronHelper;
import library.returnbook.ReturnBookControl.ControlStateConstants;



@ExtendWith(MockitoExtension.class)
class Bug1SimplificationTest {
    
    

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    private ICalendar calendar;
    private ILibrary library;
    private ReturnBookControl returnBookControl;

    @Mock IReturnBookUI ui;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        LibraryFileHelper libraryHelper = new LibraryFileHelper(new BookHelper(), new PatronHelper(), new LoanHelper());
        CalendarFileHelper calendarHelper = new CalendarFileHelper();

        calendar = calendarHelper.loadCalendar();
        library = libraryHelper.loadLibrary();
        returnBookControl = new ReturnBookControl(library);
        returnBookControl.setUI(ui);
    }

    @Test
    void testBug1() {
        // Arrange
        int barcode = 1;
        double i = 0.0;
        // Act
        returnBookControl.bookScanned(barcode);
        // Assert
        
        assertFalse(i == library.getPatronById(1).getFinesPayable());
        //verify(ui, times(2)).display(anyString());
        //verify(ui).setState(IReturnBookUI.UIStateConstants.INSPECTING);
        //assertTrue(returnBookControl.controlState == ReturnBookControl.ControlStateConstants.INSPECTING);
    }

}
