@Test public void testAppendAttributes2() throws Exception {
  Map<Object,Object> parameters=new HashMap<Object,Object>();
  AbstractConfig.appendAttributes(parameters,new AttributeConfig('l',true,(byte)0x01));
  TestCase.assertEquals('l',parameters.get("let"));
  TestCase.assertEquals(true,parameters.get("activate"));
  TestCase.assertFalse(parameters.containsKey("flag"));
}
