@Test public void testEquals(){
  assertEquals(new AtomicPositiveInteger(),new AtomicPositiveInteger());
  assertEquals(new AtomicPositiveInteger(1),new AtomicPositiveInteger(1));
}
