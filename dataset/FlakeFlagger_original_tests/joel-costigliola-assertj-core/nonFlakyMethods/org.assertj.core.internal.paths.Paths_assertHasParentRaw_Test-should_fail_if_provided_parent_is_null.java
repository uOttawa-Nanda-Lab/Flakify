@Test public void should_fail_if_provided_parent_is_null(){
  try {
    paths.assertHasParentRaw(info,actual,null);
    fail("expected a NullPointerException here");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("expected parent path should not be null");
  }
}
