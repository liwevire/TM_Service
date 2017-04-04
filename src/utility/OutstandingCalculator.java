package utility;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;

import model.Loan;
import model.Outstanding;
import model.Transaction;

public class OutstandingCalculator {
	public Outstanding calculateOutstanding(long loanId) {
		Outstanding outstanding = new Outstanding(); 
		Loan loan = new ManageLoan().getLoan(loanId);
		List<Transaction> transactions = loan.getTransactions();
		double principal = 0;
		double debit = 0;
		double creditOnAppCharges= 0;
		double creditOnInterest = 0;
		double totalInterest = 0;
		double initialPrincipal = 0;
		double creditOnPrincipal = 0;
		Date startDate= null;
		Date dateCursor= null;
		Date endDate = new Date((long)739345920);
		for (Transaction transaction : transactions) {
			if(transaction.getCategory().equalsIgnoreCase(Transaction.PRINCIPAL)){
				principal += transaction.getAmount();
				initialPrincipal += transaction.getAmount();
				debit += transaction.getAmount();
				startDate = transaction.getDate();
				dateCursor = transaction.getDate();
			}
			else if(transaction.getCategory().equalsIgnoreCase(Transaction.APPRAISAL_CHARGES))
				creditOnAppCharges += transaction.getAmount();
			else if(transaction.getCategory().equalsIgnoreCase(Transaction.FIRST_MONTH_INTEREST)){
				creditOnInterest += transaction.getAmount();
				outstanding.setFirstMonthInterest(transaction.getAmount());
			}
			else if(transaction.getCategory().equalsIgnoreCase(Transaction.RETURN_ON_INTEREST))
				creditOnInterest += transaction.getAmount();
			else if (transaction.getCategory().equalsIgnoreCase(Transaction.RETURN_ON_PRINCIPAL)) {
				totalInterest += principal*getRoi(principal)*calculateDays(dateCursor, transaction.getDate())/30;
				creditOnPrincipal += transaction.getAmount();
				dateCursor = transaction.getDate();
				principal -= transaction.getAmount();
			}
			if (transaction.getDate().compareTo(endDate) > 0)
				endDate = transaction.getDate();
		}
		if(loan.getLoanStatus().equalsIgnoreCase("closed")){
			totalInterest += principal*getRoi(principal)*calculateDays(startDate, endDate)/30;
			outstanding.setTotalDays(calculateDays(startDate, endDate));
		}
		else if(loan.getLoanStatus().equalsIgnoreCase("open")||loan.getLoanStatus().equalsIgnoreCase("true")){
			totalInterest  = ((double)calculateDays(startDate, new Date())*(double)getRoi(principal)*(double)principal)/30;
			outstanding.setTotalDays(calculateDays(startDate, new Date()));
		}
		outstanding.setOutstandingInterest(totalInterest-creditOnInterest);
		outstanding.setCurrentPrincipal(principal);
		outstanding.setOutstandingPrincipal(initialPrincipal-creditOnPrincipal);
		outstanding.setTotalOustanding(debit+outstanding.getOutstandingInterest()-creditOnPrincipal);
		return outstanding;
	}
	private long calculateDays(Date startDate, Date endDate) {
		long days = (long)TimeUnit.MILLISECONDS.toDays(new DateTime(endDate).getMillis()-new DateTime(startDate).getMillis());
		return days;
	}
	public double getRoi(double principal) {
		if(principal > 0 &&principal < 5000) 
			return (double)0.03;
		else if (principal >= 5000 && principal < 10000)
			return (double)0.025;
		else if (principal >= 10000)
			return (double)0.02;
		else
			return 0;
	}
	public double getAppraiserCharge() {
		return 5;
	}
}