package spr.board.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={"id","invdate","name", "amount","tax","total","notes"})
public class JQueryJson {
	private int id;
	private Date invdate;
	private String name;
	private int amount;
	private int tax;
	private int total;
	private String notes;
	
	public JQueryJson(int id, Date invdate, String name, int amount, int tax,
			int total, String notes) {
		super();
		this.id = id;
		this.invdate = invdate;
		this.name = name;
		this.amount = amount;
		this.tax = tax;
		this.total = total;
		this.notes = notes;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getInvdate() {
		return invdate;
	}
	public void setInvdate(Date invdate) {
		this.invdate = invdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTax() {
		return tax;
	}
	public void setTax(int tax) {
		this.tax = tax;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
