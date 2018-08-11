package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

import com.example.javajokeslib.Joker;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "sayHi")
    public MyBean sayHi(@Named("name") String name) {
        MyBean response = new MyBean();
        //response.setData("HiXXX, " + name);
        Joker joke = new Joker ();
        if (name.equals ("paid")) {
            response.setData (joke.getPaidJoke ());
        } else if (name.equals ("free")){
            response.setData (joke.getFreeJoke ());
        } else {
            response.setData (joke.getJoke ());
        }



        return response;
    }

}
