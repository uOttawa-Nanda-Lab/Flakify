@Test public void assertChildEventSuccess() throws Exception {
  when(event.getData()).thenReturn(new ChildData("/test_job",null,null));
  fooJobListener.childEvent(null,event);
  verify(list).clear();
}
