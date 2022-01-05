@Test public void testECI() throws Exception {
  BitSourceBuilder builder=new BitSourceBuilder();
  builder.write(0x07,4);
  builder.write(0x02,8);
  builder.write(0x04,4);
  builder.write(0x03,8);
  builder.write(0xA1,8);
  builder.write(0xA2,8);
  builder.write(0xA3,8);
  String result=DecodedBitStreamParser.decode(builder.toByteArray(),Version.getVersionForNumber(1),null,null).getText();
  assertEquals("\u00ed\u00f3\u00fa",result);
}
