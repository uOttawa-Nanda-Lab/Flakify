/** 
 * Test if a KeyValue is in the lists of deletes already. Cases that needs to be tested are: Not deleted Deleted by a Delete Deleted by a DeleteColumn Deleted by a DeleteFamily
 */
public void testIsDeleted_NotDeleted(){
  List<Delete> dels=new ArrayList<Delete>();
  dels.add(delQf10);
  dels.add(del21);
  for (  Delete del : dels) {
    dt.add(del.buffer,del.qualifierOffset,del.qualifierLength,del.timestamp,del.type);
  }
  dt.update();
  assertEquals(false,dt.isDeleted(col2,0,col2Len,ts3));
  assertEquals(false,dt.isDeleted(col2,0,col2Len,ts1));
}
