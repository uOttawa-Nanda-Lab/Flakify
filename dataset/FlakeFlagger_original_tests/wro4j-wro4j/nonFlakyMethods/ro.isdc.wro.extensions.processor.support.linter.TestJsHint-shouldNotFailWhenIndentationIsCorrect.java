@Test public void shouldNotFailWhenIndentationIsCorrect() throws Exception {
  jsHint.setOptions("indent=2","white");
  jsHint.validate("function test() {\n  alert(1);\n}");
}
