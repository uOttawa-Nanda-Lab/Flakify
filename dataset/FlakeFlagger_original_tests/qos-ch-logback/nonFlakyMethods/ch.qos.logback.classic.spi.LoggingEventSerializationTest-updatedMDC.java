@Test public void updatedMDC() throws Exception {
  MDC.put("key","testValue");
  ILoggingEvent event1=createLoggingEvent();
  Serializable s1=pst.transform(event1);
  oos.writeObject(s1);
  MDC.put("key","updatedTestValue");
  ILoggingEvent event2=createLoggingEvent();
  Serializable s2=pst.transform(event2);
  oos.writeObject(s2);
  ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
  inputStream=new ObjectInputStream(bis);
  inputStream.readObject();
  ILoggingEvent remoteEvent2=(ILoggingEvent)inputStream.readObject();
  Map<String,String> MDCPropertyMap=remoteEvent2.getMDCPropertyMap();
  assertEquals("updatedTestValue",MDCPropertyMap.get("key"));
}
