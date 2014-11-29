package Cache;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

public class DreamTeamCache {

    private JCS jcsCache;
    private static DreamTeamCache instance;
   
    private DreamTeamCache(){
        
    }
    /**
     * Método que carga la onfiguración de la cache.
     * @throws FileConfigurationException 
     */
    public void configLoad() throws FileConfigurationException {
        try {
            jcsCache = JCS.getInstance("mvcCache");
        } catch (CacheException ex) {
            throw new FileConfigurationException();
        }
    }
    
    /**
     * Singleton
     * @return instancia de DreamTeamCache
     */
    public static DreamTeamCache getInstance(){
        if (instance == null ){
            return new DreamTeamCache();
        }else{
            return instance;
        }
    }

    /**
     * Método que mete los objetos a la cache.
     * @param objeto el objeto que se quiere almacenar
     * @throws Cache.DuplicatedObjectException
     */
    public void put(InterfazCache objeto) throws DuplicatedObjectException {
        try {
            jcsCache.put(objeto.getId(), objeto);
        } catch (CacheException ex) {
            throw new DuplicatedObjectException();
        }

    }

    /**
     * Método que devuelve de la cache el objeto que se desea, a partir del
     * id que se le pase como parámetro
     * @param id es el identificador del objeto que se desea obtener.
     * @return el objeto que se obtuvo.
     * @throws StrangeObjectException
     */
    public Object get(int id) throws StrangeObjectException{
        return jcsCache.get(id);
    }

    /**
     * Método que elimina el objeto que se desea de la cache, a partir del id
     * que se le pase como parámetro.
     * @param id
     * @throws StrangeObjectException 
     */
    public void delete(int id)throws StrangeObjectException{
        try {
            jcsCache.remove(id);
        } catch (CacheException ex) {
            Logger.getLogger(DreamTeamCache.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
