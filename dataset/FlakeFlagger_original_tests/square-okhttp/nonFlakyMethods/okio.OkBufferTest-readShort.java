@Test public void readShort() throws Exception {
  OkBuffer data=new OkBuffer();
  data.write(new byte[]{(byte)0xab,(byte)0xcd,(byte)0xef,(byte)0x01});
  assertEquals((short)0xabcd,data.readShort());
  assertEquals((short)0xef01,data.readShort());
  assertEquals(0,data.size());
}
