package model;

public class Outstanding {
	double firstMonthInterest;
	double outstandingInterest;
	double currentPrincipal;
	double outstandingPrincipal;
	double totalOustanding;
	long totalDays;
	public Outstanding() {
		super();
	}
	public double getTotalOustanding() {
		return totalOustanding;
	}
	public void setTotalOustanding(double totalOustanding) {
		this.totalOustanding = totalOustanding;
	}
	public double getFirstMonthInterest() {
		return firstMonthInterest;
	}
	public void setFirstMonthInterest(double firstMonthInterest) {
		this.firstMonthInterest = firstMonthInterest;
	}
	public double getOutstandingInterest() {
		return outstandingInterest;
	}
	public void setOutstandingInterest(double outstandingInterest) {
		this.outstandingInterest = outstandingInterest;
	}
	public double getCurrentPrincipal() {
		return currentPrincipal;
	}
	public void setCurrentPrincipal(double currentPrincipal) {
		this.currentPrincipal = currentPrincipal;
	}
	public double getOutstandingPrincipal() {
		return outstandingPrincipal;
	}
	public void setOutstandingPrincipal(double outstandingPrincipal) {
		this.outstandingPrincipal = outstandingPrincipal;
	}
	public long getTotalDays() {
		return totalDays;
	}
	public void setTotalDays(long totalDays) {
		this.totalDays = totalDays;
	}
}
