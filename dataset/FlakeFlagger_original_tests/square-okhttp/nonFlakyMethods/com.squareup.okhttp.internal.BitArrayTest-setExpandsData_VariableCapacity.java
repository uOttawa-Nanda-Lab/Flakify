@Test public void setExpandsData_VariableCapacity(){
  BitArray.VariableCapacity b=new BitArray.VariableCapacity();
  b.set(64);
  assertEquals(asList(64),b.toIntegerList());
}
