package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Picture {
    private String medium;

    public Picture() {}

    public String getMedium() { return medium; }
    public void setMedium(String medium) { this.medium = medium; }
}
