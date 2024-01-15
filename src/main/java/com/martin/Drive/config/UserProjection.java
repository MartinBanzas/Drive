package com.martin.Drive.config;


import com.martin.Drive.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "UserProjection", types = {User.class})
public interface UserProjection {
    String getNombre();
    int getPuntuacion();
}
