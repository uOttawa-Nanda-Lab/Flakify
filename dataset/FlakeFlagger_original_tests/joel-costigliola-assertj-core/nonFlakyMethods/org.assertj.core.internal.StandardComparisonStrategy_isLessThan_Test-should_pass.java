@Test public void should_pass(){
  Employee boss=new Employee(10000,35);
  Employee young=new Employee(10000,25);
  assertTrue(standardComparisonStrategy.isLessThan(young,boss));
  assertFalse(standardComparisonStrategy.isLessThan(boss,young));
  assertFalse(standardComparisonStrategy.isLessThan(boss,boss));
}
