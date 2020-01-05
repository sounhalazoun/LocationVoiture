package com.sounhalazoun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LocationVoitureApplication {

    public static void main( String[] args ) {

        ApplicationContext context = SpringApplication.run(
                LocationVoitureApplication.class, args );
        /*
         * ClientRepository clientRepository = context.getBean(
         * ClientRepository.class ); VoitureRepository voitureRepository =
         * context.getBean( VoitureRepository.class ); RoleRepository
         * roleRepository = context.getBean( RoleRepository.class );
         * roleRepository.save( new Role( "ADMIN" ) ); roleRepository.save( new
         * Role( "CLIENT" ) );
         * 
         * Client client1 = new Client( "Omari", "Mounir", "123456", "AB568971",
         * "omari@gmail.com", "0661523987" ); Client client2 = new Client(
         * "Bouhlala", "Amine", "987654", "AB1542369", "bouhlala@gmail.com",
         * "0641569832" ); clientRepository.saveAll( Arrays.asList( client1,
         * client2 ) ); voitureRepository.save( new Voiture( "A-4001-20",
         * "2003", "R12", "Renault", "MoterGP", 50000, new Location( client1,
         * new Date(), 72 ), new Location( client2, new Date(), 48 ) ) );
         * 
         * voitureRepository.save( new Voiture( "B-3000-75", "2012", "passat",
         * "Golf", "GermanyMoter", 120000, new Location( client1, new Date(),
         * 420 ) ) );
         * 
         * ManagerRepository managerRepository = context.getBean(
         * ManagerRepository.class ); Manager manager1 = new Manager(
         * "Ennekhli", "Hassan", "hassan1949", "F5263459", "hassan@gmail.com",
         * "0661254879" ); managerRepository.save( manager1 );
         * voitureRepository.save( new Voiture( "A-4501-75", "2012", "Megane",
         * "Renault", "Diesel", 75000, manager1 ) );
         */
    }

}
