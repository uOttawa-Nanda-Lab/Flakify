@Test public void testConvert(){
  StringArrayToStringConverter converter=new StringArrayToStringConverter();
  String value=converter.convert(new String[]{"Hello","World"});
  Assert.assertEquals("Hello,World",value);
  value=converter.convert(new String[]{});
  Assert.assertNull(value);
  value=converter.convert(null);
  Assert.assertNull(value);
}
