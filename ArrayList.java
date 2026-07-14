import java.util.Iterator;

public class ArrayList<E> implements Lista<E> {

    private static final int MAX = 5;
    private int indice = 0;
    private Object[] datos = null; 

    public ArrayList() {
        this(MAX);
    }

    public ArrayList(int tam) {
        if (tam < 0) {
            throw new IllegalArgumentException();
        }
        datos = new Object[tam];
    }

    private void asegurarGC() {
        for (int i = 0; i < datos.length; i++) {
            datos[i] = null;
        }
    }

    // --- MÉTODOS AGREGADOS ---
    @Override
    public void agregarElemento(E e) {
        agregarFinal(e);
    }

    @Override
    public void agregarPosicion(E e, int posicion) {
        if (posicion < 0 || posicion > indice) {
            throw new IndexOutOfBoundsException();
        }
        if (indice >= datos.length) {
            Object[] aux = new Object[datos.length + datos.length / 2 + 1];
            System.arraycopy(datos, 0, aux, 0, posicion);
            System.arraycopy(datos, posicion, aux, posicion + 1, indice - posicion);
            asegurarGC();
            datos = aux;
        } else {
            System.arraycopy(datos, posicion, datos, posicion + 1, indice - posicion);
        }
        datos[posicion] = e;
        indice++;
    }

    @Override
    public E eliminarElemento() {
        return eliminarElementoFinal();
    }

    @Override
    public E eliminarElementoInicio() {
        if (esVacia()) return null;
        @SuppressWarnings("unchecked")
        E eliminado = (E) datos[0];
        System.arraycopy(datos, 1, datos, 0, indice - 1);
        indice--;
        datos[indice] = null;
        return eliminado;
    }

    @Override
    public E eliminarElementoFinal() {
        if (esVacia()) return null;
        indice--;
        @SuppressWarnings("unchecked")
        E eliminado = (E) datos[indice];
        datos[indice] = null;
        return eliminado;
    }

    @Override
    public E eliminarElementoPosicion(int posicion) {
        if (posicion < 0 || posicion >= indice) {
            throw new IndexOutOfBoundsException();
        }
        @SuppressWarnings("unchecked")
        E eliminado = (E) datos[posicion];
        System.arraycopy(datos, posicion + 1, datos, posicion, indice - posicion - 1);
        indice--;
        datos[indice] = null;
        return eliminado;
    }

    @Override
    public E consultar(int posicion) {
        if (posicion < 0 || posicion >= indice) {
            throw new IndexOutOfBoundsException();
        }
        @SuppressWarnings("unchecked")
        E elemento = (E) datos[posicion];
        return elemento;
    }
    // ------------------------------------------------------

    @Override
    public void agregarFinal(E e) {
        Object[] aux = null;
        if (!(indice < datos.length - 1)) {
            aux = new Object[datos.length + datos.length / 2 + 1];
            System.arraycopy(datos, 0, aux, 0, datos.length);
            asegurarGC();
            datos = aux;
        }
        datos[indice] = e;
        indice++;
    }

    @Override
    public void agregarInicio(E e) {
        Object[] auxobj = null;
        if (indice < datos.length - 1) {
            System.arraycopy(datos, 0, datos, 1, indice + 1);
        } else {
            auxobj = new Object[datos.length + datos.length / 2 + 1];
            System.arraycopy(datos, 0, auxobj, 1, datos.length);
            asegurarGC();
            datos = auxobj;
        }
        datos[0] = e;
        indice++;
    }

    @Override
    public boolean esVacia() {
        return indice == 0;
    }

    @Override
    public int numElementos() {
        return indice;
    }

    @Override
    public void limpiarLista() {
        indice = 0;
        asegurarGC();
    }
/*
    @SuppressWarnings("unchecked")
    @Override
    public E[] convertirArreglo() {
        Object[] aux = new Object[indice];
        System.arraycopy(datos, 0, aux, 0, indice);
        return (E[]) aux;
    }
*/
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = 0;

            @Override
            public boolean hasNext() {
                return i < indice;
            }

            @Override
            public E next() {
                @SuppressWarnings("unchecked")
                E aux = (E) datos[i];
                i++;
                return aux;
            }
        };
    }
}
