@Test public void readInt() throws Exception {
  OkBuffer data=new OkBuffer();
  data.write(new byte[]{(byte)0xab,(byte)0xcd,(byte)0xef,(byte)0x01,(byte)0x87,(byte)0x65,(byte)0x43,(byte)0x21});
  assertEquals(0xabcdef01,data.readInt());
  assertEquals(0x87654321,data.readInt());
  assertEquals(0,data.size());
}
