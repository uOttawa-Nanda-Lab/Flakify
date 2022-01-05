@Test public void testChildClass(){
  Class<?>[] expeccted={HandlesTypesGandchild.class,HandlesTypesImplementorChild.class};
  Assert.assertEquals(new HashSet<>(Arrays.asList(expeccted)),ChildServletContainerInitializer.HANDLES_TYPES);
}
