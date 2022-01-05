@Test public void testConstructor1() throws Exception {
  Status status=new Status(OK,"message","description");
  assertThat(status.getLevel(),is(OK));
  assertThat(status.getMessage(),equalTo("message"));
  assertThat(status.getDescription(),equalTo("description"));
}
