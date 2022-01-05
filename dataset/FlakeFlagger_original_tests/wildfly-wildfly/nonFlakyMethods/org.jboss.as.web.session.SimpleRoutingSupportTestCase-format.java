@Test public void format(){
  assertEquals("session1.route1",this.routing.format("session1","route1"));
  assertEquals("session2",this.routing.format("session2",""));
  assertEquals("session3",this.routing.format("session3",null));
  assertNull(this.routing.format(null,null));
}
