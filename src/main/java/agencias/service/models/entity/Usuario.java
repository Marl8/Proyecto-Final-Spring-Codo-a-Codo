package agencias.service.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @NotEmpty(message = "El nombre no puede estar vacio")
    @Size(min = 4, max = 12, message = "El tamaño del nombre debe ser entre 4 y 12 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotEmpty(message = "El apellido no puede estar vacio")
    @Size(min = 4, max = 12, message = "El tamaño del apellido debe ser entre 4 y 12 caracteres")
    @Column(name = "apellido")
    private String apellido;

    @NotNull(message = "El DNI es requerido")
    @Column(name = "dni", unique = true)
    private Long dni;

    @NotNull(message = "El teléfono es requerido")
    @Column(name = "telefono", unique = true)
    private Long telefono;

    @NotNull(message = "El email es requerido")
    @Column(name = "email")
    private String email;

    @NotNull(message = "La fecha de nacimiento es requerido")
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @NotBlank(message ="Debe indicarse el username del usuario")
    private String username;

    @NotBlank(message ="Debe indicarse el password del usuario")
    private String password;

    @NotEmpty(message = "El usuario debe tener asignado algún rol")
    @ManyToMany(targetEntity = Rol.class, cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "fk_user"),
            inverseJoinColumns = @JoinColumn(name = "fk_rol"))
    private Set<Rol> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = this.roles.stream().map(
                rol -> new SimpleGrantedAuthority("ROLE_" + rol.getName().name())).toList();
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
