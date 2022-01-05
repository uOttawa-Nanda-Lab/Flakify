@Test public void testConvertWithOneEntry(){
  String k="MDCConverterTest_k" + diff;
  String v="MDCConverterTest_v" + diff;
  MDC.put(k,v);
  ILoggingEvent le=createLoggingEvent();
  String result=converter.convert(le);
  assertEquals(k + "=" + v,result);
}
