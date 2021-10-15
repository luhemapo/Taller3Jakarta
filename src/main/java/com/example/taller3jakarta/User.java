package com.example.taller3jakarta;

public class User {
    String email;
    String pet;
    String date;
    String pic;
    String ruta;

    public User(String date, String userName, String pet, String pic, String ruta){
        this.email=userName;
        this.date=date;
        this.pet=pet;
        this.pic=pic;
        this.ruta=ruta;
    }

    public String toString(){
        return "Email: "+email+" date: "+date+" pet: "+pet+" pic: "+pic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

}
