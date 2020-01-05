package com.sounhalazoun.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sounhalazoun.entities.Location;
import com.sounhalazoun.entities.LocationId;
import com.sounhalazoun.entities.Manager;
import com.sounhalazoun.entities.Voiture;

public interface LocationRepository extends JpaRepository<Location, LocationId> {

    @Query("select l.voiture from Location l where l.dateFin>?1 and l.dateDebut<?2 and l.status=?3")
    public List<Voiture> findByDateFinAfterAndDateDebutBeforeAndStatus( LocalDateTime dateDebut, LocalDateTime dateFin,Boolean status);

    public List<Location> findByManagerAndStatusOrderByDateDemandeAsc( Manager manager, Boolean status );
    
    public Location findByIdAndDateDebut( LocationId id,LocalDateTime dateDebut);

}
