@Test public void parse(){
  Map.Entry<String,String> result=this.routing.parse("session1.route1");
  assertEquals("session1",result.getKey());
  assertEquals("route1",result.getValue());
  result=this.routing.parse("session2");
  assertEquals("session2",result.getKey());
  assertNull(result.getValue());
  result=this.routing.parse(null);
  assertNull(result.getKey());
  assertNull(result.getValue());
}
