package com.martin.Drive.config;


import com.martin.Drive.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.Projection;

/*Exponer toda la API Rest mostraría las contraseñas hasheadas
* Por ello hay que crear una proyección que solo muestre
* los campos que nos interesen */
@Projection(name = "UserProjection", types = {User.class})
public interface UserProjection {
    String getNombre();
    int getPuntuacion();

    String getEmail();
    String getFacebook();
    String getTwitter();
    String getBio();
    String getInstagram();
    String getRoles();
    String getMovil();
    String getAvatar();
}
