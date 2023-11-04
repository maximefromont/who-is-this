package com.app.wit.Model;

import javafx.scene.paint.Color;

import java.io.File;
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
                     Hair hair,
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
        _hair = hair;
        _hair_color = hair_color;
        _eyes_color = eyes_color;
        _gender = gender;
        _hat = hat;
        _holding_something = holding_something;
        _top_color = top_color;
        _displayed_as_kid = displayed_as_kid;
        _displayed_as_smiling = displayed_as_smiling;
    }

    //PUBLIC STATIC METHODS
    public static void loadCharactersFromFile(File file) {
        //TODO
    }

    //PRIVATE ATTRIBUTES
    private String _name;
    private String _firstname;
    private Date _birthdate;
    private String _university_city;
    private boolean _glasses;
    private boolean _mustache;
    private boolean _beard;
    private Hair _hair;
    private String _hair_color;
    private String _eyes_color;
    private String _gender;
    private boolean _hat;
    private boolean _holding_something;
    private String _top_color;
    private boolean _displayed_as_kid;
    private boolean _displayed_as_smiling;
}
