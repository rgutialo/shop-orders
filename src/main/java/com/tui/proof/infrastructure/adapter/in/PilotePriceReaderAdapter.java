package com.tui.proof.infrastructure.adapter.in;

import com.tui.proof.application.port.out.PilotePriceReaderPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/** Implementation of the {@link PilotePriceReaderPort} */
@Configuration
public class PilotePriceReaderAdapter implements PilotePriceReaderPort {

    @Value("${pilote.price}")
    private double price;

    /** {@inheritDoc} */
    @Override
    public double getPrice() {
        return price;
    }
}
