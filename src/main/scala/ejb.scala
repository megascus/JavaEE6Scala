package megascus.ejb
 
import javax.ejb._
import javax.persistence._
import megascus.model._
 
@Stateless
@LocalBean
class BookEjb extends CrudEjb[Book]
 
/** Provides basic CRUD support using an injected JPA entity manager. */
trait CrudEjb[E] {
  @PersistenceContext(unitName="default")
  protected var manager: EntityManager = _
 
  def create(entity: E): E = {
    manager persist entity
    entity
  }
 
  def readAll()(implicit m: Manifest[E]) = manager createNamedQuery ("findAll" + m.erasure.getSimpleName) getResultList
 
  def read(id: Long)(implicit manifest: Manifest[E]): E = manager.find(manifest.erasure, id).asInstanceOf[E]
 
  def update(entity: E): E = manager merge entity
 
  def delete(entity: E) { manager remove entity }
}