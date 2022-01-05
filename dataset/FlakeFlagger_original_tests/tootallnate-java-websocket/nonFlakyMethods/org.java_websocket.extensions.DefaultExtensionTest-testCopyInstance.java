@Test public void testCopyInstance() throws Exception {
  DefaultExtension defaultExtension=new DefaultExtension();
  IExtension extensionCopy=defaultExtension.copyInstance();
  assertEquals(defaultExtension,extensionCopy);
}
