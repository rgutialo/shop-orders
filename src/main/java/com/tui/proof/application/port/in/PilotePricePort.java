package com.tui.proof.application.port.in;

/** Manages pilotes price in the model */
public interface PilotePricePort {

    /**
     * Method which obtains the pilote price
     *
     * @return price per pilote
     */
    public double price();
}
