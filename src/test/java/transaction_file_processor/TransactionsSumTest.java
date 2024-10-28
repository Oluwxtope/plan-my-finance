package transaction_file_processor;

import com.bptn.planmyfinance.transactions.Transactions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionsSumTest {
    private static Transactions transactions;

    @BeforeAll
    public static void loadTransactionsFromFile() {
        transactions = new Transactions("transactions-sum-test.txt");
    }

    @Test
    public void testTransactionSum() {
        assertEquals(transactions.sumTransactions(), 6381.0);
    }
}
