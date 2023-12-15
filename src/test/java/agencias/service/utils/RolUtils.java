package agencias.service.utils;

import agencias.service.models.dto.Request.RolDTO;
import agencias.service.models.entity.Rol;
import agencias.service.models.enums.ERol;

import java.util.List;

public class RolUtils {

    public static Rol rol1(){
        Rol rol = new Rol();
        rol.setName(ERol.USER);
        return rol;
    }

    public static Rol rol2(){
        Rol rol = new Rol();
        rol.setName(ERol.ADMIN);
        return rol;
    }

    public static RolDTO rolDto(){
        RolDTO rol = new RolDTO();
        rol.setName(ERol.AGENTE_VENTAS);
        return rol;
    }

    public static RolDTO rolDto1(){
        RolDTO rol = new RolDTO();
        rol.setName(ERol.USER);
        return rol;
    }
    public static RolDTO rolDto2(){
        RolDTO rol = new RolDTO();
        rol.setName(ERol.ADMIN);
        return rol;
    }

    public static List<Rol> listaRoles(){
        return List.of(rol1(),rol2());
    }

    public static List<RolDTO> listDtoRoles(){
        return List.of(rolDto1(),rolDto2());
    }
}
