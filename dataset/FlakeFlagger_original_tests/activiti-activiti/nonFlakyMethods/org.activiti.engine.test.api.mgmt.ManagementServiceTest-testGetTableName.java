public void testGetTableName(){
  String table=managementService.getTableName(EventSubscriptionEntity.class);
  assertEquals("ACT_RU_EVENT_SUBSCR",table);
}
