@SuppressWarnings("unchecked") @Test public void should_throw_error_if_entry_is_null(){
  MapEntry<String,String>[] entries=new MapEntry[]{null};
  thrown.expectNullPointerException(entryToLookForIsNull());
  maps.assertContains(someInfo(),actual,entries);
}
