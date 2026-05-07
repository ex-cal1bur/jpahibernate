package com.example.curso.domain;

public class User {

    private final String name;
    private final String mail;


public User(String name, String mail) {
    //VALIDACIONES Y ASIGNACIONES
  
    if (name == null || name.isEmpty()) {
        throw new IllegalArgumentException("name needs almost 1 character");} else {
            this.name = name;
        }
    if (mail == null || mail.isEmpty()) {
        throw new IllegalArgumentException("mail needs almost 1 character");}
    else if (!mail.contains("@")){
        throw new IllegalArgumentException("mail needs the '@' character");}
    else {
            this.mail = mail;
            }
        } 
        

    @Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((mail == null) ? 0 : mail.hashCode());
    return result;
}

@Override
public boolean equals(Object obj) {
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    User other = (User) obj;
    if (mail == null) {
        if (other.mail != null)
            return false;
    } else if (!mail.equals(other.mail))
        return false;
    return true;
}

//getter
public String getMail() {
    return mail;
}

public String getName() {
    return name;
}
    }

