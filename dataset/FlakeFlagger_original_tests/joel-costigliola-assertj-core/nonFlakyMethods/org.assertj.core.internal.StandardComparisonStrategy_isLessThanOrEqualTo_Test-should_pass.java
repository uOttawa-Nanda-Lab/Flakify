@Test public void should_pass(){
  Employee boss=new Employee(10000,35);
  Employee young=new Employee(10000,25);
  assertTrue(standardComparisonStrategy.isLessThanOrEqualTo(young,boss));
  assertFalse(standardComparisonStrategy.isLessThanOrEqualTo(boss,young));
  assertTrue(standardComparisonStrategy.isLessThanOrEqualTo(boss,boss));
}
