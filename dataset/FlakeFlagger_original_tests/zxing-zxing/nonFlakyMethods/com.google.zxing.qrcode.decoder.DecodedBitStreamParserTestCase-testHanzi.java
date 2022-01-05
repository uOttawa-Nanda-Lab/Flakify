@Test public void testHanzi() throws Exception {
  BitSourceBuilder builder=new BitSourceBuilder();
  builder.write(0x0D,4);
  builder.write(0x01,4);
  builder.write(0x01,8);
  builder.write(0x03C1,13);
  String result=DecodedBitStreamParser.decode(builder.toByteArray(),Version.getVersionForNumber(1),null,null).getText();
  assertEquals("\u963f",result);
}
