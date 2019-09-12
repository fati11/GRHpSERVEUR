package org.sid.stage.DAO;

public class Email {
    private String email;
    private String texte;
    private String subject;

    public Email() {
    }

    public Email(String email, String texte, String subject) {
        this.email = email;
        this.texte = texte;
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
