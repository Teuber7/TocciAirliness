
import java.util.ArrayList;

public interface Gestionable<T> {
    void ver(ArrayList<T> lista);
    void agregar(T item);
    void editar(int index, T item);
    void eliminar(int index, ArrayList<T> lista);
}