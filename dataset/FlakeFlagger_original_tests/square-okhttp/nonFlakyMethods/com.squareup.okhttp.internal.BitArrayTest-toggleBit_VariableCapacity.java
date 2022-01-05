@Test public void toggleBit_VariableCapacity(){
  BitArray.VariableCapacity b=new BitArray.VariableCapacity();
  b.set(100);
  b.toggle(100);
  assertTrue(b.toIntegerList().isEmpty());
  b.toggle(1);
  assertEquals(asList(1),b.toIntegerList());
}
