@Test public void intRange(){
  Integer value=(Integer)this.source.getProperty("random.int[4,10]");
  assertNotNull(value);
  assertTrue(value >= 4);
}
