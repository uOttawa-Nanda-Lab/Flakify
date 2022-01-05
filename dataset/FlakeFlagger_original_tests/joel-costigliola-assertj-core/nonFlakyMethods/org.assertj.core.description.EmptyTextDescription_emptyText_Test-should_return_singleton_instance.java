@Test public void should_return_singleton_instance(){
  Description description=EmptyTextDescription.emptyText();
  for (int i=0; i < 6; i++)   assertThat(EmptyTextDescription.emptyText()).isSameAs(description);
}
