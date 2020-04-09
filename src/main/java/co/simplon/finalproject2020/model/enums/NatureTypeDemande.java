package co.simplon.finalproject2020.model.enums;

public enum NatureTypeDemande {

	ALLOCATION ("41"),
	COMMUNICATION ("01"),
	ESTIMATION ("21"),
	RETRAITE("22"),
	REQUETE("61");

    private final String code;

    private NatureTypeDemande(final String value) {
        this.code = value;
    }

    public String getCode() { return code; }
}
