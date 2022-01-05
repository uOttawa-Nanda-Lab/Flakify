@Test public void clearBits_VariableCapacity(){
  BitArray.VariableCapacity b=new BitArray.VariableCapacity();
  b.set(10);
  b.set(100);
  b.set(1000);
  b.clear();
  assertTrue(b.toIntegerList().isEmpty());
}
