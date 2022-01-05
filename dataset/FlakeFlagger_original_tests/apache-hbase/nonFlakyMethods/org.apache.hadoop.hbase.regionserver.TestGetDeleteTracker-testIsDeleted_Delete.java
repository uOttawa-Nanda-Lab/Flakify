public void testIsDeleted_Delete(){
  List<Delete> dels=new ArrayList<Delete>();
  dels.add(del21);
  for (  Delete del : dels) {
    dt.add(del.buffer,del.qualifierOffset,del.qualifierLength,del.timestamp,del.type);
  }
  dt.update();
  assertEquals(true,dt.isDeleted(col2,0,col2Len,ts2));
}
