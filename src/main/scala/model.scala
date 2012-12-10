/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package megascus.model
 
import javax.persistence._
import java.util._
import scala.reflect._

@Entity
@serializable
@NamedQuery(name = "findAllBook", query = "SELECT b FROM Book b")
class Book extends EntityBase with Title with Price with ISBN with NumberOfPage with Illustrations { 
}

/** Defines required properties and operetions. */
trait EntityBase {
  @javax.persistence.Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @BeanProperty
  var id: Long = _
  
  @Temporal(TemporalType.TIME)
  @BeanProperty
  var updateDate: Date = _
  
  @Temporal(TemporalType.TIME)
  @BeanProperty
  var createDate: Date = _
  
  @PreUpdate
  def preUpdate() = {
    updateDate = new Date()
  }
  
  @PrePersist
  def prePersist() = {
    var d = new Date()
    updateDate = d
    createDate = d
  }
}

trait Title {
  @Column(nullable = false)
  @BeanProperty
  var title: String = _
}

trait Price {
  @BeanProperty
  var price: Float = _  
}

trait ISBN {
  @BeanProperty
  var isbn: String = _  
}

trait NumberOfPage {
  @BeanProperty
  var numberOfPage: Int = _  
}

trait Illustrations {
  @BeanProperty
  var illustrations: Boolean = _
}