package org.iesvdm.appointment.repository;


import org.assertj.core.api.AbstractBooleanArrayAssert;
import org.iesvdm.appointment.entity.Appointment;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository {

    static void add(Appointment appointment1) {
    }

    static <SELF extends AbstractBooleanArrayAssert<SELF>> AbstractBooleanArrayAssert<SELF> findAll() {
    }

    public Appointment getOne(Class<? extends Integer> appointmentId);

    public static void save(Appointment appointment);

    public List<Appointment> findCanceledByUser(int userId);

    public List<Appointment> findByCustomerIdWithStartInPeroid( int customerId, LocalDateTime startPeroid, LocalDateTime endPeroid);

    public List<Appointment> findScheduledWithEndBeforeDate( LocalDateTime now);

    public List<Appointment> getEligibleAppointmentsForExchange(LocalDateTime start, Integer customerId);

    public List<Appointment> findExchangeRequestedWithStartBefore(LocalDateTime date);

}
