@Test public void mongoIsUp() throws Exception {
  CommandResult commandResult=mock(CommandResult.class);
  given(commandResult.getString("version")).willReturn("2.6.4");
  MongoTemplate mongoTemplate=mock(MongoTemplate.class);
  given(mongoTemplate.executeCommand("{ buildInfo: 1 }")).willReturn(commandResult);
  MongoHealthIndicator healthIndicator=new MongoHealthIndicator(mongoTemplate);
  Health health=healthIndicator.health();
  assertEquals(Status.UP,health.getStatus());
  assertEquals("2.6.4",health.getDetails().get("version"));
  verify(commandResult).getString("version");
  verify(mongoTemplate).executeCommand("{ buildInfo: 1 }");
}
