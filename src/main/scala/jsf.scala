/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package megascus.jsf
 
import javax.ejb._
import javax.faces.bean._
 
import megascus.model._
import megascus.ejb._

import scala.reflect._
 
@ManagedBean
@RequestScoped
class BookController {
  @EJB
  private var ejb: BookEjb = _
 
  @BeanProperty
  var book = new Book
 
  def getBookList = ejb.readAll
  
  def createBook() = {
    ejb create book
    book = new Book
    ""
  }
  
  def cancel() = {
    book = new Book
    ""
  }
}