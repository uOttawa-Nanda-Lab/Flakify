@Test public void testToQueryString() throws Exception {
  Map<String,String> map=new HashMap<String,String>();
  map.put("key1","value1");
  map.put("key2","value2");
  String queryString=StringUtils.toQueryString(map);
  assertThat(queryString,containsString("key1=value1"));
  assertThat(queryString,containsString("key2=value2"));
}
