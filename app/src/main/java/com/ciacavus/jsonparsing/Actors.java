package com.ciacavus.jsonparsing;

/**
 * Created by ciaran on 25/05/2016.
 */
public class Actors {

    private String name;
    private String desc;
    private String dob;
    private String country;
    private String height;
    private String spouse;
    private String children;
    private String image;

    public Actors(String name, String desc,String dob,
                  String country,String height,String spouse,String children,String image){
        super();

        this.name = name;
        this.desc = desc;
        this.dob = dob;
        this.country = country;
        this.height = height;
        this.spouse = spouse;
        this.children = children;
        this.image = image;



    }

    //individual function for getting and setting values for each information bit
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDesc()
    {
        return name;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public String getDob()
    {
        return dob;
    }

    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getHeight()
    {
        return height;
    }

    public void setHeight(String height)
    {
        this.height = height;
    }

    public String getSpouse()
    {
        return spouse;
    }

    public void setSpouse(String spouse)
    {
        this.spouse = spouse;
    }

    public String getChildren()
    {
        return children;
    }

    public void setChildren(String children)
    {
        this.children = children;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
}
