@Test public void testConstructor2() throws Exception {
  Status status=new Status(OK,"message");
  assertThat(status.getLevel(),is(OK));
  assertThat(status.getMessage(),equalTo("message"));
  assertThat(status.getDescription(),isEmptyOrNullString());
}
