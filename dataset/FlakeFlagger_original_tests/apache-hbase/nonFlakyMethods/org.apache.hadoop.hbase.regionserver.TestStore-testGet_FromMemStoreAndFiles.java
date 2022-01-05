/** 
 * Getting data from memstore and files
 * @throws IOException
 */
public void testGet_FromMemStoreAndFiles() throws IOException {
  init(this.getName());
  this.store.add(new KeyValue(row,family,qf1,null));
  this.store.add(new KeyValue(row,family,qf2,null));
  flush(1);
  this.store.add(new KeyValue(row,family,qf3,null));
  this.store.add(new KeyValue(row,family,qf4,null));
  flush(2);
  this.store.add(new KeyValue(row,family,qf5,null));
  this.store.add(new KeyValue(row,family,qf6,null));
  this.store.get(get,qualifiers,result);
  Collections.sort(result,KeyValue.COMPARATOR);
  assertCheck();
}
