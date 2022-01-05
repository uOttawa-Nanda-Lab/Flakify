@Test public void shiftLeftAcrossOffset_VariableCapacity(){
  BitArray.VariableCapacity b=new BitArray.VariableCapacity();
  b.set(63);
  assertEquals(1,b.data.length);
  b.shiftLeft(1);
  assertEquals(asList(64),b.toIntegerList());
  assertEquals(2,b.data.length);
}
