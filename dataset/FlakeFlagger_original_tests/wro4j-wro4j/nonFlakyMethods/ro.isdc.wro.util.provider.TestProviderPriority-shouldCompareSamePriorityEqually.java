@Test public void shouldCompareSamePriorityEqually(){
  assertEquals(0,Ordered.ASCENDING_COMPARATOR.compare(LOW,LOW));
  assertEquals(0,Ordered.ASCENDING_COMPARATOR.compare(MEDIUM,MEDIUM));
  assertEquals(0,Ordered.ASCENDING_COMPARATOR.compare(HIGH,HIGH));
}
