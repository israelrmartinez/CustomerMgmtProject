package pkg.cogent.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer {
	private String cId;
	private String cName;
	private String cEmail;
	private String cDob;
	public Date cDobObj;
	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String cId, String cName, String cEmail, String cDob) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.cEmail = cEmail;
		this.cDob = cDob;
		try {
			this.cDobObj = sdformat.parse(cDob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}

	public String getcDob() {
		String dob = sdformat.format(this.cDobObj);
		return dob;
	}

	public void setcDob(String cDob) {
		this.cDob = cDob;
		try {
			this.cDobObj = sdformat.parse(cDob);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
