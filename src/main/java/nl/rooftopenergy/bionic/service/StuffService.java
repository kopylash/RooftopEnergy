package nl.rooftopenergy.bionic.service;

import javax.inject.Named;


@Named
public class StuffService {
	
 public boolean checkLoginAndPassword(String name, String password){
     if ("user".equals(name) && "password".equals(password)) return true;
     else return false;
 }
	
}
