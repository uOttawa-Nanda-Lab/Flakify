@Test public void mongoIsDown() throws Exception {
  MongoTemplate mongoTemplate=mock(MongoTemplate.class);
  given(mongoTemplate.executeCommand("{ buildInfo: 1 }")).willThrow(new MongoException("Connection failed"));
  MongoHealthIndicator healthIndicator=new MongoHealthIndicator(mongoTemplate);
  Health health=healthIndicator.health();
  assertEquals(Status.DOWN,health.getStatus());
  assertTrue(((String)health.getDetails().get("error")).contains("Connection failed"));
  verify(mongoTemplate).executeCommand("{ buildInfo: 1 }");
}
