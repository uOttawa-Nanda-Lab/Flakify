@Test public void testLoadPropertiesNoFile() throws Exception {
  Properties p=ConfigUtils.loadProperties("notExisted",true);
  Properties expected=new Properties();
  assertEquals(expected,p);
  p=ConfigUtils.loadProperties("notExisted",false);
  assertEquals(expected,p);
}
