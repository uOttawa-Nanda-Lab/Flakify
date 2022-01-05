@Test public void should_return_values_of_property(){
  List<Integer> ages=newArrayList();
  ages.add(yoda.getAge());
  when(propertySupport.propertyValues(propertyName,Integer.class,employees)).thenReturn(ages);
  assertSame(ages,properties.from(employees));
}
