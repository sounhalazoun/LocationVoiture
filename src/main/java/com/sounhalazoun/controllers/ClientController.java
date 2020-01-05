package com.sounhalazoun.controllers;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sounhalazoun.entities.Categorie;
import com.sounhalazoun.entities.Client;
import com.sounhalazoun.entities.Location;
import com.sounhalazoun.entities.Voiture;
import com.sounhalazoun.repositories.CategorieRepository;
import com.sounhalazoun.repositories.ClientRepository;
import com.sounhalazoun.repositories.LocationRepository;
import com.sounhalazoun.repositories.ManagerRepository;
import com.sounhalazoun.repositories.VoitureRepository;

@Controller
@RequestMapping( value = "client" )
public class ClientController {
    @Autowired
    ClientRepository    clientRepository;
    @Autowired
    VoitureRepository   voitureRepository;
    @Autowired
    LocationRepository  locationRepository;
    @Autowired
    ManagerRepository   managerRepository;
    @Autowired
    CategorieRepository categorieRepository;

    @GetMapping( value = "/home" )
    public String home() {
        return "homeClient";
    }

    @GetMapping( value = "/voitureDispo" )
    public String voitureDispo( Model model,
            @RequestParam( "dateDebut" ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dateDebut,
            @RequestParam( "dateFin" ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dateFin ) {

        List<Voiture> voitureIndispo = locationRepository.findByDateFinAfterAndDateDebutBeforeAndStatus( dateDebut, dateFin, true );
        List<Voiture> allVoitures = voitureRepository.findAll();
        Boolean changed = allVoitures.removeAll( voitureIndispo );
        if ( changed ) {
            
            model.addAttribute( "voitureDispo", allVoitures );
        } else {
            model.addAttribute( "voitureDispo", voitureRepository.findAll() );
        }
        model.addAttribute( "dateDebut", dateDebut );
        model.addAttribute( "dateFin", dateFin );
        model.addAttribute( "categories", categorieRepository.findAll() );

        return "listVoitureDispo";
    }

    @GetMapping( value = "/reserver" )
    public String reserver( @RequestParam( "id" ) Long id,
            @RequestParam( "dateDebut" ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dateDebut,
            @RequestParam( "dateFin" ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dateFin,
            HttpServletRequest httpServletRequest ) {

        Client client = clientRepository.findByEmail( httpServletRequest.getRemoteUser() );
        Voiture voiture = voitureRepository.getOne( id );

        Location location = new Location( client, voiture, dateDebut,
                dateFin, LocalDateTime.now(), voiture.getManager(), false );
        // Location location = new Location( new LocationId( client.getId(),
        // voiture.getId() ), client, voiture, dateDebut,
        // dateFin, false );

        System.out
                .println( "nom du client est : " + client.getNom() + "objet: " + client + "his Location : "
                        + client.getLocations() );
        System.out.println(
                "la marque de la voiture est : " + voiture.getMarque() + "objet: " + voiture + "his Location : "
                        + voiture.getLocations() );

        // client.getLocations().add( location );
        // voiture.getLocations().add( location );

        /*
         * Manager managerVoiture = voiture.getManager(); Location location =
         * new Location( new LocationId( client, voiture ), client, voiture,
         * dateDebut, dateFin ); // managerVoiture.getDemandes().add( location
         * ); List<Location> listLocation = new ArrayList<Location>();
         * listLocation.add( location ); managerVoiture.setDemandes(
         * listLocation );
         */
        // clientRepository.save( client );
        // voitureRepository.save( voiture );
        locationRepository.save( location );

        return "homeClient";
    }

    @GetMapping( value = "/categorie" )
    public String listCategorie( String cat, Model model ) {

        Categorie categorie = categorieRepository.findByNomCat( cat );

        List<Voiture> voitures = voitureRepository.findByCategorie( categorie );

        model.addAttribute( "listeVoitures", voitures );

        return "listPerCat";
    }
    
    @GetMapping( value = "/detailsVoiture" )
    public String detailsVoiture( Long id,@RequestParam( "dateDebut" ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dateDebut,
                                  @RequestParam( "dateFin" ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dateFin, Model model ) {

        model.addAttribute( "voiture", voitureRepository.getOne(id));
        model.addAttribute( "dateDebut", dateDebut );
        model.addAttribute( "dateFin", dateFin );
        return "detailsVoiture";
    }
    
    
    

}
