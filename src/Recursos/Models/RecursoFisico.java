package sv.edu.udb.www.Recursos.Models;
import java.sql.Date;

public class RecursoFisico extends Recursos {
    private int numeroPaginas;

    public RecursoFisico(int id, String codigoIdentificacion, String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas) {
        super(id, codigoIdentificacion, titulo, fechaPublicacion, stock, nombreEstante);
        this.numeroPaginas = numeroPaginas;
    }
    public RecursoFisico(String titulo, Date fechaPublicacion, int stock, String nombreEstante, int numeroPaginas) {
        super(titulo, fechaPublicacion, stock, nombreEstante);
        this.numeroPaginas = numeroPaginas;
    }
    public RecursoFisico(){

    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }
}
