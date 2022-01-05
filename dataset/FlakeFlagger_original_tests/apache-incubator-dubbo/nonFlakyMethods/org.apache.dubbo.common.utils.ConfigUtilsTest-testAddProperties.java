@Test public void testAddProperties() throws Exception {
  Properties p=new Properties();
  p.put("key1","value1");
  ConfigUtils.addProperties(p);
  assertThat((String)ConfigUtils.getProperties().get("key1"),equalTo("value1"));
}
