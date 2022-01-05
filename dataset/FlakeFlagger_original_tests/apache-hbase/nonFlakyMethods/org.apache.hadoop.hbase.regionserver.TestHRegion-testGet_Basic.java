public void testGet_Basic() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] row1=Bytes.toBytes("row1");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] col1=Bytes.toBytes("col1");
  byte[] col2=Bytes.toBytes("col2");
  byte[] col3=Bytes.toBytes("col3");
  byte[] col4=Bytes.toBytes("col4");
  byte[] col5=Bytes.toBytes("col5");
  String method=this.getName();
  initHRegion(tableName,method,fam1);
  Put put=new Put(row1);
  put.add(fam1,col1,null);
  put.add(fam1,col2,null);
  put.add(fam1,col3,null);
  put.add(fam1,col4,null);
  put.add(fam1,col5,null);
  region.put(put);
  Get get=new Get(row1);
  get.addColumn(fam1,col2);
  get.addColumn(fam1,col4);
  KeyValue kv1=new KeyValue(row1,fam1,col2);
  KeyValue kv2=new KeyValue(row1,fam1,col4);
  KeyValue[] expected={kv1,kv2};
  Result res=region.get(get,null);
  assertEquals(expected.length,res.size());
  for (int i=0; i < res.size(); i++) {
    assertEquals(0,Bytes.compareTo(expected[i].getRow(),res.raw()[i].getRow()));
    assertEquals(0,Bytes.compareTo(expected[i].getFamily(),res.raw()[i].getFamily()));
    assertEquals(0,Bytes.compareTo(expected[i].getQualifier(),res.raw()[i].getQualifier()));
  }
  Get g=new Get(row1);
  final int count=2;
  g.setFilter(new ColumnCountGetFilter(count));
  res=region.get(g,null);
  assertEquals(count,res.size());
}
