/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledb.query;

import java.util.Collection;

/**
 * 
 * @author lochga01
 */
public class RenameScan implements Scan {
    private Scan s;
    private Collection<String> fieldlist;
   
   /**
    * Creates a project scan having the specified
    * underlying scan and field list.
    * @param s the underlying scan
    * @param fieldlist the list of field names
    */
   public RenameScan(Scan s, Collection<String> fieldlist) {
      this.s = s;
      this.fieldlist = fieldlist;
   }
   
   public void beforeFirst() {
      s.beforeFirst();
   }
   
   public boolean next() {
      return s.next();
   }
   
   public void close() {
      s.close();
   }
   
   public Constant getVal(String fldname) {
       /*if (hasField(fldname) && fldname == this.oldName) {
           return s.getVal(this.newName);
       }
       else */if (hasField(fldname)) {
          return s.getVal(fldname);
      }
      else
         throw new RuntimeException("field " + fldname + " not found.");
   }
   
   public int getInt(String fldname) {
      if (hasField(fldname))
         return s.getInt(fldname);
      else
         throw new RuntimeException("field " + fldname + " not found.");
   }
   
   public String getString(String fldname) {
      if (hasField(fldname))
         return s.getString(fldname);
      else
         throw new RuntimeException("field " + fldname + " not found.");
   }
   
   /**
    * Returns true if the specified field
    * is in the projection list.
    * @see simpledb.query.Scan#hasField(java.lang.String)
    */
   public boolean hasField(String fldname) {
      return fieldlist.contains(fldname);
   }
}
