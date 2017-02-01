/*
 * Java domain class for entity "Driver" 
 * Created on 2017-01-30 ( Date ISO 2017-01-30 - Time 10:21:30 )
 * Generated by Telosys Tools Generator ( version 3.0.0 )
 */
package org.demo.data.domain;

import java.io.Serializable;



/**
 * Domain class for entity "Driver"
 *
 * @author Telosys Tools Generator
 *
 */
public class Driver implements Serializable {

    private static final long serialVersionUID = 1L;

    //----------------------------------------------------------------------
    // ENTITY PRIMARY KEY 
    //----------------------------------------------------------------------
    private int        id           ;

    //----------------------------------------------------------------------
    // ENTITY DATA FIELDS 
    //----------------------------------------------------------------------    
    private String     nom          ;

    //----------------------------------------------------------------------
    // ENTITY LINKS ( RELATIONSHIP )
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // CONSTRUCTOR(S)
    //----------------------------------------------------------------------
    public Driver() {
		super();
    }
    
    //----------------------------------------------------------------------
    // GETTER & SETTER FOR "KEY FIELD(S)"
    //----------------------------------------------------------------------
    public void setId( int id ) {
        this.id = id ;
    }
    public int getId() {
        return this.id;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR "DATA FIELDS"
    //----------------------------------------------------------------------
    public void setNom( String nom ) {
        this.nom = nom ;
    }
    public String getNom() {
        return this.nom;
    }


    //----------------------------------------------------------------------
    // GETTERS & SETTERS FOR LINKS
    //----------------------------------------------------------------------

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(nom);
        return sb.toString(); 
    } 

}
