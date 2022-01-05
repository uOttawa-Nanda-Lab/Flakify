@Test public void should_fail_if_other_is_null(){
  try {
    paths.assertEndsWithRaw(info,actual,null);
    fail("expected a NullPointerException here");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("the expected end path should not be null");
  }
}
