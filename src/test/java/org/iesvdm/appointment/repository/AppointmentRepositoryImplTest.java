package org.iesvdm.appointment.repository;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.BigDecimalAssert;
import org.iesvdm.appointment.entity.Appointment;
import org.iesvdm.appointment.repository.impl.AppointmentRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.print.DocFlavor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

public class AppointmentRepositoryImplTest {

    private Set<Appointment> appointments;

    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setup() {
        appointments = new HashSet<>();
        appointmentRepository = new AppointmentRepositoryImpl(appointments);
    }

    /**
     * Crea 2 citas (Appointment) una con id 1 y otra con id 2,
     * resto de valores inventados.
     * Agrégalas a las citas (appointments) con la que
     * construyes el objeto appointmentRepository bajo test.
     * Comprueba que cuando invocas appointmentRepository.getOne con uno
     * de los id's anteriores recuperas obtienes el objeto.
     * Pero si lo invocas con otro id diferente recuperas null
     */
    @Test
    void getOneTest() {
        //given Cuando tengo 2 citas (NO me deja meter los campos de inicioVIsita, FinalVisita, por eso me da
        // fallos y no me funciona como debería, pero la manera y estructura es esa. Primero creamos citas, las guardamos/añadimos y
        // después comprobamos lo que nos pide
        Appointment appointment1= new Appointment(3);
        Appointment appointment2= new Appointment(5);
        //when CUando añadimos
        AppointmentRepository.add(appointment1);
        AppointmentRepository.add(appointment2);
        //then   El then return me deberia devolver el resultado pero no me lo coge
        Mockito.when(appointmentRepository.getOne(3).thenReturn(appointment1));
        //Devuelve null si el id de appointment da fallo
        Mockito.when(appointmentRepository.getOne(4).thenReturn(null));
    }


    /**
     * Crea 2 citas (Appointment) y guárdalas mediante
     * appointmentRepository.save.
     * Comprueba que la colección appointments
     * contiene sólo esas 2 citas.
     */
    @Test
    void saveTest() {

        //given  En el constructor tenia que meter valores pero no me salia por la estructutra al añadir los LOcalDAteTIme
        //  Por eso los he dejado vacios, pero deberían ir rellenos con valores e información
        Appointment appointment1= new Appointment();
        Appointment appointment2= new Appointment();

        //when
        AppointmentRepository.save(appointment1);
        AppointmentRepository.save(appointment2);
        //then  //Quiero que me aqsegure que es un decimal pero no me lo coge
        assertThat(appointment1, new BigDecimalAssert(appointment1));
        assertThat(appointment2, new BigDecimalAssert(appointment2));
    }

    /**
     * Crea 2 citas (Appointment) una cancelada por un usuario y otra no,
     * (atención al estado de la cita, lee el código) y agrégalas mediante
     * appointmentRepository.save a la colección de appointments
     * Comprueba que mediante appointmentRepository.findCanceledByUser
     * obtienes la cita cancelada.
     */
    @Test
    void findCanceledByUserTest() {

        //given  DAdo 2 empleados
        AppointmentRepository appointment1= (AppointmentRepository) new Appointment();
        AppointmentRepository appointment2= (AppointmentRepository) new Appointment();

        //when CUando los guardamos
        appointmentRepository.save(appointment1):
        appointmentRepository.save(appointment2);


        //then Comprobamos que la cita se cancela usando verify cuando devuelve null
        Mockito.when(appointmentRepository.findCanceledByUser()).thenReturn(null);
        verify(appointment1, 5);
    }

    private void verify(AppointmentRepository appointmentRepository, int i) {
    }

    /**
     * Crea 3 citas (Appointment), 2 para un mismo cliente (Customer)
     * con sólo una cita de ellas presentando fecha de inicio (start)
     * y fin (end) dentro del periodo de búsqueda (startPeriod,endPeriod).
     * Guárdalas mediante appointmentRepository.save.
     * Comprueba que appointmentRepository.findByCustomerIdWithStartInPeroid
     * encuentra la cita en cuestión.
     * Nota: utiliza LocalDateTime.of(...) para crear los LocalDateTime
     */
    @Test
    void findByCustomerIdWithStartInPeroidTest() {

        //given DADO 3 citas(2 de ellas iguales) El constructor me está mareando
        //Te pide que metas fechaIncio, fechaFInal y el id_cliente
        Appointment ap2;
        Appointment ap1= new Appointment(2024-12-03, 1//Juan);
        Appointment ap2= new Appointment(2024-03-23, 2//Pepe);
        Appointment ap3= new Appointment(2024-03-23, 2//Pepe);

        setup();

        final LocalDateTime l1 = LocalDateTime.of(2022, 02, 12, 12, 12);
        //l2 y l3 son iguales
        final LocalDateTime l2= LocalDateTime.of(2024,12,12,14,02);
        final LocalDateTime l3= LocalDateTime.of(2024,12,12,14,02);

        //GUardamos las citas
        Appointment.save(ap1);
        Appointment.save(ap2);
        Appointment.save(ap3);
        //when
        verify(appointmentRepository.findByCustomerIdWithStartInPeroid(3,12, 13));
        //then

    }


    /**
     * Crea 2 citas (Appointment) una planificada (SCHEDULED) con tiempo fin
     * anterior a la tiempo buscado por appointmentRepository.findScheduledWithEndBeforeDate
     * guardándolas mediante appointmentRepository.save para la prueba de findScheduledWithEndBeforeDate
     *
     */
    @Test
    void findScheduledWithEndBeforeDateTest() {

        //given
        //Volvemos a lo mismo de siempre, el constructor está incorrecto y por eso salen fallos
        Appointment ap1= new Appointment(12,14,"1234");
        Appointment ap2= new Appointment(13,14,"1423");

        //when
        appointmentRepository.save(ap1);
        appointmentRepository.save(ap2);

        //then Comprobamos que sea correcto y lo verificamos
        verify((AppointmentRepository) ap1, 1234);
        verify((AppointmentRepository) ap2, 1423);

        //En este caso cogiendo el ap1 y el ap2 pero como está el constructor con paráetros vacios está mal
        Assertions.assertThat(ap1, 1234);
        Assertions.assertThat(ap2, 1423);
    }


    /**
     * Crea 3 citas (Appointment) planificadas (SCHEDULED)
     * , 2 para un mismo cliente, con una elegible para cambio (con fecha de inicio, start, adecuada)
     * y otra no.
     * La tercera ha de ser de otro cliente.
     * Guárdalas mediante appointmentRepository.save
     * Comprueba que getEligibleAppointmentsForExchange encuentra la correcta.
     */
    @Test
    void getEligibleAppointmentsForExchangeTest() {
        //given
        //Volvemos a crear 3 citas pllanificadas, las 2 primeras del mismo cliente
        Appointment ap1= new Appointment(12,14,"123");
        Appointment ap2= new Appointment(13,14,"123");
        Appointment ap3= new Appointment (13,14,"1234");

        //when
        appointmentRepository.save(ap1);
        appointmentRepository.save(ap2);
        appointmentRepository.save(ap3);
        //then
        verify(getEligibleAppointmentsForExchangeTest(););
    }


    /**
     * Igual que antes, pero ahora las 3 citas tienen que tener
     * clientes diferentes y 2 de ellas con fecha de inicio (start)
     * antes de la especificada en el método de búsqueda para
     * findExchangeRequestedWithStartBefore
     */
    @Test
    void findExchangeRequestedWithStartBeforeTest() {
        //given
        //Volvemos a crear 3 citas pllanificadas, todas diferentes (Me falla por le constructor)
        Appointment ap1= new Appointment(12,14,"123");
        Appointment ap2= new Appointment(13,14,"1423");
        Appointment ap3= new Appointment (16,20,"1254");

        //when  Cuando los guardamos
        Appointment.save(ap1);
        Appointment.save(ap2);
        Appointment.save(ap3);

        //then  Deberia aplicarse el mentodo indicado abajo
        findExchangeRequestedWithStartBeforeTest();

    }
}
