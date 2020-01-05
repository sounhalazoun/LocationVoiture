package com.sounhalazoun.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sounhalazoun.entities.Location;
import com.sounhalazoun.entities.LocationId;
import com.sounhalazoun.entities.Manager;
import com.sounhalazoun.entities.Voiture;
import com.sounhalazoun.repositories.CategorieRepository;
import com.sounhalazoun.repositories.LocationRepository;
import com.sounhalazoun.repositories.ManagerRepository;
import com.sounhalazoun.repositories.VoitureRepository;

@Controller
@RequestMapping( value = "manager" )
public class ManagerController {
    @Autowired
    ManagerRepository   managerRepository;
    @Autowired
    VoitureRepository   voitureRepository;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    LocationRepository  locationRepository;

    @GetMapping( value = "/home" )
    public String home() {
        return "homeManager";
    }

    @GetMapping( value = "/ajouterVoiture" )
    public String ajouterVoiture( Model model ) {

        model.addAttribute( "voiture", new Voiture() );
        model.addAttribute( "categories", categorieRepository.findAll() );

        return "ajouterVoiture";
    }

    @PostMapping( value = "/ajouterVoiture" )
    public String insererVoiture( @Valid Voiture voiture, BindingResult bindingResult,
            HttpServletRequest httpServletRequest ) {

        if ( bindingResult.hasErrors() ) {
            return "ajouterVoiture";

        }

        Manager manager = managerRepository.findByEmail( httpServletRequest.getRemoteUser() );
        voiture.setManager( manager );
        voitureRepository.save( voiture );

        return "redirect:suppEditVoiture";
    }

    @GetMapping( value = "/suppEditVoiture" )
    public String suppEditVoiture( Model model, HttpServletRequest httpServletRequest ) {

        Manager manager = managerRepository.findByEmail( httpServletRequest.getRemoteUser() );
        model.addAttribute( "ListVoiture", voitureRepository.findByManager( manager ) );
        return "suppEditVoiture";
    }

    @GetMapping( value = "/editer" )
    public String editer( Model model, Long id ) {
        model.addAttribute( "voiture", voitureRepository.findById( id ) );
        model.addAttribute( "categories", categorieRepository.findAll() );

        return "editerVoiture";
    }

    @GetMapping( value = "/supprimer" )
    public String supprimer( Model model, Long id ) {
        voitureRepository.deleteById( id );
        return "redirect:suppEditVoiture";
    }

    @GetMapping( value = "/demandes" )
    public String demandes( Model model, HttpServletRequest httpServletRequest ) {
        Manager manager = managerRepository.findByEmail( httpServletRequest.getRemoteUser() );

        model.addAttribute( "demandes",
                locationRepository.findByManagerAndStatusOrderByDateDemandeAsc( manager, false ) );
        return "demandes";
    }

    @GetMapping( value = "/accepter" )
    public String accepter( Long clientId, Long voitureId ) {
        Location location = locationRepository.getOne( new LocationId( clientId, voitureId ) );
        location.setStatus( true );
        locationRepository.save( location );

        return "redirect:demandes";
    }

    @GetMapping( value = "/refuser" )
    public String refuser( Long clientId, Long voitureId,@RequestParam( "dateDebut" ) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE_TIME ) LocalDateTime dateDebut ) {
        Location location = locationRepository.findByIdAndDateDebut( new LocationId( clientId, voitureId ),dateDebut);
        locationRepository.deleteById( location.getId()  );
             
        return "demandes";
    }
}
