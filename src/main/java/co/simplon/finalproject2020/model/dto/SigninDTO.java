package co.simplon.finalproject2020.model.dto;

public class SigninDTO {
	
	private String identifiantRH;
	
	private String password;
	

	public SigninDTO() {
	}

	public SigninDTO(String identifiantRH, String password) {
		this.identifiantRH = identifiantRH;
		this.password = password;
	}

	public String getIdentifiantRH() {
		return identifiantRH;
	}

	public void setIdentifiantRH(String identifiantRH) {
		this.identifiantRH = identifiantRH;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
