@Test public void contextProperty(){
  PropertyConverter converter=new PropertyConverter();
  converter.setContext(lc);
  List<String> ol=new ArrayList<String>();
  ol.add("k");
  converter.setOptionList(ol);
  converter.start();
  lc.setName("aValue");
  lc.putProperty("k","v");
  ILoggingEvent event=makeLoggingEvent(null);
  String result=converter.convert(event);
  assertEquals("v",result);
}
