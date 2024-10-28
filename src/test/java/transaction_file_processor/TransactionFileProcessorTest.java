package transaction_file_processor;

import com.bptn.planmyfinance.date.DateConversion;
import com.bptn.planmyfinance.transactions.Transactions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionFileProcessorTest {
    private static Transactions transactions;

    @BeforeAll
    public static void loadTransactionsFromFile() {
        transactions = new Transactions("transactions-file-processor-test.txt");
    }

    @Test
    public void testTransactionCorrectlyLoaded() {
        assertEquals("Rent", transactions.getTransactions().get(0).getName());
        assertEquals(500.0, transactions.getTransactions().get(0).getAmount());
        assertEquals(DateConversion.convertStringToDateDDMMYYYY("01-11-2024"), transactions.getTransactions().get(0).getDate());
        assertEquals("debit", transactions.getTransactions().get(0).getType());
    }
}
