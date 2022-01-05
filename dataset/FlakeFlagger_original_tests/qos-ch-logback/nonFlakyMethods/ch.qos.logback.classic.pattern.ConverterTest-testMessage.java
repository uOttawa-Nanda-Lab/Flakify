@Test public void testMessage(){
  DynamicConverter<ILoggingEvent> converter=new MessageConverter();
  StringBuilder buf=new StringBuilder();
  converter.write(buf,le);
  assertEquals("Some message",buf.toString());
}
