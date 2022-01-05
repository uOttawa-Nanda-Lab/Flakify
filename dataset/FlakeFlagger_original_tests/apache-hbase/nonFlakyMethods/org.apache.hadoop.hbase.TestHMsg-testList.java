public void testList(){
  List<HMsg> msgs=new ArrayList<HMsg>();
  HMsg hmsg=null;
  final int size=10;
  for (int i=0; i < size; i++) {
    byte[] b=Bytes.toBytes(i);
    hmsg=new HMsg(HMsg.Type.MSG_REGION_OPEN,new HRegionInfo(new HTableDescriptor(Bytes.toBytes("test")),b,b));
    msgs.add(hmsg);
  }
  assertEquals(size,msgs.size());
  int index=msgs.indexOf(hmsg);
  assertNotSame(-1,index);
  msgs.remove(index);
  assertEquals(size - 1,msgs.size());
  byte[] other=Bytes.toBytes("other");
  hmsg=new HMsg(HMsg.Type.MSG_REGION_OPEN,new HRegionInfo(new HTableDescriptor(Bytes.toBytes("test")),other,other));
  assertEquals(-1,msgs.indexOf(hmsg));
  byte[] b=Bytes.toBytes(1);
  hmsg=new HMsg(HMsg.Type.MSG_REGION_OPEN,new HRegionInfo(new HTableDescriptor(Bytes.toBytes("test")),b,b));
  assertNotSame(-1,msgs.indexOf(hmsg));
}
