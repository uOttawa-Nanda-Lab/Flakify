public void testRegionScanner_Next() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] row1=Bytes.toBytes("row1");
  byte[] row2=Bytes.toBytes("row2");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] fam2=Bytes.toBytes("fam2");
  byte[] fam3=Bytes.toBytes("fam3");
  byte[] fam4=Bytes.toBytes("fam4");
  byte[][] families={fam1,fam2,fam3,fam4};
  long ts=System.currentTimeMillis();
  String method=this.getName();
  initHRegion(tableName,method,families);
  Put put=null;
  put=new Put(row1);
  put.add(fam1,null,ts,null);
  put.add(fam2,null,ts,null);
  put.add(fam3,null,ts,null);
  put.add(fam4,null,ts,null);
  region.put(put);
  put=new Put(row2);
  put.add(fam1,null,ts,null);
  put.add(fam2,null,ts,null);
  put.add(fam3,null,ts,null);
  put.add(fam4,null,ts,null);
  region.put(put);
  Scan scan=new Scan();
  scan.addFamily(fam2);
  scan.addFamily(fam4);
  InternalScanner is=region.getScanner(scan);
  List<KeyValue> res=null;
  List<KeyValue> expected1=new ArrayList<KeyValue>();
  expected1.add(new KeyValue(row1,fam2,null,ts,KeyValue.Type.Put,null));
  expected1.add(new KeyValue(row1,fam4,null,ts,KeyValue.Type.Put,null));
  res=new ArrayList<KeyValue>();
  is.next(res);
  for (int i=0; i < res.size(); i++) {
    assertEquals(expected1.get(i),res.get(i));
  }
  List<KeyValue> expected2=new ArrayList<KeyValue>();
  expected2.add(new KeyValue(row2,fam2,null,ts,KeyValue.Type.Put,null));
  expected2.add(new KeyValue(row2,fam4,null,ts,KeyValue.Type.Put,null));
  res=new ArrayList<KeyValue>();
  is.next(res);
  for (int i=0; i < res.size(); i++) {
    assertEquals(expected2.get(i),res.get(i));
  }
}
