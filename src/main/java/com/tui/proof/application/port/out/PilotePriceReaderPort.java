package com.tui.proof.application.port.out;

/** Manages reader operations for price per pilotes in infraestructure package */
public interface PilotePriceReaderPort {

    /**
     * Gets the price defined in the system
     *
     * @return the price per pilote
     */
    public double getPrice();
}
