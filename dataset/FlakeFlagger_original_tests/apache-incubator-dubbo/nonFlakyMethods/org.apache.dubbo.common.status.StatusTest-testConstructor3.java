@Test public void testConstructor3() throws Exception {
  Status status=new Status(OK);
  assertThat(status.getLevel(),is(OK));
  assertThat(status.getMessage(),isEmptyOrNullString());
  assertThat(status.getDescription(),isEmptyOrNullString());
}
