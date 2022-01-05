@Test public void writeShort() throws Exception {
  OkBuffer data=new OkBuffer();
  data.writeShort(0xabcd);
  data.writeShort(0x4321);
  assertEquals("OkBuffer[size=4 data=abcd4321]",data.toString());
}
