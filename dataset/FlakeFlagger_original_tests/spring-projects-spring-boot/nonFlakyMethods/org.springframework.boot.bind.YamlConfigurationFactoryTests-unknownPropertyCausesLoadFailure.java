@Test(expected=YAMLException.class) public void unknownPropertyCausesLoadFailure() throws Exception {
  createFoo("hi: hello\nname: foo\nbar: blah");
}
