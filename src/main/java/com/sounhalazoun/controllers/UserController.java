package com.sounhalazoun.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sounhalazoun.entities.Client;
import com.sounhalazoun.entities.Manager;
import com.sounhalazoun.entities.Role;
import com.sounhalazoun.entities.User;
import com.sounhalazoun.repositories.ClientRepository;
import com.sounhalazoun.repositories.ManagerRepository;
import com.sounhalazoun.repositories.RoleRepository;
import com.sounhalazoun.repositories.UserRepository;

@Controller
@RequestMapping( value = "/user" )
public class UserController {

    @Autowired
    UserRepository    userRepository;
    @Autowired
    ClientRepository  clientRepository;
    @Autowired
    ManagerRepository managerRepository;
    @Autowired
    RoleRepository    roleRepository;

    @GetMapping( value = "/403" )
    public String accessDenied() {
        return "403";

    }

    @GetMapping( value = "/signUp" )
    public String inscrire( Model model ) {
        model.addAttribute( "client", new Client() );
        model.addAttribute( "roles", roleRepository.findAll() );
        return "inscription";
    }

    @PostMapping( value = "/signUp" )
    public String signUpPost( @Valid Client client, BindingResult bindingResult,
            @RequestParam( name = "password" ) String pass1,
            @RequestParam( name = "passwordConfirm" ) String pass2, Model model,
            @RequestParam( name = "role" ) String role ) {

        if ( bindingResult.hasErrors() ) {
            model.addAttribute( "roles", roleRepository.findAll() );
            return "inscription";
        } else if ( !pass1.equals( pass2 ) ) {
            model.addAttribute( "roles", roleRepository.findAll() );
            model.addAttribute( "passwordNotEquals", "les deux password ne sont pas égaux" );
            return "inscription";
        }

        List<Role> listR = new ArrayList<Role>();
        listR.add( new Role( role ) );

        if ( role.equals( "ADMIN" ) ) {
            User user = new User( client.getEmail(), new BCryptPasswordEncoder().encode( client.getPassword() ), 1,
                    roleRepository.findAll() );
            userRepository.save( user );

        } else if ( role.equals( "CLIENT" ) ) {
            User user = new User( client.getEmail(), new BCryptPasswordEncoder().encode( client.getPassword() ), 1,
                    listR );
            userRepository.save( user );
            clientRepository.save( client );

        } else if ( role.equals( "MANAGER" ) ) {
            User user = new User( client.getEmail(), new BCryptPasswordEncoder().encode( client.getPassword() ), 1,
                    listR );
            userRepository.save( user );
            Manager manager = new Manager( client.getNom(), client.getPrenom(), client.getPassword(), client.getCin(),
                    client.getEmail(), client.getTel() );
            managerRepository.save( manager );
        }

        return "redirect:/";

    }

    @GetMapping( value = "login" )
    public String login( @RequestParam( name = "error", defaultValue = "" ) String error, Model model ) {
        if ( error.equals( "true" ) )
            model.addAttribute( "errorMessage", "username ou password est incorrecte" );
        return "login";
    }

    @GetMapping( value = "home" )
    public String home() {

        return "homeUser";
    }

}
