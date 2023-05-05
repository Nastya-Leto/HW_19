package models.lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseWithGivenTotalModel {

    private Integer page;
    private Integer perPage;
    private Integer total;
    private Integer totalPages;
    private List<ListUsersData> data;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ListUsersData {
        private String avatar;
        private String email;
        @JsonProperty("first_name")
        private String firstName;
        private Integer id;
        @JsonProperty("last_name")
        private String lastName;
    }
}
