@Test public void assertChildEventWhenEventDataIsEmpty() throws Exception {
  when(event.getData()).thenReturn(null);
  fooJobListener.childEvent(null,event);
  verify(list,times(0)).clear();
}
