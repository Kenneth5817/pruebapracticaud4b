package org.iesvdm.appointment.service.impl;

import net.bytebuddy.asm.Advice;
import org.assertj.core.api.Assertions;
import org.iesvdm.appointment.entity.Appointment;
import org.iesvdm.appointment.entity.AppointmentStatus;
import org.iesvdm.appointment.entity.Customer;
import org.iesvdm.appointment.entity.ExchangeRequest;
import org.iesvdm.appointment.repository.AppointmentRepository;
import org.iesvdm.appointment.repository.ExchangeRequestRepository;
import org.iesvdm.appointment.repository.impl.AppointmentRepositoryImpl;
import org.iesvdm.appointment.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class ExchangeServiceImplTest {

    @Spy
    private AppointmentRepository appointmentRepository = new AppointmentRepositoryImpl(new HashSet<>());

    @Mock
    private NotificationService notificationService;

    @Mock
    private  ExchangeRequestRepository exchangeRequestRepository;

    @InjectMocks
    private ExchangeServiceImpl exchangeService;

    private Customer customer1 = new Customer(1
            ,"paco"
            , "1234"
            , new ArrayList<>());
    private Customer customer2 = new Customer(2
            ,"pepe"
            , "1111"
            , new ArrayList<>());

    @Captor
    private ArgumentCaptor<Integer> appointmentIdCaptor;

    @Spy
    private Appointment appointment1 = new Appointment(LocalDateTime.of(2024, 6, 10,6, 0)
            , LocalDateTime.of(2024, 6, 16,18, 0)
            , null
            , null
            , AppointmentStatus.SCHEDULED
            , customer1
            , null
                                );

    @Spy
    private Appointment appointment2 = new Appointment(LocalDateTime.of(2024, 5, 18,8, 15)
            , LocalDateTime.of(2024, 5, 18,10, 15)
            , null
            , null
            , AppointmentStatus.SCHEDULED
            , customer2
            , null
    );

    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

    }

    /**
     * Crea un stub para appointmentRepository.getOne
     * que devuelva una cita (Appointment) que
     * cumple que la fecha/tiempo de inicio (start) es
     * al menos un día después de la fecha/tiempo de búsqueda (actual)
     * , junto con los parámetros de estar planificada (SCHEDULED) y
     * pertenecer al cliente con userId 3.
     * De este modo que al invocar exchangeServiceImpl.checkIfEligibleForExchange
     * se debe obtener true.
     */
    @Test
    void checkIfEligibleForExchange() {
        //Creamos las citas
        Appointment appointment1= spy(new Appointment(1,2,"1"));
        Appointment appointment2= spy(new Appointment(1,2,"3"));

        appointmentRepository.getOne(1);
        appointmentRepository.getOne(2);

        //when
        //Guardamos las citas
        AppointmentRepository.save(appointment1);
        AppointmentRepository.save(appointment2);
        appointmentRepository.getOne(appointment1.getId());

        //then
        //Al obtener los datos, tendremos true
        Assertions.assertThat(exchangeService.checkIfEligibleForExchange(3),true);
        Assertions.assertThat(exchangeService.checkIfEligibleForExchange(1), true);
    }


    /**
     * Añade mediante appointementRepository.save
     * 2 citas (Appointment) de modo que la eligible
     * la 2a empieza más de 24 horas más tarde
     * y pertenece a un cliente (Customer) con id diferente del
     * cliente de la primera que será appointmentToExchange.
     * Se debe verificar la invocación de los métodos appointmentRepository.getOne
     * con el appointmentId pasado a capturar mediante el captor de id
     */
    @Test
    void getEligibleAppointmentsForExchangeTest() {
        //Creamos las citas. LA 2ª empieza 24 horas más tarde, por lo tanto, la cita del appointment2 será del dia siguiente
        Appointment appointment1= spy(new Appointment(12:30,14,"1"));
        Appointment appointment2= spy(new Appointment(12:30,15,"3"));

        //when Cuando los guardamos
        AppointmentRepository.save(appointment1);
        AppointmentRepository.save(appointment2);

        //then Comprobamos con el captor
        //PAra elo, primero cogemos el appointmentRepository y al hacerle el captor, creamos una coleccion de integer donde se almacenarán los códigos de las citas de las personas
        appointmentRepository.getOne(3);
        ArgumentCaptor<Integer> idCaptor= ArgumentCaptor.forClass(Integer.class);
        //Get All VAlues me da fallos
        verify(appointmentRepository.getOne(idCaptor.capture().getClass());

    }

    /**
     * Realiza una prueba que mediante stubs apropiados demuestre
     * que cuando el userID es igual al userId del oldAppointment
     * se lanza una RuntimeException con mensaje Unauthorized
     */
    @Test
    void checkIfExchangeIsPossibleTest() {
        //given DAdo dos citas usando stub (spy)
        Appointment ap1= spy(new Appointment(2,3,1234));
        Appointment ap2= spy(new Appointment(3,4,4231));

        //then Cuando los guardamos. comprobamos que....
        AppointmentRepository.add(ap1);
        AppointmentRepository.add(ap2);

        //when Cuando no se cumple la condición, lanzamos el mensaje de excepcion
        //eN la estructura de la excepcion me falta algo y por eso después del when se me marca en rojo
        Mockito.doThrow(RuntimeException.class("Unauthorized").when(AppointmentRepository).userId(any(), anyDouble());
    }

    /**
     * Crea un stub para exchangeRequestRepository.getOne
     * que devuelva un exchangeRequest que contiene una cita (Appointment)
     * en el método getRequestor.
     * Verifica que se invoca exchangeRequestRepository.save capturando
     * al exchangeRequest y comprobando que se le ha establecido un status
     * rechazado (REJECTED).
     * Verfifica se invoca al método con el exchangeRequest del stub.
     */
     void rejectExchangeTest() {
         //given Dado una cita usando Spy....
         Appointment ap1= spy(new Appointment(2,3,1234));

         //when (Cuando lo guardemos...)
         exchangeRequestRepository.save(ap1.getExchangeRequest());


         //then Comprobamos con el captor
         //PAra elo, primero cogemos el appointmentRepository y al hacerle el captor, creamos una coleccion de integer donde se almacenarán los códigos de las citas de las personas
         appointmentRepository.getOne(1234);
         ArgumentCaptor<Integer> idCaptor= ArgumentCaptor.forClass(Integer.class);
         //Get All VAlues me da fallos para comprobar y verificar si es correcto
         verify(exchangeRequestRepository.getOne(idCaptor.capture().getClass()));

     }

}
