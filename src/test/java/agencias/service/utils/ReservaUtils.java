package agencias.service.utils;

import agencias.service.models.dto.Request.ReservaRequestDTO;
import agencias.service.models.dto.Request.ReservasByUserRequestDTO;
import agencias.service.models.dto.Request.TicketRequestDTO;
import agencias.service.models.dto.Response.TicketByUserResponseDTO;
import agencias.service.models.entity.*;
import agencias.service.models.enums.Clase;
import agencias.service.models.enums.TipoPago;

import java.time.LocalDate;
import java.util.List;

public class ReservaUtils {

    public static Reserva reserva1(){
        Vuelo vuelo = new Vuelo();
        vuelo.setNumVuelo(123);
        vuelo.setFecha(LocalDate.parse("2024-12-21"));
        Itinerario itinerario = new Itinerario();
        itinerario.setCiudadOrigen("Montevideo");
        itinerario.setCiudadDestino("Londres");
        vuelo.setItinerario(itinerario);
        Ticket ticket = new Ticket();
        ticket.setNumAsiento(65);
        ticket.setClase(Clase.ECONOMIC);
        List<Ticket> tickets = List.of(ticket);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Silva");
        return new Reserva(1L, TipoPago.PAGO_ONLINE, LocalDate.now(), vuelo,
                tickets, 90000D, usuario);
    }

    public static Reserva reserva2(){
        Vuelo vuelo = new Vuelo();
        vuelo.setNumVuelo(5236);
        vuelo.setFecha(LocalDate.parse("2024-03-09"));
        vuelo.setAerolinea(new Aerolinea(1L, "FlyBondy", "35478956987"));
        Itinerario itinerario = new Itinerario();
        itinerario.setCiudadOrigen("Madrid");
        itinerario.setCiudadDestino("Paris");
        vuelo.setItinerario(itinerario);
        Ticket ticket = new Ticket();
        ticket.setNumAsiento(128);
        ticket.setClase(Clase.BUSINESS);
        ticket.setPrecio(1000);
        List<Ticket> tickets = List.of(ticket);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2L);
        usuario.setNombre("Ana");
        usuario.setApellido("Romero");
        usuario.setDni(32145876L);
        usuario.setEmail("anar@gmail.com");
        usuario.setFechaNacimiento(LocalDate.of(2023, 5, 9));
        return new Reserva(1L, TipoPago.TARJETA_CREDITO, LocalDate.of(2023, 12, 11), vuelo,
                tickets, 153000D, usuario);
    }

    public static Reserva reserva3(){
        Vuelo vuelo = new Vuelo();
        vuelo.setNumVuelo(1235);
        vuelo.setFecha(LocalDate.parse("2023-06-27"));
        vuelo.setAerolinea(new Aerolinea(1L, "Aerolineas Argentinas", "35478956693"));
        Itinerario itinerario = new Itinerario();
        itinerario.setCiudadOrigen("Buenos aires");
        itinerario.setCiudadDestino("Rio Janeiro");
        vuelo.setItinerario(itinerario);
        Ticket ticket = new Ticket();
        ticket.setNumAsiento(101);
        ticket.setPrecio(9000D);
        ticket.setClase(Clase.BUSINESS);
        ticket.setPrecio(1000);
        List<Ticket> tickets = List.of(ticket);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2L);
        usuario.setNombre("Ana");
        usuario.setApellido("Romero");
        usuario.setDni(32145876L);
        usuario.setEmail("anar@gmail.com");
        usuario.setFechaNacimiento(LocalDate.of(2023, 5, 9));
        return new Reserva(1L, TipoPago.TARJETA_CREDITO, LocalDate.of(2023, 12, 11), vuelo,
                tickets, 115500D, usuario);
    }

    public static ReservaRequestDTO reservaDto1(){
        Vuelo vuelo = new Vuelo();
        vuelo.setIdVuelo(1L);
        vuelo.setNumVuelo(123);
        vuelo.setFecha(LocalDate.parse("2024-12-21"));
        Itinerario itinerario = new Itinerario();
        itinerario.setCiudadOrigen("Montevideo");
        itinerario.setCiudadDestino("Londres");
        vuelo.setItinerario(itinerario);
        TicketRequestDTO ticket = new TicketRequestDTO();
        ticket.setNumAsiento(65);
        ticket.setPrecio(90000D);
        ticket.setClase(Clase.ECONOMIC);
        List<TicketRequestDTO> tickets = List.of(ticket);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1L);
        usuario.setNombre("Juan");
        usuario.setApellido("Silva");
        return new ReservaRequestDTO(LocalDate.now(),
                TipoPago.PAGO_ONLINE, vuelo.getIdVuelo(), usuario.getIdUsuario() ,tickets);
    }

    public static ReservasByUserRequestDTO reservaByDto2(){
        Vuelo vuelo = new Vuelo();
        vuelo.setNumVuelo(5236);
        vuelo.setFecha(LocalDate.parse("2024-03-09"));
        Itinerario itinerario = new Itinerario();
        itinerario.setCiudadOrigen("Madrid");
        itinerario.setCiudadDestino("Paris");
        vuelo.setItinerario(itinerario);
        TicketByUserResponseDTO ticket = new TicketByUserResponseDTO();
        ticket.setNumAsiento(128);
        ticket.setClase(Clase.BUSINESS);
        ticket.setPrecio(1000);
        List<TicketByUserResponseDTO> tickets = List.of(ticket);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2L);
        usuario.setNombre("Ana");
        usuario.setApellido("Romero");
        ReservasByUserRequestDTO r = new ReservasByUserRequestDTO();
        r.setFechaReserva(LocalDate.of(2023, 12, 11));
        r.setNombre(usuario.getNombre());
        r.setApellido(usuario.getApellido());
        r.setDni(32145876L);
        r.setEmail("anar@gmail.com");
        r.setFechaNacimiento(LocalDate.of(2023, 5, 9));
        r.setNumVuelo(vuelo.getNumVuelo());
        r.setFecha(vuelo.getFecha());
        r.setAerolinea(new Aerolinea(1L, "FlyBondy", "35478956987"));
        r.setItinerario(itinerario);
        r.setTipoPago(TipoPago.TARJETA_CREDITO);
        r.setTickets(tickets);
        return r;
    }

    public static ReservasByUserRequestDTO reservaByDto3(){
        Vuelo vuelo = new Vuelo();
        vuelo.setNumVuelo(1235);
        vuelo.setFecha(LocalDate.parse("2023-06-27"));
        Itinerario itinerario = new Itinerario();
        itinerario.setCiudadOrigen("Buenos aires");
        itinerario.setCiudadDestino("Rio Janeiro");
        vuelo.setItinerario(itinerario);
        TicketByUserResponseDTO ticket = new TicketByUserResponseDTO();
        ticket.setNumAsiento(101);
        ticket.setClase(Clase.BUSINESS);
        ticket.setPrecio(1000);
        List<TicketByUserResponseDTO> tickets = List.of(ticket);
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(2L);
        usuario.setNombre("Ana");
        usuario.setApellido("Romero");
        ReservasByUserRequestDTO r = new ReservasByUserRequestDTO();
        r.setFechaReserva(LocalDate.of(2023, 12, 11));
        r.setNombre(usuario.getNombre());
        r.setApellido(usuario.getApellido());
        r.setDni(32145876L);
        r.setEmail("anar@gmail.com");
        r.setFechaNacimiento(LocalDate.of(2023, 5, 9));
        r.setNumVuelo(vuelo.getNumVuelo());
        r.setFecha(vuelo.getFecha());
        r.setAerolinea(new Aerolinea(1L, "Aerolineas Argentinas", "35478956693"));
        r.setItinerario(itinerario);
        r.setTipoPago(TipoPago.TARJETA_CREDITO);
        r.setTickets(tickets);
        return r;
    }
    
    public static List<Reserva> listaReservas(){
       return List.of(reserva2(), reserva3());
    }

    public static List<ReservasByUserRequestDTO> listaReservasByDto(){
        return List.of(reservaByDto2(),reservaByDto3());
    }
}
