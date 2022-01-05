@Test public void multipleShiftsLeftAcrossOffset_VariableCapacity(){
  BitArray.VariableCapacity b=new BitArray.VariableCapacity();
  b.set(1000);
  b.shiftLeft(67);
  assertEquals(asList(1067),b.toIntegerList());
  b.shiftLeft(69);
  assertEquals(asList(1136),b.toIntegerList());
}
