package es.urjc.code.service;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SwearingServiceImplTest {

    private SwearingService swearingService = new SwearingServiceImpl();

    @Test
    public void shouldReturnTrue() {
        String textWithSwearing = "Era un domingo en la tarde y fui a los gilipipas de choque";

        assertTrue(swearingService.checkComment(textWithSwearing));
    }

    @Test
    public void shouldReturnFalse() {
        String textWithoutSwearing = "Era un domingo en la tarde y fui a los coches de choque";

        assertFalse(swearingService.checkComment(textWithoutSwearing));
    }
}