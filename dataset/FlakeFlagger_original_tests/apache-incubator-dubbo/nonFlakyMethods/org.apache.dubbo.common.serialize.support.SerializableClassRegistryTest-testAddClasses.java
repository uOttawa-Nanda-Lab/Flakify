@Test public void testAddClasses(){
  SerializableClassRegistry.registerClass(A.class);
  SerializableClassRegistry.registerClass(B.class);
  Map<Class,Object> registeredClasses=SerializableClassRegistry.getRegisteredClasses();
  assertThat(registeredClasses.size(),equalTo(2));
}
