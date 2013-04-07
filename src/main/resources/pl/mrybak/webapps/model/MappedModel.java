package pl.mrybak.webapps.model;

public abstract class MappedModel {
	public abstract class MappedModel {
		 
	    @Id
	    @GeneratedValue(generator="system-uuid")
	    @GenericGenerator(name="system-uuid", strategy="uuid")
	    @Column(name="id")
	    private String id;
	 
	    public String getId() {
	        return id;
	    }
	 
	    public void setId(String id) {
	        this.id = id;
	    }
	}
}
