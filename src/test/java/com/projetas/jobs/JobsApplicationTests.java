package com.projetas.jobs;

import com.projetas.jobs.model.Vehicle;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

public class JobsApplicationTests {

    Client client = ClientBuilder.newClient();
    WebTarget target = client.target("http://localhost:8080");

    @Test
	public void readAll() {
        WebTarget target = client.target("http://localhost:8080");
        String string = target.path("/api/v1/vehicle/all").request().get(String.class);
        System.out.println(string);
        Assert.assertNotNull(string);
    }

    @Test
    public void createAndDelete() {
        Vehicle vehicle = new Vehicle(1L,"brand", "model", "color", 2017,
                20.5, "desc", false, new Date(), new Date());
        Response response = target.path("/api/v1/vehicle").request().post(Entity.entity(vehicle,MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response);
        Assert.assertEquals(response.getStatus(),200);

        response = target.path("/api/v1/vehicle/"+vehicle.getId()).request().delete();
        System.out.println(response);
        Assert.assertEquals(response.getStatus(),202);
    }

    @Test
    public void createFail() {
        Vehicle vehicle = new Vehicle(2017,
                20.5, "desc", false, new Date(), new Date());
        Response response = target.path("/api/v1/vehicle").request().post(Entity.entity(vehicle,MediaType.APPLICATION_JSON_TYPE));
        System.out.println(response);
        Assert.assertNotEquals(response.getStatus(),200);
    }

}