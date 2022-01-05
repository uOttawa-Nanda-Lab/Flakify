@Test public void shouldOrderProviders(){
  final OrderedProvider lowest=new OrderedProvider(Ordered.LOWEST);
  final Object defaultPriority=new Object();
  final OrderedProvider highest=new OrderedProvider(Ordered.HIGHEST);
  victim=new ProviderFinder<Object>(Object.class){
    @Override @SuppressWarnings("unchecked") <P>Iterator<P> lookupProviders(    final Class<P> providerClass){
      if (providerClass == Object.class) {
        return (Iterator<P>)Arrays.asList(defaultPriority,highest,lowest).iterator();
      }
      return Collections.<P>emptyList().iterator();
    }
  }
;
  assertEquals(Arrays.asList(lowest,defaultPriority,highest),victim.find());
}
