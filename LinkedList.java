import java.util.Iterator;

public class LinkedList<E> implements Lista<E>{
    
    private class Nodo <E> {
        private Nodo <E> siguiente = null;
        private E info= null;

        Nodo (Nodo <E> siguiente, E info){
            this.siguiente = siguiente;
            this.info = info;
        }
        public Nodo<E> getSiguiente(){
            return siguiente;
        }

        public void setSiguiente(Nodo<E> siguiente){
            this.siguiente = siguiente;
        }

        public E getInfo(){
            return info;
        }

        public void setInfo(E info){
            this.info= info;
        }
    }

    private Nodo<E> primero = null;
    private Nodo<E> ultimo = null;
    private int tamanio=0;

    @Override
    public void agregarElemento(E e){
        agregarFinal(e);
    }

    @Override
    public void agregarInicio(E e){
        
        Nodo<E> aux = new Nodo<> (primero, e);
        primero = aux;
        if(ultimo == null){
            ultimo = primero;
        }
        tamanio++;
    }

    @Override
    public void agregarFinal(E e){
        Nodo<E> aux = new Nodo<>(null, e);
        if (esVacia()) {
            primero = aux;
            ultimo = aux;
        } else{
            ultimo.setSiguiente(aux);
            ultimo = aux;
        }
        tamanio++;
    }

    @Override
    public void agregarPosicion(E e, int posicion){
        if (posicion < 0 || posicion > tamanio) {
            throw new IndexOutOfBoundsException();
        }
        if (posicion == 0) {
            agregarInicio(e);
        } else if (posicion == tamanio) {
            agregarFinal(e);
        } else {
            Nodo<E> actual = primero;
            for(int i = 0; i < posicion - 1; i++){
                actual = actual.getSiguiente();
            }
            Nodo<E> aux= new Nodo<>(actual.getSiguiente(), e);
            actual.setSiguiente(aux);
            tamanio++;
        }
    }

    @Override
    public E eliminarElemento(){
        return eliminarElementoFinal();
    }

    @Override
    public E eliminarElementoInicio(){
        if (esVacia()) return null;
        E infoEliminada= primero.getInfo();
        primero = primero.getSiguiente();
        tamanio--;
        if (esVacia()) {
            ultimo = null;
        }
        return infoEliminada;           
    }

    
}