@Test public void testParse_NullBody(){
  RequestBodyParser parser=new JsonPropertyParser();
  Set<Map<String,Object>> setProps=parser.parse(null);
  assertNotNull(setProps);
  assertEquals(0,setProps.size());
}
