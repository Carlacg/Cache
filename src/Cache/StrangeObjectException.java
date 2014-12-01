
package Cache;

import org.apache.jcs.access.exception.CacheException;

public class StrangeObjectException extends Exception{

    public StrangeObjectException() {
        super("Objeto desconocido, el objeto que quiere no se encuentra actualmente en la memoria");
    }
    
    
}
