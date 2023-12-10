package sv.edu.udb.www.Recursos.Models;
import java.sql.Date;

public class RecursoDigital extends Recursos {
    private String genero;

    public RecursoDigital(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante);
        this.genero = genero;
    }
    public RecursoDigital(String titulo, Date fechaPublicacion, int stock, String nombreEstante, String genero) {
        super(titulo, fechaPublicacion, stock, nombreEstante);
        this.genero = genero;
    }
    public RecursoDigital(){

    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}

