@Test public void down() throws Exception {
  Health health=Health.down().build();
  assertThat(health.getStatus(),equalTo(Status.DOWN));
  assertThat(health.getDetails().size(),equalTo(0));
}
