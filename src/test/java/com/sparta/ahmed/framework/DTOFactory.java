package com.sparta.ahmed.framework;

import com.sparta.ahmed.framework.dtos.*;

public class DTOFactory {

    public StarWarsDTO getDTO(String endpoint) {
        if (endpoint == null) {
            return null;
        }
        if (endpoint.startsWith("people")) {
            return new PersonDTO();
        } else if (endpoint.startsWith("films")) {
            return new FilmsDTO();
        } else if (endpoint.startsWith("species")) {
            return new SpeciesDTO();
        } else if (endpoint.startsWith("starships")) {
            return new StarshipsDTO();
        } else if (endpoint.startsWith("vehicles")) {
            return new VehiclesDTO();
        } else if (endpoint.startsWith("planets")) {
            return new PlanetsDTO();
        }
        return null;
    }

}
