@Test public void should_create_error_message_file_even_if_content_contains_format_specifier(){
  ErrorMessageFactory factory=ShouldHaveSameContent.shouldHaveSameContent(new FakeFile("abc"),new FakeFile("xyz"),diffs);
  StringBuilder b=new StringBuilder("[Test] \nFile:\n  <abc>\nand file:\n  <xyz>\ndo not have same content:");
  for (  String diff : diffs)   b.append(LINE_SEPARATOR).append(diff);
  assertThat(factory.create(new TextDescription("Test"),new StandardRepresentation())).isEqualTo(b.toString());
}
