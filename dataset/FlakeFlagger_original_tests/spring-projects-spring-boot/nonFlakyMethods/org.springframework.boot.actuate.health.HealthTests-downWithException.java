@Test public void downWithException() throws Exception {
  RuntimeException ex=new RuntimeException("bang");
  Health health=Health.down(ex).build();
  assertThat(health.getStatus(),equalTo(Status.DOWN));
  assertThat(health.getDetails().get("error"),equalTo((Object)"java.lang.RuntimeException: bang"));
}
