@Test public void test_Constructor_CheckStatus() throws Exception {
  URL url=URL.valueOf("notsupported://10.20.30.40/" + service + "?a=b").addParameterAndEncoded(Constants.REFER_KEY,"foo=bar");
  RegistryDirectory reg=getRegistryDirectory(url);
  Field field=reg.getClass().getDeclaredField("queryMap");
  field.setAccessible(true);
  Map<String,String> queryMap=(Map<String,String>)field.get(reg);
  Assert.assertEquals("bar",queryMap.get("foo"));
  Assert.assertEquals(url.clearParameters().addParameter("foo","bar"),reg.getUrl());
}
