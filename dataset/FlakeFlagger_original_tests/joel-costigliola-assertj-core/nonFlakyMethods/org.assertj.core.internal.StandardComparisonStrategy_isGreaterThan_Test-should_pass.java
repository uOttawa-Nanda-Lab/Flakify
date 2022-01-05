@Test public void should_pass(){
  Employee boss=new Employee(10000,35);
  Employee young=new Employee(10000,25);
  assertTrue(standardComparisonStrategy.isGreaterThan(boss,young));
  assertFalse(standardComparisonStrategy.isGreaterThan(young,boss));
  assertFalse(standardComparisonStrategy.isGreaterThan(boss,boss));
}
