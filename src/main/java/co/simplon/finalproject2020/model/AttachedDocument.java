package co.simplon.finalproject2020.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import co.simplon.finalproject2020.model.Demande;

@Entity
public class AttachedDocument {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String fileExtension;
	
	/*
	 * @ManyToOne
	 * @JoinColumn(name = "id_file", referencedColumnName = "id" )		// in that case use Demande Object instead of int.
	 */
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "NNUMERO")
	private Demande demande;			// référence du dossier auquel la piece jointe est associée
	
	@Lob // for Large Objects
	@Column(nullable=false, columnDefinition="MEDIUMBLOB") // columnDefinition not working, though 'nullable = false' did up me from tinyblob to blob
	private byte[] content;				// contenu de la pièce jointe (tout type de fichier peut être réduit à un tableau de byte)

	
	
	// CONSTRUCTORS
	
	public AttachedDocument() {
	}

	public AttachedDocument(Demande demande, byte[] content) {
		this.demande = demande;
		this.content = content;
	}

	public AttachedDocument(String name, String fileExtension, Demande demande, byte[] content) {
		this.name = name;
		this.fileExtension = fileExtension;
		this.demande = demande;
		this.content = content;
	}	
	
	// IMPORTANT OVERRIDES

	@Override
	public String toString() {
		return "AttachedDocument [id=" + id + ", name=" + name + ", fileExtension=" + fileExtension + ", demande="
				+ demande + ", content length=" + content.length + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(content);
		result = prime * result + ((fileExtension == null) ? 0 : fileExtension.hashCode());
		result = prime * result + ((demande == null) ? 0 : demande.hashCode());
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttachedDocument other = (AttachedDocument) obj;
		if (!Arrays.equals(content, other.content))
			return false;
		if (fileExtension == null) {
			if (other.fileExtension != null)
				return false;
		} else if (!fileExtension.equals(other.fileExtension))
			return false;
		if (demande != other.demande)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	// GETTERS / SETTERS

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public Demande getFileReference() {
		return demande;
	}

	public void setFileReference(Demande fileReference) {
		this.demande = fileReference;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	

}
