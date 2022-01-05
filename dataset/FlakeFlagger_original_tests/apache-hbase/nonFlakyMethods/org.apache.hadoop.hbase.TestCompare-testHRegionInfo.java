/** 
 * Sort of HRegionInfo.
 */
public void testHRegionInfo(){
  HRegionInfo a=new HRegionInfo(new HTableDescriptor("a"),null,null);
  HRegionInfo b=new HRegionInfo(new HTableDescriptor("b"),null,null);
  assertTrue(a.compareTo(b) != 0);
  HTableDescriptor t=new HTableDescriptor("t");
  byte[] midway=Bytes.toBytes("midway");
  a=new HRegionInfo(t,null,midway);
  b=new HRegionInfo(t,midway,null);
  assertTrue(a.compareTo(b) < 0);
  assertTrue(b.compareTo(a) > 0);
  assertEquals(a,a);
  assertTrue(a.compareTo(a) == 0);
  a=new HRegionInfo(t,Bytes.toBytes("a"),Bytes.toBytes("d"));
  b=new HRegionInfo(t,Bytes.toBytes("e"),Bytes.toBytes("g"));
  assertTrue(a.compareTo(b) < 0);
  a=new HRegionInfo(t,Bytes.toBytes("aaaa"),Bytes.toBytes("dddd"));
  b=new HRegionInfo(t,Bytes.toBytes("e"),Bytes.toBytes("g"));
  assertTrue(a.compareTo(b) < 0);
  a=new HRegionInfo(t,Bytes.toBytes("aaaa"),Bytes.toBytes("dddd"));
  b=new HRegionInfo(t,Bytes.toBytes("aaaa"),Bytes.toBytes("eeee"));
  assertTrue(a.compareTo(b) < 0);
}
