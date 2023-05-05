package models.lombok;

import lombok.Data;

@Data
public class RequestRegisterUserBodyLombokModel {
    /*
{
    "email": "eve.holt@reqres.in",
    "password": "pistol"
}
     */

    String email, password;


}