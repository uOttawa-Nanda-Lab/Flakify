public void testHMsg() throws Exception {
  HMsg m=new HMsg(HMsg.Type.MSG_REGIONSERVER_QUIESCE);
  byte[] mb=Writables.getBytes(m);
  HMsg deserializedHMsg=(HMsg)Writables.getWritable(mb,new HMsg());
  assertTrue(m.equals(deserializedHMsg));
  m=new HMsg(HMsg.Type.MSG_REGIONSERVER_QUIESCE,new HRegionInfo(new HTableDescriptor(getName()),HConstants.EMPTY_BYTE_ARRAY,HConstants.EMPTY_BYTE_ARRAY),"Some message".getBytes());
  mb=Writables.getBytes(m);
  deserializedHMsg=(HMsg)Writables.getWritable(mb,new HMsg());
  assertTrue(m.equals(deserializedHMsg));
}
