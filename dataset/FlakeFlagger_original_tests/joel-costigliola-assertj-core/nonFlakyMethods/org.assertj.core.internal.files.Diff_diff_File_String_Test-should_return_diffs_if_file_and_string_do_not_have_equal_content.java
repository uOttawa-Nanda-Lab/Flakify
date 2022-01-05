@Test public void should_return_diffs_if_file_and_string_do_not_have_equal_content() throws IOException {
  writer.write(actual,UTF8,"Touché");
  String expected="Touché";
  List<String> diffs=diff.diff(actual,expected,ISO_8859_1);
  assertEquals(1,diffs.size());
  assertEquals("line:<1>, expected:<Touché> but was:<TouchÃ©>",diffs.get(0));
}
