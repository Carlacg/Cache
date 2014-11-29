
package Cache;

public class DuplicatedObjectException extends Exception{
    
    public DuplicatedObjectException(){
        super("El objeto que se intenta ingresar ya existe en la memoria");
    }
}
