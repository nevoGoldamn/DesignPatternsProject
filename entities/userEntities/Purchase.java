package userEntities;

//part of MVC
public class Purchase {//model
	
	private String firstName;
	private String lastName;
	private String remarks;
	private String type;
	private String date;
	private String id;

	public Purchase(String firstName, String lastName, String remarks, String type, String date, String id) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.remarks = remarks;
		this.type = type;
		this.date = date;
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(firstName);
		sBuilder.append(",. ");
		sBuilder.append(lastName);
		sBuilder.append(",. ");
		sBuilder.append(id);
		sBuilder.append(",. ");
		sBuilder.append(date);
		sBuilder.append(",. ");
		sBuilder.append(remarks);
		sBuilder.append(",. ");
		sBuilder.append(type);
		
		return sBuilder.toString();
	}
}
