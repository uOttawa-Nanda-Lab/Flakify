@Test public void testAppendAttributes1() throws Exception {
  Map<Object,Object> parameters=new HashMap<Object,Object>();
  AbstractConfig.appendAttributes(parameters,new AttributeConfig('l',true,(byte)0x01),"prefix");
  TestCase.assertEquals('l',parameters.get("prefix.let"));
  TestCase.assertEquals(true,parameters.get("prefix.activate"));
  TestCase.assertFalse(parameters.containsKey("prefix.flag"));
}
