package ejerexam;

public class Libro {
    private int id;
    private String titulo;

    public Libro(int id, String title) {
        this.id = id;
        this.titulo = title;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getitle() {
        return titulo;
    }

    public void setitle(String title) {
        titulo = title;
    }

}