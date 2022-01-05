@Test public void shiftLeftFromZero_VariableCapacity(){
  BitArray.VariableCapacity b=new BitArray.VariableCapacity();
  b.set(0);
  b.shiftLeft(1);
  assertEquals(asList(1),b.toIntegerList());
}
