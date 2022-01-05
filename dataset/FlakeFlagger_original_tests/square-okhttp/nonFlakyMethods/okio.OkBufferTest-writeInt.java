@Test public void writeInt() throws Exception {
  OkBuffer data=new OkBuffer();
  data.writeInt(0xabcdef01);
  data.writeInt(0x87654321);
  assertEquals("OkBuffer[size=8 data=abcdef0187654321]",data.toString());
}
