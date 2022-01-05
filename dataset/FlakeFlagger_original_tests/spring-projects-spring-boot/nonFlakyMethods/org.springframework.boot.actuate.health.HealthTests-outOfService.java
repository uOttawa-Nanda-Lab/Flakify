@Test public void outOfService() throws Exception {
  Health health=Health.outOfService().build();
  assertThat(health.getStatus(),equalTo(Status.OUT_OF_SERVICE));
  assertThat(health.getDetails().size(),equalTo(0));
}
