@Test public void testGetServiceKey() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put(Constants.GROUP_KEY,"dubbo");
  map.put(Constants.INTERFACE_KEY,"a.b.c.Foo");
  map.put(Constants.VERSION_KEY,"1.0.0");
  assertThat(StringUtils.getServiceKey(map),equalTo("dubbo/a.b.c.Foo:1.0.0"));
}
