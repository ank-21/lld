package test.java.com.walletApp.services;

import main.java.com.walletApp.models.TopUpMode;
import main.java.com.walletApp.services.TransactionService;
import main.java.com.walletApp.services.UserService;
import main.java.com.walletApp.services.WalletService;
import main.java.com.walletApp.utils.exceptions.InvalidAmountTransaction;
import main.java.com.walletApp.utils.exceptions.NotSufficientBalanceException;
import main.java.com.walletApp.utils.exceptions.UserNotFound;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionServiceTest {
    private TransactionService transactionService;
    private UserService userService;
    private WalletService walletService;

    @BeforeEach
    public void setUp() throws Exception {
        transactionService = TransactionService.getInstance();
        userService = UserService.getInstance();
        walletService = new WalletService();


        // Clear users to ensure no side effects
        userService.getUsers().clear();

        // Set up mock users for testing
        userService.registerUser("sender1", "Alice");
        userService.registerUser("receiver1", "Bob");

        walletService.topUpWallet("sender1", new BigDecimal(400).setScale(2, RoundingMode.CEILING), TopUpMode.UPI);
    }

    @Test
    public void testProcessTransaction_Success() throws Exception {
        walletService.sendMoney("sender1", "receiver1", new BigDecimal(200.00).setScale(2, RoundingMode.CEILING));

        // Verify balances
        assertEquals(new BigDecimal(230.01).setScale(2, RoundingMode.CEILING), userService.getUsers().get("sender1").getWallet().getBalance());
        assertEquals(new BigDecimal(230.01).setScale(2, RoundingMode.CEILING), userService.getUsers().get("receiver1").getWallet().getBalance());
    }

    @Test
    public void testProcessTransaction_Failure() throws UserNotFound, InvalidAmountTransaction, NotSufficientBalanceException, InterruptedException {
        // Verify Balances
        NotSufficientBalanceException exception = assertThrows(
                NotSufficientBalanceException.class,
                () -> walletService.sendMoney("sender1", "receiver1", new BigDecimal(800.00))
        );

        assertEquals("Alice does not have sufficient Balance for the transaction", exception.getMessage());
    }

    @AfterEach
    public void tearDown() {
        // Clean up if needed
        userService.getUsers().clear();
    }
}
