public void testDelete_CheckFamily() throws IOException {
  byte[] tableName=Bytes.toBytes("testtable");
  byte[] row1=Bytes.toBytes("row1");
  byte[] fam1=Bytes.toBytes("fam1");
  byte[] fam2=Bytes.toBytes("fam2");
  byte[] fam3=Bytes.toBytes("fam3");
  byte[] fam4=Bytes.toBytes("fam4");
  byte[][] families={fam1,fam2,fam3};
  String method=this.getName();
  initHRegion(tableName,method,families);
  List<KeyValue> kvs=new ArrayList<KeyValue>();
  kvs.add(new KeyValue(row1,fam4,null,null));
  byte[] family=fam2;
  try {
    Map<byte[],List<KeyValue>> deleteMap=new HashMap<byte[],List<KeyValue>>();
    deleteMap.put(family,kvs);
    region.delete(deleteMap,true);
  }
 catch (  Exception e) {
    assertTrue("Family " + new String(family) + " does not exist",false);
  }
  boolean ok=false;
  family=fam4;
  try {
    Map<byte[],List<KeyValue>> deleteMap=new HashMap<byte[],List<KeyValue>>();
    deleteMap.put(family,kvs);
    region.delete(deleteMap,true);
  }
 catch (  Exception e) {
    ok=true;
  }
  assertEquals("Family " + new String(family) + " does exist",true,ok);
}
