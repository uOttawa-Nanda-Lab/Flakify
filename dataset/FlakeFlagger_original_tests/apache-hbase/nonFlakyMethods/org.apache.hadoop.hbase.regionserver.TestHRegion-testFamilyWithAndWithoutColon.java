public void testFamilyWithAndWithoutColon() throws Exception {
  byte[] b=Bytes.toBytes(getName());
  byte[] cf=Bytes.toBytes("cf");
  initHRegion(b,getName(),cf);
  Put p=new Put(b);
  byte[] cfwithcolon=Bytes.toBytes("cf:");
  p.add(cfwithcolon,cfwithcolon,cfwithcolon);
  boolean exception=false;
  try {
    this.region.put(p);
  }
 catch (  NoSuchColumnFamilyException e) {
    exception=true;
  }
  assertTrue(exception);
}
