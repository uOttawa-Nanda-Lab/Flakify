@Test public void should_throw_error_if_expected_is_not_file(){
  thrown.expectIllegalArgumentException("Expected file:<'xyz'> should be an existing file");
  File notAFile=new File("xyz");
  files.assertSameContentAs(someInfo(),actual,notAFile);
}
