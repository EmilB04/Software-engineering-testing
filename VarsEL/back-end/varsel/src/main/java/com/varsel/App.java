package com.varsel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // Under er kode som tester registrering og innlogging ved hjelp av et enkelt brukergrensesnitt.
        // Det lager en tekstfil som lagrer input email og passord(kryptert), og leser input fra bruker
        // dersom man ønsker å logge inn og sjekker om det er likt noe av det som er lagret i filen.
        /*
        UserRepository userRepository = new UserRepository(FILE_PATH);
        UserService userService = new UserService(userRepository);
        LoginMenu loginMenu = new LoginMenu(userService);

        loginMenu.start();  // Starter menyen
         */
    }
}
