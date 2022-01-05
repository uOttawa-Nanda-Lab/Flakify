@Test public void testSimpleByteMode() throws Exception {
  BitSourceBuilder builder=new BitSourceBuilder();
  builder.write(0x04,4);
  builder.write(0x03,8);
  builder.write(0xF1,8);
  builder.write(0xF2,8);
  builder.write(0xF3,8);
  String result=DecodedBitStreamParser.decode(builder.toByteArray(),Version.getVersionForNumber(1),null,null).getText();
  assertEquals("\u00f1\u00f2\u00f3",result);
}
