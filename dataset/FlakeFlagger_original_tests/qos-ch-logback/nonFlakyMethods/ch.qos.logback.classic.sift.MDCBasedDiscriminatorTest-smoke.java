@Test public void smoke(){
  MDC.put(key,value);
  event=new LoggingEvent("a",logger,Level.DEBUG,"",null,null);
  String discriminatorValue=discriminator.getDiscriminatingValue(event);
  assertEquals(value,discriminatorValue);
}
