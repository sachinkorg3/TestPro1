package resources;

public enum APIResources {
	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	
	private String resource;
	APIResources(String resoruce)
	
	{
		
		this.resource=resoruce;
	}

public String getResource()
{
	return resource;
}

}
