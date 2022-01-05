@Test public void testRemoveChildrenListener(){
  ChildListener childListener=mock(ChildListener.class);
  curatorClient.addChildListener("/children",childListener);
  curatorClient.removeChildListener("/children",childListener);
}
