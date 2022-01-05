@Test public void longValue(){
  Long value=(Long)this.source.getProperty("random.long");
  assertNotNull(value);
}
