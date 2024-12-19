package resources;

import java.util.ArrayList;
import java.util.List;

import Pojo.AddPlace;
import Pojo.Locaton;
import io.restassured.RestAssured;

public class TestDataBuild {
	
    public AddPlace addPlacePayload(String name, String language, String address)
    {
    //Pojo.AddPlace p =new Pojo.AddPlace();
    AddPlace p1 =new AddPlace();
    
    p1.setAccuracy(42);
    p1.setAddress(address);
    p1.setLanguage(language);
	p1.setPhone_number("(+91) 983 893 3937");
	p1.setWebsite("https://rahulshettyacademy.com/");
	p1.setName(name);
	List<String> mylist = new ArrayList<String>();
	mylist.add("Shoe park");
	mylist.add("Shop");
	p1.setTypes(mylist);
	Locaton l= new Locaton();
	l.setLat(-41.383494);
	l.setLng(42.427362);
	p1.setLocation(l);
	return p1;
    }
		
    
    public String deletePlacePayload(String placeId)
	{
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
	}
}
