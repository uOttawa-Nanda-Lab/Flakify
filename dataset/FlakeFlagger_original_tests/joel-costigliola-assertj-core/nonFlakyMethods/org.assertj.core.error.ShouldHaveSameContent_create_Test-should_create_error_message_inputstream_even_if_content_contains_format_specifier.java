@Test public void should_create_error_message_inputstream_even_if_content_contains_format_specifier(){
  ErrorMessageFactory factory=shouldHaveSameContent(new ByteArrayInputStream(new byte[]{'a'}),new ByteArrayInputStream(new byte[]{'b'}),diffs);
  StringBuilder b=new StringBuilder("[Test] \nInputStreams do not have same content:");
  for (  String diff : diffs)   b.append(LINE_SEPARATOR).append(diff);
  assertThat(factory.create(new TextDescription("Test"),new StandardRepresentation())).isEqualTo(b.toString());
}
