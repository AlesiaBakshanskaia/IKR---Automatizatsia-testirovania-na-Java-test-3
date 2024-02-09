import com.max.BookingService;
import com.max.CantBookException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(BookingServiceTest.class);
    @Mock
    static BookingService bookingService;

    @BeforeAll
    static void init() {
        bookingService = new BookingService();
        logger.info("Moc object bookingService has been created");
    }

    @Test
    void checkBookingServiceWithMockTrue() throws CantBookException {
        logger.info("Test \"checkBookingServiceWithMockTrue\" start");
        Mockito.doReturn(true).when(bookingService)
                .book(anyString(), any(LocalDateTime.class), any(LocalDateTime.class));
        logger.debug("Mok return true");
        Assertions.assertTrue(bookingService.book("15", LocalDateTime.now(), LocalDateTime.now()));
        logger.info("Test \"checkBookingServiceWithMockTrue\" completed");
    }

    @Test
    void checkBookingServiceWithMockException() throws CantBookException {
        logger.info("Test \"checkBookingServiceWithMockException\" start");
        Mockito.doThrow(CantBookException.class).when(bookingService)
                .book(anyString(), any(LocalDateTime.class), any(LocalDateTime.class));
        logger.debug("Mok return exception");
        Assertions.assertThrows(CantBookException.class,
                () -> bookingService.book("15", LocalDateTime.now(), LocalDateTime.now()));
        logger.info("Test \"checkBookingServiceWithMockException\" completed");
    }

}
