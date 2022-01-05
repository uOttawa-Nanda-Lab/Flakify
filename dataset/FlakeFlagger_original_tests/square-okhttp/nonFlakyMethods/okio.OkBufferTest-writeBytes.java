@Test public void writeBytes() throws Exception {
  OkBuffer data=new OkBuffer();
  data.writeByte(0xab);
  data.writeByte(0xcd);
  assertEquals("OkBuffer[size=2 data=abcd]",data.toString());
}
