@Test public void should_fail_if_given_file_name_is_null(){
  try {
    paths.assertHasFileName(info,existingFile,null);
    fail("expected a NullPointerException here");
  }
 catch (  NullPointerException e) {
    assertThat(e).hasMessage("expected fileName should not be null");
  }
}
