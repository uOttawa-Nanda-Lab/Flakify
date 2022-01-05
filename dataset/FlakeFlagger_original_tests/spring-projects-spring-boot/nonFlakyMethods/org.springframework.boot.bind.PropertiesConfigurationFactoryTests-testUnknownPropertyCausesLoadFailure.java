@Test(expected=NotWritablePropertyException.class) public void testUnknownPropertyCausesLoadFailure() throws Exception {
  this.ignoreUnknownFields=false;
  createFoo("hi: hello\nname: foo\nbar: blah");
}
