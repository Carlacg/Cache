package Cache;

import java.util.ArrayList;
import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;

public class DreamTeamCache {

    private JCS jcsCache;
    private static DreamTeamCache instance;

    private DreamTeamCache() {

    }

    /**
     * Método que carga la onfiguración de la cache.
     *
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
     *
     * @return instancia de DreamTeamCache
     */
    public static DreamTeamCache getInstance() {
        if (instance == null) {
            return new DreamTeamCache();
        } else {
            return instance;
        }
    }

    /**
     * Método que mete los objetos a la cache.
     *
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
     * Método que devuelve de la cache el objeto que se desea, a partir del id
     * que se le pase como parámetro
     *
     * @param id es el identificador del objeto que se desea obtener.
     * @return el objeto que se obtuvo.
     * @throws StrangeObjectException
     */
    public InterfazCache get(int id) throws StrangeObjectException {
        return (InterfazCache) jcsCache.get(id);
    }

    /**
     * Método que elimina el objeto que se desea de la cache, a partir del id
     * que se le pase como parámetro.
     *
     * @param id
     * @throws StrangeObjectException
     */
    public void delete(int id) throws StrangeObjectException {
        try {
            jcsCache.remove(id);
        } catch (CacheException ex) {
            throw new StrangeObjectException();
        }
    }

    /**
     * Método encargado de limpiar completamente la cache.
     *
     * @throws org.apache.jcs.access.exception.CacheException
     */
    public void clean() throws CacheException {
        jcsCache.clear();
    }

    /**
     * Método que recorre la caché del índice inicio hasta el índice fin;
     * convirtiendo los objetos encontrados a tipo Cacheable.
     *
     * @param inicio, es el índice desde donde empezará a recorrer la caché.
     * @param fin, es el índice donde terminará de recorrer la caché.
     * @return una lista de los objetos en esa porción de la caché.
     * @throws Cache.StrangeObjectException
     */
    public ArrayList getList(int inicio, int fin) throws StrangeObjectException {
        ArrayList arreglo = new ArrayList<>();
        for (int i = inicio; i <= fin; i++) {
            arreglo.add(get(i));
        }
        return arreglo;
    }

    /**
     * Método que verifica si un objeto se encuentra en la caché
     *     
* @param id el identificador asociado al objeto que se desee encontrar
     * @return true si el objeto existe, false si no existe
     */
    public boolean existenciaDeObjeto(int id) {
        InterfazCache object;
        object = (InterfazCache) jcsCache.get(id);
        return object != null;
    }
}
