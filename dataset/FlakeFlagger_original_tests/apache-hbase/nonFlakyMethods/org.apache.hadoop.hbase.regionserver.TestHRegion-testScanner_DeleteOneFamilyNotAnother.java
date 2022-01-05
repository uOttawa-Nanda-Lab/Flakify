public void testScanner_DeleteOneFamilyNotAnother() throws IOException {
  byte[] tableName=Bytes.toBytes("test_table");
  byte[] fam1=Bytes.toBytes("columnA");
  byte[] fam2=Bytes.toBytes("columnB");
  initHRegion(tableName,getName(),fam1,fam2);
  byte[] rowA=Bytes.toBytes("rowA");
  byte[] rowB=Bytes.toBytes("rowB");
  byte[] value=Bytes.toBytes("value");
  Delete delete=new Delete(rowA);
  delete.deleteFamily(fam1);
  region.delete(delete,null,true);
  Put put=new Put(rowA);
  put.add(fam2,null,value);
  region.put(put);
  put=new Put(rowB);
  put.add(fam1,null,value);
  put.add(fam2,null,value);
  region.put(put);
  Scan scan=new Scan();
  scan.addFamily(fam1).addFamily(fam2);
  InternalScanner s=region.getScanner(scan);
  List<KeyValue> results=new ArrayList<KeyValue>();
  s.next(results);
  assertTrue(Bytes.equals(rowA,results.get(0).getRow()));
  results.clear();
  s.next(results);
  assertTrue(Bytes.equals(rowB,results.get(0).getRow()));
}
