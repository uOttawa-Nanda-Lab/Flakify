@Test public void testParentClass(){
  Class<?>[] expeccted={HandlesTypesChild.class,HandlesTypesImplementor.class,HandlesTypesGandchild.class,HandlesTypesImplementorChild.class};
  Assert.assertEquals(new HashSet<>(Arrays.asList(expeccted)),ParentServletContainerInitializer.HANDLES_TYPES);
}
