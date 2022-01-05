@Test public void nullMDC(){
  event=new LoggingEvent("a",logger,Level.DEBUG,"",null,null);
  assertEquals(new HashMap(),event.getMDCPropertyMap());
  String discriminatorValue=discriminator.getDiscriminatingValue(event);
  assertEquals(DEFAULT_VAL,discriminatorValue);
}
