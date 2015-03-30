package spr.board.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder = {"creatDate", "id", "password"})
public class ConvertToXmlTest {

		private int id;
		private String password;
		private Date creatDate;
		
		public ConvertToXmlTest() {}
		
		public ConvertToXmlTest(int id, String password, Date creatDate) {
			this.id = id;
			this.password = password;
			this.creatDate = creatDate;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public Date getCreatDate() {
			return creatDate;
		}
		public void setCreatDate(Date creatDate) {
			this.creatDate = creatDate;
		}
		
		
}
