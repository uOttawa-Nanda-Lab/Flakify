@Test public void testConvert(){
  StringArrayToMapConverter converter=new StringArrayToMapConverter();
  Map<String,String> value=converter.convert(new String[]{"Hello","World"});
  Map<String,String> expected=new LinkedHashMap<String,String>();
  expected.put("Hello","World");
  Assert.assertEquals(expected,value);
  value=converter.convert(new String[]{});
  Assert.assertNull(value);
  value=converter.convert(null);
  Assert.assertNull(value);
}
