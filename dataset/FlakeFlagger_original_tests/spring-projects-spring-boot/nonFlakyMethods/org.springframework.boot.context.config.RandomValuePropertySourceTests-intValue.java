@Test public void intValue(){
  Integer value=(Integer)this.source.getProperty("random.int");
  assertNotNull(value);
}
