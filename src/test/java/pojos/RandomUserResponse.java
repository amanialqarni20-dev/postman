package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RandomUserResponse {
    private List<Result> results;

    public RandomUserResponse() {}

    public List<Result> getResults() { return results; }
    public void setResults(List<Result> results) { this.results = results; }
}
