@Test public void readByte() throws Exception {
  OkBuffer data=new OkBuffer();
  data.write(new byte[]{(byte)0xab,(byte)0xcd});
  assertEquals(0xab,data.readByte() & 0xff);
  assertEquals(0xcd,data.readByte() & 0xff);
  assertEquals(0,data.size());
}
