package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    private String email;
    private Login login;
    private Picture picture;

    public Result() {}

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Login getLogin() { return login; }
    public void setLogin(Login login) { this.login = login; }

    public Picture getPicture() { return picture; }
    public void setPicture(Picture picture) { this.picture = picture; }
}
