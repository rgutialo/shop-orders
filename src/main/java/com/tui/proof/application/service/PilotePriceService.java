package com.tui.proof.application.service;

import com.tui.proof.application.port.in.PilotePricePort;
import com.tui.proof.application.port.out.PilotePriceReaderPort;

/** Implementation of the {@link PilotePricePort} */
public class PilotePriceService implements PilotePricePort {

    private final PilotePriceReaderPort pilotePriceReaderPort;

    public PilotePriceService(PilotePriceReaderPort pilotePriceReaderPort) {
        this.pilotePriceReaderPort = pilotePriceReaderPort;
    }

    /** {@inheritDoc} */
    @Override
    public double price() {
        return pilotePriceReaderPort.getPrice();
    }
}
