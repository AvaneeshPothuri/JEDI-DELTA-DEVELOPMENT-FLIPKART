package com.flipfit.exceptions;


public class CentreNotFoundException extends Exception{
    public CenterNotFoundException(String gymId){
        super(RED_COLOR+"Gym Centre" + gymId + " not found!"+RESET_COLOR);
    }
}
