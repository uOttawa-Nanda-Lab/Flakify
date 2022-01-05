@Test public void testSimpleSJIS() throws Exception {
  BitSourceBuilder builder=new BitSourceBuilder();
  builder.write(0x04,4);
  builder.write(0x04,8);
  builder.write(0xA1,8);
  builder.write(0xA2,8);
  builder.write(0xA3,8);
  builder.write(0xD0,8);
  String result=DecodedBitStreamParser.decode(builder.toByteArray(),Version.getVersionForNumber(1),null,null).getText();
  assertEquals("\uff61\uff62\uff63\uff90",result);
}
