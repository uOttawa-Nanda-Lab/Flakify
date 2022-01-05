public void testStackOverflow(){
  List<Delete> dels=new ArrayList<Delete>();
  Delete adel=new Delete(col1,0,col1Len,del,0L);
  for (long i=0; i < 9000; i++) {
    dt.add(adel.buffer,adel.qualifierOffset,adel.qualifierLength,i,adel.type);
  }
  dt.update();
  assertEquals(false,dt.isDeleted(col2,0,col2Len,7000000));
}
