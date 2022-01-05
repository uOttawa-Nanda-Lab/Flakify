public void testStackOverflow(){
  int maxVersions=1;
  ColumnTracker wild=new WildcardColumnTracker(maxVersions);
  for (int i=0; i < 100000; i+=2) {
    byte[] col=Bytes.toBytes("col" + i);
    wild.checkColumn(col,0,col.length);
  }
  wild.update();
  for (int i=1; i < 100000; i+=2) {
    byte[] col=Bytes.toBytes("col" + i);
    wild.checkColumn(col,0,col.length);
  }
}
