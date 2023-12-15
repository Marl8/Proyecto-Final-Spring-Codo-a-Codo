package agencias.service.service.impl;

import agencias.service.exceptions.CustomException;
import agencias.service.exceptions.ReservaNotFoundException;
import agencias.service.models.dto.Request.ReservaRequestDTO;
import agencias.service.models.dto.Request.ReservasByUserRequestDTO;
import agencias.service.models.dto.Request.TicketRequestDTO;
import agencias.service.models.dto.Response.ReporteResponseDTO;
import agencias.service.models.dto.Response.ReservaResponseDTO;
import agencias.service.models.dto.Response.ReservasByUserResponseDTO;
import agencias.service.models.dto.Response.TicketByUserResponseDTO;
import agencias.service.models.entity.Reserva;
import agencias.service.models.entity.Ticket;
import agencias.service.models.entity.Usuario;
import agencias.service.models.entity.Vuelo;
import agencias.service.repository.ReservaRepository;
import agencias.service.repository.UsuarioRepository;
import agencias.service.repository.VueloRepository;
import agencias.service.service.ReservaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
public class ReservaServiceImpl implements ReservaService {

    ReservaRepository reservaRepo;

    UsuarioRepository usuarioRepository;

    VueloRepository vueloRepository;

    public ReservaServiceImpl(ReservaRepository reservaRepo, UsuarioRepository userRepo, VueloRepository vueloRepository) {
        this.reservaRepo = reservaRepo;
        this.usuarioRepository = userRepo;
        this.vueloRepository = vueloRepository;
    }

    @Override
    public ReporteResponseDTO generarReporte(LocalDate since, LocalDate to) {
        //obtengo todas las reservas de la base de datos
        List<Reserva> resultRepo = reservaRepo.findAll();
        //obtengo el resultado del filtro
        List<Reserva> result = new ArrayList<>();

        Double ingresosTotales = 0.00;
        //para generar el reporte
        ReporteResponseDTO reporte = new ReporteResponseDTO();
        //contar cuantas veces repite el destino
        Map<String, Integer> contadorDestinos = new HashMap<>();
        String destino = null;

        for (Reserva res : resultRepo) {
            if (res.getFechaReserva().isAfter(since) && res.getFechaReserva().isBefore(to)) {
                result.add(res);
            }
        }

        if (result.isEmpty()) {
            throw new ReservaNotFoundException("No existen reservas para el rango de fechas seleccionadas");
        }

        int cantidadReservas = result.size();
        for (Reserva reserva : result) {
            destino = reserva.getVuelo().getItinerario().getCiudadDestino();
            contadorDestinos.put(destino, contadorDestinos.getOrDefault(destino, 0) + 1);
            for (Ticket ticket : reserva.getTickets()) {
                ingresosTotales += ticket.getPrecio();
            }
        }

        reporte.setNumeroVuelosVendidos(cantidadReservas);
        reporte.setIngresosGenerados(ingresosTotales);
        reporte.setDestinosPopulares(contadorDestinos);
        return reporte;

    }

    @Override
    public ReporteResponseDTO generarReporteUnaFecha(LocalDate fecha) {
        //obtengo todas las reservas de la base de datos
        List<Reserva> resultRepo = reservaRepo.findAll();
        //obtengo el resultado del filtro
        List<Reserva> result = new ArrayList<>();
        //para generar el reporte
        ReporteResponseDTO reporte = new ReporteResponseDTO();


        Double ingresosTotales = 0.00;

        //contar cuantas veces repite el destino
        Map<String, Integer> contadorDestinos = new HashMap<>();
        String destino = null;
        //obtengo las reservas para una fecha determinada
        for (Reserva res : resultRepo) {
            if (res.getFechaReserva().equals(fecha)) {
                result.add(res);

            }
        }
        if (result.isEmpty()) {
            throw new ReservaNotFoundException("No existen reservas para la fecha solicitada");
        }

        //tomo de la lista que se filtro la fecha los tickets para calcular los ingresos generados y destinos
        for (Reserva reserva : result) {
            destino = reserva.getVuelo().getItinerario().getCiudadDestino();
            contadorDestinos.put(destino, contadorDestinos.getOrDefault(destino, 0) + 1);
            for (Ticket ticket : reserva.getTickets()) {
                ingresosTotales += ticket.getPrecio();
            }
        }
        int cantidadReservas = result.size();

        reporte.setNumeroVuelosVendidos(cantidadReservas);
        reporte.setIngresosGenerados(ingresosTotales);
        reporte.setDestinosPopulares(contadorDestinos);
        return reporte;
    }

    @Override
    public ReservasByUserResponseDTO reservasByUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() ->
                new ReservaNotFoundException("No existen usuarios con ese id"));
        ModelMapper mapper =  new ModelMapper();
        List<Reserva> reservas = reservaRepo.findReservaByIdUsuario(id);

        List<ReservasByUserRequestDTO> listaReservas = getReservasByUserRequestDTO(reservas, mapper);
        return new ReservasByUserResponseDTO("Reservas efectuadas por " +
                usuario.getNombre() + " " + usuario.getApellido() + ": ", listaReservas);
    }

    public List<ReservasByUserRequestDTO> getReservasByUserRequestDTO(List<Reserva> reservas, ModelMapper mapper) {
        return reservas.stream().map(r -> new ReservasByUserRequestDTO(
                r.getFechaReserva(), r.getUsuario().getNombre(), r.getUsuario().getApellido(), r.getUsuario().getDni(),
                r.getUsuario().getTelefono(), r.getUsuario().getEmail(),r.getUsuario().getFechaNacimiento(), r.getTipoPago(),
                r.getVuelo().getNumVuelo(), r.getVuelo().getFecha(), r.getVuelo().getItinerario(), r.getVuelo().getAerolinea(),
                r.getTickets().stream().map(t -> mapper.map(t, TicketByUserResponseDTO.class)).toList())).toList();
    }

    @Override
    public ReservaResponseDTO crearReserva(ReservaRequestDTO reservaRequestDTO) {

        try {
            Optional<Vuelo> vueloOptional = vueloRepository.findById(reservaRequestDTO.getIdVuelo());
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(reservaRequestDTO.getIdUsuario());

            if (vueloOptional.isEmpty()){
                throw new CustomException(HttpStatus.NOT_FOUND, "No existe el vuelo que intenta reservar");
            }

            if (usuarioOptional.isEmpty()){
                throw new CustomException(HttpStatus.NOT_FOUND, "No existe el usuario con el que intenta reservar");
            }

            Reserva reserva = new Reserva();

            reserva.setTipoPago(reservaRequestDTO.getTipoPago());
            reserva.setFechaReserva(reservaRequestDTO.getFechaReserva());
            reserva.setVuelo(vueloOptional.get());

            List<Ticket> ticketList = new ArrayList<>();
            Double total = 0D;
            for (TicketRequestDTO ticketRequestDTO : reservaRequestDTO.getTickets()){
                total += ticketRequestDTO.getPrecio();
                Ticket ticket = new Ticket();

                ticket.setNumAsiento(ticketRequestDTO.getNumAsiento());
                ticket.setPrecio(ticketRequestDTO.getPrecio());
                ticket.setClase(ticketRequestDTO.getClase());
                ticket.setPasajero(ticketRequestDTO.getPasajero());
                ticket.setReserva(reserva);

                ticketList.add(ticket);
            }

            reserva.setTickets(ticketList);
            long diasHastaVuelo = ChronoUnit.DAYS.between(reservaRequestDTO.getFechaReserva(), vueloOptional.get().getFecha());
            reserva.setTotal(diasHastaVuelo >= 7 ? total * 0.9 : total);
            reserva.setUsuario(usuarioOptional.get());

            reservaRepo.save(reserva);

            return new ReservaResponseDTO("Su reserva ha sido exitosa. " + (diasHastaVuelo >=7 ? "Precio con descuento" : ""),
                    "Vuelo con salida de " + vueloOptional.get().getItinerario().getCiudadOrigen() + " con destino a "
                            + vueloOptional.get().getItinerario().getCiudadDestino(),
                    reserva.getTotal(),
                    reserva.getTipoPago(),
                    reserva.getFechaReserva());

        } catch (Exception e){
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

    }
}
