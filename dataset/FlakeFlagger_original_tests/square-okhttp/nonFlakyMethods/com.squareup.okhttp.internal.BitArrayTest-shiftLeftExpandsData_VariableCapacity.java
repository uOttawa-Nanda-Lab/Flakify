@Test public void shiftLeftExpandsData_VariableCapacity(){
  BitArray.VariableCapacity b=new BitArray.VariableCapacity();
  b.set(0);
  b.shiftLeft(64);
  assertEquals(asList(64),b.toIntegerList());
}
