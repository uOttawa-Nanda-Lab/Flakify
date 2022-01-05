/** 
 * Getting data from memstore only
 * @throws IOException
 */
public void testGet_FromMemStoreOnly() throws IOException {
  init(this.getName());
  this.store.add(new KeyValue(row,family,qf1,null));
  this.store.add(new KeyValue(row,family,qf2,null));
  this.store.add(new KeyValue(row,family,qf3,null));
  this.store.add(new KeyValue(row,family,qf4,null));
  this.store.add(new KeyValue(row,family,qf5,null));
  this.store.add(new KeyValue(row,family,qf6,null));
  this.store.get(get,qualifiers,result);
  assertCheck();
}
