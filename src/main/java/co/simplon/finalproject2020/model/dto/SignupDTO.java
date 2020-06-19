package co.simplon.finalproject2020.model.dto;

public class SignupDTO {
	
	private String identifiantRH;
	
	private String Equipe;

	// CONSTRUCTORS
	
	public SignupDTO() {
	}
	public SignupDTO(String identifiantRH, String equipe) {
		this.identifiantRH = identifiantRH;
		Equipe = equipe;
	}

	// GETTERS / SETTERS
	
	public String getIdentifiantRH() {
		return identifiantRH;
	}

	public void setIdentifiantRH(String identifiantRH) {
		this.identifiantRH = identifiantRH;
	}

	public String getEquipe() {
		return Equipe;
	}

	public void setEquipe(String equipe) {
		Equipe = equipe;
	}

	
	

}
