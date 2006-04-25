
package org.sakaiproject.component.osid.logging;

/**
 * 
 * @inheritDoc
 *
 */
public class EntryIterator implements org.osid.logging.EntryIterator
{
  private java.util.Vector vector = new java.util.Vector();
  int i = 0;

  public EntryIterator(java.util.Vector vector)
      throws org.osid.logging.LoggingException
  {
    this.vector = vector;
  }

  public boolean hasNextEntry() throws org.osid.logging.LoggingException
  {
    return i < vector.size();
  }

  public org.osid.logging.Entry nextEntry()
      throws org.osid.logging.LoggingException
  {
    if (i < vector.size())
    {
      return (Entry) vector.elementAt(i++);
    }
    else
    {
      throw new org.osid.logging.LoggingException(
          org.osid.logging.LoggingException.NO_MORE_ITERATOR_ELEMENTS);
    }
  }
}
