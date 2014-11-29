
package Cache;


import org.apache.jcs.access.exception.CacheException;

public class FileConfigurationException extends CacheException{
    
    public FileConfigurationException() {
        super("Error en el archivo de configuración");
    }
}
