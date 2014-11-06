package nl.rooftopenergy.bionic.web;


import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import nl.rooftopenergy.bionic.service.StuffService;

@Named
@Scope("session")
public class LoginBean {
	@Inject
	private StuffService stuffService;
	
	private String name;
	private String password;



    private String rememberMe;
	
	private String message;
	
	public String user;

	public LoginBean() {  }
	
	public String login(){
		boolean result =  stuffService.checkLoginAndPassword(name, password);
		if (result) {user = "User"; return "loggedPage?faces-redirect=true";}
		else {message = "Wrong login/password" ; return "loginPage?faces-redirect=true";}
	}

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "loginPage?faces-redirect=true";
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    public String getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(String rememberMe) {
        this.rememberMe = rememberMe;
    }
}
