/** 
 * hbase-2259
 */
public void testStackOverflow(){
  int maxVersions=1;
  TreeSet<byte[]> columns=new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
  for (int i=0; i < 100000; i++) {
    columns.add(Bytes.toBytes("col" + i));
  }
  ColumnTracker explicit=new ExplicitColumnTracker(columns,maxVersions);
  for (int i=0; i < 100000; i+=2) {
    byte[] col=Bytes.toBytes("col" + i);
    explicit.checkColumn(col,0,col.length);
  }
  explicit.update();
  for (int i=1; i < 100000; i+=2) {
    byte[] col=Bytes.toBytes("col" + i);
    explicit.checkColumn(col,0,col.length);
  }
}
