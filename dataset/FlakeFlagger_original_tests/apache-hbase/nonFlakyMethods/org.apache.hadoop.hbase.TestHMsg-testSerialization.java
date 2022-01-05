public void testSerialization() throws IOException {
  byte[] abytes=Bytes.toBytes("a");
  byte[] bbytes=Bytes.toBytes("b");
  byte[] parentbytes=Bytes.toBytes("parent");
  HRegionInfo parent=new HRegionInfo(new HTableDescriptor(Bytes.toBytes("parent")),parentbytes,parentbytes);
  HMsg hmsg=new HMsg(HMsg.Type.MSG_REGION_CLOSE,parent);
  byte[] bytes=Writables.getBytes(hmsg);
  HMsg close=(HMsg)Writables.getWritable(bytes,new HMsg());
  assertTrue(close.equals(hmsg));
  HRegionInfo daughtera=new HRegionInfo(new HTableDescriptor(Bytes.toBytes("a")),abytes,abytes);
  HRegionInfo daughterb=new HRegionInfo(new HTableDescriptor(Bytes.toBytes("b")),bbytes,bbytes);
  HMsg splithmsg=new HMsg(HMsg.Type.MSG_REPORT_SPLIT_INCLUDES_DAUGHTERS,parent,daughtera,daughterb,Bytes.toBytes("split"));
  bytes=Writables.getBytes(splithmsg);
  hmsg=(HMsg)Writables.getWritable(bytes,new HMsg());
  assertTrue(splithmsg.equals(hmsg));
}
