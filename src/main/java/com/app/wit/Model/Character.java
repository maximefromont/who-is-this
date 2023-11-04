package com.app.wit.Model;

import com.app.wit.Application;
import javafx.scene.paint.Color;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Character {

    //PUBLIC METHODS
    public Character(String name,
                     String firstname,
                     Date birthdate,
                     String university_city,
                     boolean glasses,
                     boolean mustache,
                     boolean beard,
                     String hair_type,
                     String hair_color,
                     String eyes_color,
                     String gender,
                     boolean hat,
                     boolean holding_something,
                     String top_color,
                     boolean displayed_as_kid,
                     boolean displayed_as_smiling)
    {
        _name = name;
        _firstname = firstname;
        _birthdate = birthdate;
        _university_city = university_city;
        _glasses = glasses;
        _mustache = mustache;
        _beard = beard;
        _hair_type = hair_type;
        _hair_color = hair_color;
        _eyes_color = eyes_color;
        _gender = gender;
        _hat = hat;
        _holding_something = holding_something;
        _top_color = top_color;
        _displayed_as_kid = displayed_as_kid;
        _displayed_as_smiling = displayed_as_smiling;
    }

    public String getName() {
        return _name;
    }

    public String getFirstname() {
        return _firstname;
    }

    public Date getBirthdate() {
        return _birthdate;
    }

    public String getUniversityCity() {
        return _university_city;
    }

    public boolean hasGlasses() {
        return _glasses;
    }

    public boolean hasMustache() {
        return _mustache;
    }

    public boolean hasBeard() {
        return _beard;
    }

    public String getHairType() {
        return _hair_type;
    }

    public String getHairColor() {
        return _hair_color;
    }

    public String getEyesColor() {
        return _eyes_color;
    }

    public String getGender() {
        return _gender;
    }

    public boolean hasHat() {
        return _hat;
    }

    public boolean isHoldingSomething() {
        return _holding_something;
    }

    public String getTopColor() {
        return _top_color;
    }

    public boolean isDisplayedAsKid() {
        return _displayed_as_kid;
    }

    public boolean isDisplayedAsSmiling() {
        return _displayed_as_smiling;
    }

    //PUBLIC STATIC METHODS
    public static ArrayList<Character> loadCharactersFromFile(File file) {
        ArrayList<Character> characters_list = new ArrayList<Character>();
        File game_data_file = Application.getGameDataFile();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Application.getGameDataFile()));

            String line;
            boolean firstLine = true; //Important to skip the header line since it's just the columns names
            while ((line = bufferedReader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; //Skiping the header line
                }

                String[] parts = line.split(";");
                if (parts.length == 16) {
                    Character character = createCharacterFromCSV(parts);
                    characters_list.add(character);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return characters_list;
    }

    //PRIVATE STATIC METHODS
    private static Character createCharacterFromCSV(String[] data) throws ParseException {
        String name = data[0].toUpperCase();
        String firstname = data[1].toUpperCase();
        Date birthdate = new SimpleDateFormat("dd/MM/yyyy").parse(data[2]);
        String university_city = data[3].toUpperCase();
        boolean glasses = data[4].equalsIgnoreCase("O");
        boolean mustache = data[5].equalsIgnoreCase("O");
        boolean beard = data[6].equalsIgnoreCase("O");
        String hair_type = data[7].toUpperCase();
        String hair_color = data[8].toUpperCase();
        String eyes_color = data[9].toUpperCase();
        String gender = data[10].toUpperCase();
        boolean hat = data[11].equalsIgnoreCase("O");
        boolean holding_something = data[12].equalsIgnoreCase("O");
        String top_color = data[13].toUpperCase();
        boolean displayed_as_kid = data[14].equalsIgnoreCase("O");
        boolean displayed_as_smiling = data[15].equalsIgnoreCase("O");

        return new Character(name, firstname, birthdate, university_city, glasses, mustache, beard, hair_type,
                hair_color, eyes_color, gender, hat, holding_something, top_color, displayed_as_kid, displayed_as_smiling);
    }


//PRIVATE ATTRIBUTES
    private String _name;
    private String _firstname;
    private Date _birthdate;
    private String _university_city;
    private boolean _glasses;
    private boolean _mustache;
    private boolean _beard;
    private String _hair_type;
    private String _hair_color;
    private String _eyes_color;
    private String _gender;
    private boolean _hat;
    private boolean _holding_something;
    private String _top_color;
    private boolean _displayed_as_kid;
    private boolean _displayed_as_smiling;
}
