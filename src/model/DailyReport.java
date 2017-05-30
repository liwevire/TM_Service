package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;


@NamedNativeQueries({
	@NamedNativeQuery(
	name = "calculateDailyReport",
	query = "CALL calculateDailyReport(:date)",
	resultClass = DailyReport.class
	)
})
@Entity
@Table(name="daily")
public class DailyReport implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="date", unique=true, nullable =false)
	private Date date;
	@Column(name="principal")
	private double principal;
	@Column(name="roi")
	private double roi;
	@Column(name="rop")
	private double rop;
	@Column(name="firstMonthInterest")
	private double firstMonthInterest;
	@Column(name="appraisalCharges")
	private double appraisalCharges;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getRoi() {
		return roi;
	}
	public void setRoi(double roi) {
		this.roi = roi;
	}
	public double getRop() {
		return rop;
	}
	public void setRop(double rop) {
		this.rop = rop;
	}
	public double getFirstMonthInterest() {
		return firstMonthInterest;
	}
	public void setFirstMonthInterest(double firstMonthInterest) {
		this.firstMonthInterest = firstMonthInterest;
	}
	public double getAppraisalCharges() {
		return appraisalCharges;
	}
	public void setAppraisalCharges(double appraisalCharges) {
		this.appraisalCharges = appraisalCharges;
	}
}