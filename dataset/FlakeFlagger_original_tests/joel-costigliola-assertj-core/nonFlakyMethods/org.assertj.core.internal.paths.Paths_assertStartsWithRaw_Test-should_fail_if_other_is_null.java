@Test public void should_fail_if_other_is_null(){
  try {
    paths.assertStartsWithRaw(info,actual,null);
    fail("expected a NullPointerException here");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("the expected start path should not be null");
  }
}
