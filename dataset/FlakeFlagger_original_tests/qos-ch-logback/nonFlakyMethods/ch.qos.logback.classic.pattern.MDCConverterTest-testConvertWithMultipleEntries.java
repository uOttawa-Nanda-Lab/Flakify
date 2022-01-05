@Test public void testConvertWithMultipleEntries(){
  MDC.put("testKey","testValue");
  MDC.put("testKey2","testValue2");
  ILoggingEvent le=createLoggingEvent();
  String result=converter.convert(le);
  boolean isConform=result.matches("testKey2?=testValue2?, testKey2?=testValue2?");
  assertTrue(result + " is not conform",isConform);
}
