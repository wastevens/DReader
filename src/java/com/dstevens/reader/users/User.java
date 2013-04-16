package com.dstevens.reader.users;

public class User {

    private String id;
    private String email;
    
    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }
    
    public String getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            User that = (User) o;
            return this.id.equals(that.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return "User[id: " + id +", email: " + email +"]";
    }
}
