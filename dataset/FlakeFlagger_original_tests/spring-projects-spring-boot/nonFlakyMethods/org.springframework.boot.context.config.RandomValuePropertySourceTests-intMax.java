@Test public void intMax(){
  Integer value=(Integer)this.source.getProperty("random.int(10)");
  assertNotNull(value);
  assertTrue(value < 10);
}
