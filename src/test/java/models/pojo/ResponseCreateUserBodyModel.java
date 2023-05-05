package models.pojo;

public class ResponseCreateUserBodyModel {
    /*
    {
    "name": "morpheus",
    "job": "leader",
    "id": "81",
    "createdAt": "2023-04-29T18:31:50.346Z"
}
     */
    String name;
    String job;
    String id;
    String createdAt;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}

