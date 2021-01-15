package com.sparta.ahmed.framework.dtos;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.ahmed.framework.Injector;

import java.util.*;

public class PersonDTO implements StarWarsDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("height")
    private String height;
    @JsonProperty("mass")
    private String mass;
    @JsonProperty("hair_color")
    private String hairColor;
    @JsonProperty("skin_color")
    private String skinColor;
    @JsonProperty("eye_color")
    private String eyeColor;
    @JsonProperty("birth_year")
    private String birthYear;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("homeworld")
    private String homeworld;
    @JsonProperty("films")
    private List<String> films = null;
    @JsonProperty("species")
    private List<Object> species = null;
    @JsonProperty("vehicles")
    private List<String> vehicles = null;
    @JsonProperty("starships")
    private List<String> starships = null;
    @JsonProperty("created")
    private String created;
    @JsonProperty("edited")
    private String edited;
    @JsonProperty("url")
    private String url;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public PersonDTO() {
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("height")
    public String getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(String height) {
        this.height = height;
    }

    @JsonProperty("mass")
    public String getMass() {
        return mass;
    }

    @JsonProperty("mass")
    public void setMass(String mass) {
        this.mass = mass;
    }

    @JsonProperty("hair_color")
    public String getHairColor() {
        return hairColor;
    }

    @JsonProperty("hair_color")
    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    @JsonProperty("skin_color")
    public String getSkinColor() {
        return skinColor;
    }

    @JsonProperty("skin_color")
    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    @JsonProperty("eye_color")
    public String getEyeColor() {
        return eyeColor;
    }

    @JsonProperty("eye_color")
    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    @JsonProperty("birth_year")
    public String getBirthYear() {
        return birthYear;
    }

    @JsonProperty("birth_year")
    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("homeworld")
    public String getHomeworld() {
        return homeworld;
    }

    @JsonProperty("homeworld")
    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    @JsonProperty("films")
    public List<String> getFilms() {
        return films;
    }

    @JsonProperty("films")
    public void setFilms(List<String> films) {
        this.films = films;
    }

    @JsonProperty("species")
    public List<Object> getSpecies() {
        return species;
    }

    @JsonProperty("species")
    public void setSpecies(List<Object> species) {
        this.species = species;
    }

    @JsonProperty("vehicles")
    public List<String> getVehicles() {
        return vehicles;
    }

    @JsonProperty("vehicles")
    public void setVehicles(List<String> vehicles) {
        this.vehicles = vehicles;
    }

    @JsonProperty("starships")
    public List<String> getStarships() {
        return starships;
    }

    @JsonProperty("starships")
    public void setStarships(List<String> starships) {
        this.starships = starships;
    }

    @JsonProperty("created")
    public String getCreated() {
        return created;
    }

    @JsonProperty("created")
    public void setCreated(String created) {
        this.created = created;
    }

    @JsonProperty("edited")
    public String getEdited() {
        return edited;
    }

    @JsonProperty("edited")
    public void setEdited(String edited) {
        this.edited = edited;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public boolean hasName() {
        return !name.isEmpty();
    }

    public boolean hasNameFormatted() {
        return Character.isUpperCase(name.charAt(0));
    }

    public boolean hasHeight() {
        return !height.isEmpty();
    }

    public boolean hasMass() {
        return !mass.isEmpty();
    }

    public boolean hasHairColor() {
        return !hairColor.isEmpty();
    }

    public boolean hasSkinColor() {
        return !skinColor.isEmpty();
    }

    public boolean hasEyeColor() {
        return !eyeColor.isEmpty();
    }

    public boolean hasBirthYear() {
        return !birthYear.isEmpty();
    }

    public boolean hasGender() {
        return !gender.isEmpty();
    }

    public boolean hasHomeworld() {
        return !homeworld.isEmpty();
    }

    public boolean hasOneFilmMinimum() {
        return films.size() >= 1;
    }


    public ArrayList<FilmsDTO> convertFilmsListToDTO(List<String> Films) {
        FilmsDTO filmsDTO;
        String endpoint = "";
        ArrayList<FilmsDTO> filmsDTOS = new ArrayList<>();
        for (String url : Films) {
            String[] splitUrl = url.split("/");
            //System.out.println(Arrays.toString(splitUrl));
            endpoint = splitUrl[4] + "/" + splitUrl[5] + "/";
            filmsDTO = (FilmsDTO) Injector.injectDTO("https://swapi.dev/api/", endpoint);
            filmsDTOS.add(filmsDTO);
            //System.out.println(filmsDTOS.toString());
        }
        return filmsDTOS;
    }

    public ArrayList<VehiclesDTO> convertVehiclesListToDTO(List<String> Vehicles) {
        VehiclesDTO vehiclesDTO;
        String endpoint = "";
        ArrayList<VehiclesDTO> vehiclesDTOS = new ArrayList<>();
        for (String url : Vehicles) {
            String[] splitUrl = url.split("/");
            //System.out.println(Arrays.toString(splitUrl));
            endpoint = splitUrl[4] + "/" + splitUrl[5] + "/";
            vehiclesDTO = (VehiclesDTO) Injector.injectDTO("https://swapi.dev/api/", endpoint);
            vehiclesDTOS.add(vehiclesDTO);
            //System.out.println(filmsDTOS.toString());
        }
        return vehiclesDTOS;
    }

    public ArrayList<StarshipsDTO> convertStarshipsListToDTO(List<String> Starships) {
        StarshipsDTO starshipsDTO;
        String endpoint = "";
        ArrayList<StarshipsDTO> starshipsDTOS = new ArrayList<>();
        for (String url : Starships) {
            String[] splitUrl = url.split("/");
            //System.out.println(Arrays.toString(splitUrl));
            endpoint = splitUrl[4] + "/" + splitUrl[5] + "/";
            starshipsDTO = (StarshipsDTO) Injector.injectDTO("https://swapi.dev/api/", endpoint);
            starshipsDTOS.add(starshipsDTO);
            //System.out.println(filmsDTOS.toString());
        }
        return starshipsDTOS;
    }

    public ArrayList<SpeciesDTO> convertSpeciesListToDTO(List<String> Species) {
        SpeciesDTO speciesDTO;
        String endpoint = "";
        ArrayList<SpeciesDTO> speciesDTOS = new ArrayList<>();
        for (String url : Species) {
            String[] splitUrl = url.split("/");
            //System.out.println(Arrays.toString(splitUrl));
            endpoint = splitUrl[4] + "/" + splitUrl[5] + "/";
            speciesDTO = (SpeciesDTO) Injector.injectDTO("https://swapi.dev/api/", endpoint);
            speciesDTOS.add(speciesDTO);
            //System.out.println(filmsDTOS.toString());
        }
        return speciesDTOS;
    }

    public PlanetsDTO convertPlanetStringToDTO(String planet) {
        PlanetsDTO planetsDTO;
        String endpoint = "";
        String[] splitUrl = url.split("/");
        endpoint = splitUrl[4] + "/" + splitUrl[5] + "/";
        planetsDTO = (PlanetsDTO) Injector.injectDTO("https://swapi.dev/api/", endpoint);
        return planetsDTO;
    }


}
