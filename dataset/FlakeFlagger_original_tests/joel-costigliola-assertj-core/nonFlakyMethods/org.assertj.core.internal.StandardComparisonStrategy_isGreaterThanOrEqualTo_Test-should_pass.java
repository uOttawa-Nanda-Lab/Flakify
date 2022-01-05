@Test public void should_pass(){
  Employee boss=new Employee(10000,35);
  Employee young=new Employee(10000,25);
  assertTrue(standardComparisonStrategy.isGreaterThanOrEqualTo(boss,young));
  assertTrue(standardComparisonStrategy.isGreaterThanOrEqualTo(boss,boss));
  assertFalse(standardComparisonStrategy.isGreaterThanOrEqualTo(young,boss));
}
