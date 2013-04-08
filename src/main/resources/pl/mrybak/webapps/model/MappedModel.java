package pl.mrybak.webapps.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
// even though there is no table created for MappedModel, pass down it's properties to subclasses
public abstract class MappedModel {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid") // we will use Hibernate UUID generator
	@Column(name = "id")   // this is redundant in this case (column name is by default same with field name)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
