@Test public void indicatesUp() throws Exception {
  ApplicationHealthIndicator healthIndicator=new ApplicationHealthIndicator();
  assertEquals(Status.UP,healthIndicator.health().getStatus());
}
