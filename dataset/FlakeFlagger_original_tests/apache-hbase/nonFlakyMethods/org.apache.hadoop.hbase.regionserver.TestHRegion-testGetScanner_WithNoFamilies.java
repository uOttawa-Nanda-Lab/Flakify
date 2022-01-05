public void testGetScanner_WithNoFamilies() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] row1=Bytes.toBytes("row1");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] fam2=Bytes.toBytes("fam2");
  byte[] fam3=Bytes.toBytes("fam3");
  byte[] fam4=Bytes.toBytes("fam4");
  byte[][] families={fam1,fam2,fam3,fam4};
  String method=this.getName();
  initHRegion(tableName,method,families);
  Put put=new Put(row1);
  put.add(fam1,null,null);
  put.add(fam2,null,null);
  put.add(fam3,null,null);
  put.add(fam4,null,null);
  region.put(put);
  Scan scan=null;
  InternalScanner is=null;
  scan=new Scan();
  scan.addFamily(fam2);
  scan.addFamily(fam4);
  is=region.getScanner(scan);
  assertEquals(1,((RegionScanner)is).getStoreHeap().getHeap().size());
  scan=new Scan();
  is=region.getScanner(scan);
  assertEquals(families.length - 1,((RegionScanner)is).getStoreHeap().getHeap().size());
}
