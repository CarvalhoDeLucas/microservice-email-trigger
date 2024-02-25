package com.carvalho.eventsmicroservice.repositories;

import com.carvalho.eventsmicroservice.domain.Event;
import jakarta.annotation.Nonnull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, String> {

    @Query(value = "select * from event e where parsedatetime(e.date, 'dd/MM/yyyy') > :currentDate", nativeQuery = true)
    List<Event> findUpcomingEvents(@Param("currentDate")LocalDateTime currentDate);

    /*
    * Como a estrategia de geracao foi com UUID e o tipo esta como string
    * e preciso fazer essa chamada para busca por id usando string e nao
    * UUID
    * */
    @Nonnull
    Optional<Event> findById(@Nonnull String id);

}
