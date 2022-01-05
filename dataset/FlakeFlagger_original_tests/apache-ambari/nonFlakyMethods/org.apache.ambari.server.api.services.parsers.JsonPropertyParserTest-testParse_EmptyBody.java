@Test public void testParse_EmptyBody(){
  RequestBodyParser parser=new JsonPropertyParser();
  Set<Map<String,Object>> setProps=parser.parse("");
  assertNotNull(setProps);
  assertEquals(0,setProps.size());
}
