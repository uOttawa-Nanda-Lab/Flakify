@Test public void testLoadPropertiesOneFileAllowMulti() throws Exception {
  Properties p=ConfigUtils.loadProperties("properties.load",true);
  Properties expected=new Properties();
  expected.put("a","12");
  expected.put("b","34");
  expected.put("c","56");
  assertEquals(expected,p);
}
