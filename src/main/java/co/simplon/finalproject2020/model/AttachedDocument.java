package co.simplon.finalproject2020.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
	private int fileReference;			// r�f�rence du dossier auquel la piece jointe est associ�e
	
	@Lob // for Large Objects
	@Column(nullable=false, columnDefinition="MEDIUMBLOB") // columnDefinition not working, though 'nullable = false' did up me from tinyblob to blob
	private byte[] content;				// contenu de la pi�ce jointe (tout type de fichier peut �tre r�duit � un tableau de byte)

	
	
	// CONSTRUCTORS
	
	public AttachedDocument() {
	}

	public AttachedDocument(int fileReference, byte[] content) {
		this.fileReference = fileReference;
		this.content = content;
	}

	public AttachedDocument(String name, String fileExtension, int fileReference, byte[] content) {
		super();
		this.name = name;
		this.fileExtension = fileExtension;
		this.fileReference = fileReference;
		this.content = content;
	}	
	
	// IMPORTANT OVERRIDES

	@Override
	public String toString() {
		return "AttachedDocument [id=" + id + ", name=" + name + ", fileExtension=" + fileExtension + ", fileReference="
				+ fileReference + ", content=" + Arrays.toString(content) + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(content);
		result = prime * result + ((fileExtension == null) ? 0 : fileExtension.hashCode());
		result = prime * result + fileReference;
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
		if (fileReference != other.fileReference)
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

	public int getFileReference() {
		return fileReference;
	}

	public void setFileReference(int fileReference) {
		this.fileReference = fileReference;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
	

}
