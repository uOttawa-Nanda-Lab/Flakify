@Test public void invoke() throws Exception {
  List<Object> result=getEndpointBean().invoke();
  assertEquals(1,result.size());
  assertTrue(result.get(0) instanceof Map);
}
