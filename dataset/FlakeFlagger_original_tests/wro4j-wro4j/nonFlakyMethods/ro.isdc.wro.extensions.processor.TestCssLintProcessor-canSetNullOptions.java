@Test public void canSetNullOptions() throws Exception {
  final String[] options=null;
  victim.setOptions(options);
  victim.process(null,new StringReader(createValidCss()),new StringWriter());
}
