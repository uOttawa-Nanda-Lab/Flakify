/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.standalone.jpa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.ActivitiIllegalArgumentException;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.test.AbstractActivitiTestCase;
import org.activiti.engine.impl.variable.EntityManagerSession;
import org.activiti.engine.impl.variable.EntityManagerSessionFactory;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;

public class JPAVariableTest extends AbstractActivitiTestCase {

    protected static ProcessEngine cachedProcessEngine;

    private static FieldAccessJPAEntity simpleEntityFieldAccess;
    private static PropertyAccessJPAEntity simpleEntityPropertyAccess;
    private static SubclassFieldAccessJPAEntity subclassFieldAccess;
    private static SubclassPropertyAccessJPAEntity subclassPropertyAccess;

    private static ByteIdJPAEntity byteIdJPAEntity;
    private static ShortIdJPAEntity shortIdJPAEntity;
    private static IntegerIdJPAEntity integerIdJPAEntity;
    private static LongIdJPAEntity longIdJPAEntity;
    private static FloatIdJPAEntity floatIdJPAEntity;
    private static DoubleIdJPAEntity doubleIdJPAEntity;
    private static CharIdJPAEntity charIdJPAEntity;
    private static StringIdJPAEntity stringIdJPAEntity;
    private static DateIdJPAEntity dateIdJPAEntity;
    private static SQLDateIdJPAEntity sqlDateIdJPAEntity;
    private static BigDecimalIdJPAEntity bigDecimalIdJPAEntity;
    private static BigIntegerIdJPAEntity bigIntegerIdJPAEntity;
    private static CompoundIdJPAEntity compoundIdJPAEntity;

    private static FieldAccessJPAEntity entityToQuery;
    private static FieldAccessJPAEntity entityToUpdate;

    private static boolean entitiesInitialized;

    private static EntityManagerFactory entityManagerFactory;

    protected void initializeProcessEngine() {
        if (cachedProcessEngine == null) {
            ProcessEngineConfigurationImpl processEngineConfiguration = (ProcessEngineConfigurationImpl) ProcessEngineConfiguration
                    .createProcessEngineConfigurationFromResource("org/activiti/standalone/jpa/activiti.cfg.xml");

            cachedProcessEngine = processEngineConfiguration.buildProcessEngine();

            EntityManagerSessionFactory entityManagerSessionFactory = (EntityManagerSessionFactory) processEngineConfiguration.getSessionFactories().get(EntityManagerSession.class);

            entityManagerFactory = entityManagerSessionFactory.getEntityManagerFactory();
        }
        processEngine = cachedProcessEngine;
    }

    

//  //@TODO: fix Caused by: org.h2.jdbc.JdbcSQLException: Concurrent update in table "JPA_ENTITY_FIELD": another transaction has updated or deleted the same row [90131-193]
//  @Deployment
//  public void testStoreJPAEntityAsVariable() {
//    setupJPAEntities();
//    // -----------------------------------------------------------------------------
//    // Simple test, Start process with JPA entities as variables
//    // -----------------------------------------------------------------------------
//    Map<String, Object> variables = new HashMap<String, Object>();
//    variables.put("simpleEntityFieldAccess", simpleEntityFieldAccess);
//    variables.put("simpleEntityPropertyAccess", simpleEntityPropertyAccess);
//    variables.put("subclassFieldAccess", subclassFieldAccess);
//    variables.put("subclassPropertyAccess", subclassPropertyAccess);
//
//    // Start the process with the JPA-entities as variables. They will be
//    // stored in the DB.
//    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("JPAVariableProcess", variables);
//
//    // Read entity with @Id on field
//    Object fieldAccessResult = runtimeService.getVariable(processInstance.getId(), "simpleEntityFieldAccess");
//    assertTrue(fieldAccessResult instanceof FieldAccessJPAEntity);
//    assertEquals(1L, ((FieldAccessJPAEntity) fieldAccessResult).getId().longValue());
//    assertEquals("value1", ((FieldAccessJPAEntity) fieldAccessResult).getValue());
//
//    // Read entity with @Id on property
//    Object propertyAccessResult = runtimeService.getVariable(processInstance.getId(), "simpleEntityPropertyAccess");
//    assertTrue(propertyAccessResult instanceof PropertyAccessJPAEntity);
//    assertEquals(1L, ((PropertyAccessJPAEntity) propertyAccessResult).getId().longValue());
//    assertEquals("value2", ((PropertyAccessJPAEntity) propertyAccessResult).getValue());
//
//    // Read entity with @Id on field of mapped superclass
//    Object subclassFieldResult = runtimeService.getVariable(processInstance.getId(), "subclassFieldAccess");
//    assertTrue(subclassFieldResult instanceof SubclassFieldAccessJPAEntity);
//    assertEquals(1L, ((SubclassFieldAccessJPAEntity) subclassFieldResult).getId().longValue());
//    assertEquals("value3", ((SubclassFieldAccessJPAEntity) subclassFieldResult).getValue());
//
//    // Read entity with @Id on property of mapped superclass
//    Object subclassPropertyResult = runtimeService.getVariable(processInstance.getId(), "subclassPropertyAccess");
//    assertTrue(subclassPropertyResult instanceof SubclassPropertyAccessJPAEntity);
//    assertEquals(1L, ((SubclassPropertyAccessJPAEntity) subclassPropertyResult).getId().longValue());
//    assertEquals("value4", ((SubclassPropertyAccessJPAEntity) subclassPropertyResult).getValue());
//
//    // -----------------------------------------------------------------------------
//    // Test updating JPA-entity to null-value and back again
//    // -----------------------------------------------------------------------------
//    Object currentValue = runtimeService.getVariable(processInstance.getId(), "simpleEntityFieldAccess");
//    assertNotNull(currentValue);
//    // Set to null
//    runtimeService.setVariable(processInstance.getId(), "simpleEntityFieldAccess", null);
//    currentValue = runtimeService.getVariable(processInstance.getId(), "simpleEntityFieldAccess");
//    assertNull(currentValue);
//    // Set to JPA-entity again
//    runtimeService.setVariable(processInstance.getId(), "simpleEntityFieldAccess", simpleEntityFieldAccess);
//    currentValue = runtimeService.getVariable(processInstance.getId(), "simpleEntityFieldAccess");
//    assertNotNull(currentValue);
//    assertTrue(currentValue instanceof FieldAccessJPAEntity);
//    assertEquals(1L, ((FieldAccessJPAEntity) currentValue).getId().longValue());
//
//    // -----------------------------------------------------------------------------
//    // Test all allowed types of ID values
//    // -----------------------------------------------------------------------------
//
//    variables = new HashMap<String, Object>();
//    variables.put("byteIdJPAEntity", byteIdJPAEntity);
//    variables.put("shortIdJPAEntity", shortIdJPAEntity);
//    variables.put("integerIdJPAEntity", integerIdJPAEntity);
//    variables.put("longIdJPAEntity", longIdJPAEntity);
//    variables.put("floatIdJPAEntity", floatIdJPAEntity);
//    variables.put("doubleIdJPAEntity", doubleIdJPAEntity);
//    variables.put("charIdJPAEntity", charIdJPAEntity);
//    variables.put("stringIdJPAEntity", stringIdJPAEntity);
//    variables.put("dateIdJPAEntity", dateIdJPAEntity);
//    variables.put("sqlDateIdJPAEntity", sqlDateIdJPAEntity);
//    variables.put("bigDecimalIdJPAEntity", bigDecimalIdJPAEntity);
//    variables.put("bigIntegerIdJPAEntity", bigIntegerIdJPAEntity);
//
//    // Start the process with the JPA-entities as variables. They will be
//    // stored in the DB.
//    ProcessInstance processInstanceAllTypes = runtimeService.startProcessInstanceByKey("JPAVariableProcess", variables);
//    Object byteIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "byteIdJPAEntity");
//    assertTrue(byteIdResult instanceof ByteIdJPAEntity);
//    assertEquals(byteIdJPAEntity.getByteId(), ((ByteIdJPAEntity) byteIdResult).getByteId());
//
//    Object shortIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "shortIdJPAEntity");
//    assertTrue(shortIdResult instanceof ShortIdJPAEntity);
//    assertEquals(shortIdJPAEntity.getShortId(), ((ShortIdJPAEntity) shortIdResult).getShortId());
//
//    Object integerIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "integerIdJPAEntity");
//    assertTrue(integerIdResult instanceof IntegerIdJPAEntity);
//    assertEquals(integerIdJPAEntity.getIntId(), ((IntegerIdJPAEntity) integerIdResult).getIntId());
//
//    Object longIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "longIdJPAEntity");
//    assertTrue(longIdResult instanceof LongIdJPAEntity);
//    assertEquals(longIdJPAEntity.getLongId(), ((LongIdJPAEntity) longIdResult).getLongId());
//
//    Object floatIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "floatIdJPAEntity");
//    assertTrue(floatIdResult instanceof FloatIdJPAEntity);
//    assertEquals(floatIdJPAEntity.getFloatId(), ((FloatIdJPAEntity) floatIdResult).getFloatId());
//
//    Object doubleIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "doubleIdJPAEntity");
//    assertTrue(doubleIdResult instanceof DoubleIdJPAEntity);
//    assertEquals(doubleIdJPAEntity.getDoubleId(), ((DoubleIdJPAEntity) doubleIdResult).getDoubleId());
//
//    Object charIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "charIdJPAEntity");
//    assertTrue(charIdResult instanceof CharIdJPAEntity);
//    assertEquals(charIdJPAEntity.getCharId(), ((CharIdJPAEntity) charIdResult).getCharId());
//
//    Object stringIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "stringIdJPAEntity");
//    assertTrue(stringIdResult instanceof StringIdJPAEntity);
//    assertEquals(stringIdJPAEntity.getStringId(), ((StringIdJPAEntity) stringIdResult).getStringId());
//
//    Object dateIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "dateIdJPAEntity");
//    assertTrue(dateIdResult instanceof DateIdJPAEntity);
//    assertEquals(dateIdJPAEntity.getDateId(), ((DateIdJPAEntity) dateIdResult).getDateId());
//
//    Object sqlDateIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "sqlDateIdJPAEntity");
//    assertTrue(sqlDateIdResult instanceof SQLDateIdJPAEntity);
//    assertEquals(sqlDateIdJPAEntity.getDateId(), ((SQLDateIdJPAEntity) sqlDateIdResult).getDateId());
//
//    Object bigDecimalIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "bigDecimalIdJPAEntity");
//    assertTrue(bigDecimalIdResult instanceof BigDecimalIdJPAEntity);
//    assertEquals(bigDecimalIdJPAEntity.getBigDecimalId(), ((BigDecimalIdJPAEntity) bigDecimalIdResult).getBigDecimalId());
//
//    Object bigIntegerIdResult = runtimeService.getVariable(processInstanceAllTypes.getId(), "bigIntegerIdJPAEntity");
//    assertTrue(bigIntegerIdResult instanceof BigIntegerIdJPAEntity);
//    assertEquals(bigIntegerIdJPAEntity.getBigIntegerId(), ((BigIntegerIdJPAEntity) bigIntegerIdResult).getBigIntegerId());
//  }
//
//  //@TODO: fix Caused by: org.h2.jdbc.JdbcSQLException: Value too long for column "ID_ DECIMAL(19, 2) NOT NULL": "12345678912345678900000.12 (25)"; SQL statement:
//  @Deployment(resources = { "org/activiti/standalone/jpa/JPAVariableTest.testStoreJPAEntityAsVariable.bpmn20.xml" })
//  public void testStoreJPAEntityListAsVariable() {
//    setupJPAEntities();
//    // -----------------------------------------------------------------------------
//    // Simple test, Start process with lists of JPA entities as variables
//    // -----------------------------------------------------------------------------
//    Map<String, Object> variables = new HashMap<String, Object>();
//    variables.put("simpleEntityFieldAccess", Arrays.asList(simpleEntityFieldAccess, simpleEntityFieldAccess, simpleEntityFieldAccess));
//    variables.put("simpleEntityPropertyAccess", Arrays.asList(simpleEntityPropertyAccess, simpleEntityPropertyAccess, simpleEntityPropertyAccess));
//    variables.put("subclassFieldAccess", Arrays.asList(subclassFieldAccess, subclassFieldAccess, subclassFieldAccess));
//    variables.put("subclassPropertyAccess", Arrays.asList(subclassPropertyAccess, subclassPropertyAccess, subclassPropertyAccess));
//
//    // Start the process with the JPA-entities as variables. They will be
//    // stored in the DB.
//    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("JPAVariableProcess", variables);
//
//    // Read entity with @Id on field
//    Object fieldAccessResult = runtimeService.getVariable(processInstance.getId(), "simpleEntityFieldAccess");
//    assertTrue(fieldAccessResult instanceof List<?>);
//    List<?> list = (List<?>) fieldAccessResult;
//    assertEquals(3L, list.size());
//    assertTrue(list.get(0) instanceof FieldAccessJPAEntity);
//    assertEquals(((FieldAccessJPAEntity) list.get(0)).getId(), simpleEntityFieldAccess.getId());
//
//    // Read entity with @Id on property
//    Object propertyAccessResult = runtimeService.getVariable(processInstance.getId(), "simpleEntityPropertyAccess");
//    assertTrue(propertyAccessResult instanceof List<?>);
//    list = (List<?>) propertyAccessResult;
//    assertEquals(3L, list.size());
//    assertTrue(list.get(0) instanceof PropertyAccessJPAEntity);
//    assertEquals(((PropertyAccessJPAEntity) list.get(0)).getId(), simpleEntityPropertyAccess.getId());
//
//    // Read entity with @Id on field of mapped superclass
//    Object subclassFieldResult = runtimeService.getVariable(processInstance.getId(), "subclassFieldAccess");
//    assertTrue(subclassFieldResult instanceof List<?>);
//    list = (List<?>) subclassFieldResult;
//    assertEquals(3L, list.size());
//    assertTrue(list.get(0) instanceof SubclassFieldAccessJPAEntity);
//    assertEquals(((SubclassFieldAccessJPAEntity) list.get(0)).getId(), simpleEntityPropertyAccess.getId());
//
//    // Read entity with @Id on property of mapped superclass
//    Object subclassPropertyResult = runtimeService.getVariable(processInstance.getId(), "subclassPropertyAccess");
//    assertTrue(subclassPropertyResult instanceof List<?>);
//    list = (List<?>) subclassPropertyResult;
//    assertEquals(3L, list.size());
//    assertTrue(list.get(0) instanceof SubclassPropertyAccessJPAEntity);
//    assertEquals(((SubclassPropertyAccessJPAEntity) list.get(0)).getId(), simpleEntityPropertyAccess.getId());
//  }
//
//  //@TODO: fix Caused by: org.h2.jdbc.JdbcSQLException: Concurrent update in table "JPA_ENTITY_FIELD": another transaction has updated or deleted the same row [90131-193]
//  @Deployment(resources = { "org/activiti/standalone/jpa/JPAVariableTest.testStoreJPAEntityAsVariable.bpmn20.xml" })
//  public void testStoreJPAEntityListAsVariableEdgeCases() {
//    setupJPAEntities();
//
//    // Test using mixed JPA-entities which are not serializable, should not
//    // be picked up by JPA list type en therefor fail
//    // due to serialization error
//    Map<String, Object> variables = new HashMap<String, Object>();
//    variables.put("simpleEntityFieldAccess", Arrays.asList(simpleEntityFieldAccess, simpleEntityPropertyAccess));
//
//    try {
//      runtimeService.startProcessInstanceByKey("JPAVariableProcess", variables);
//      fail("Exception expected");
//    } catch (ActivitiException ae) {
//      // Expected
//    }
//
//    // Test updating value to an empty list and back
//    variables = new HashMap<String, Object>();
//    variables.put("list", Arrays.asList(simpleEntityFieldAccess, simpleEntityFieldAccess));
//
//    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("JPAVariableProcess", variables);
//
//    runtimeService.setVariable(processInstance.getId(), "list", new ArrayList<String>());
//    assertEquals(0L, ((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).size());
//
//    runtimeService.setVariable(processInstance.getId(), "list", Arrays.asList(simpleEntityFieldAccess, simpleEntityFieldAccess));
//    assertEquals(2L, ((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).size());
//    assertTrue(((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).get(0) instanceof FieldAccessJPAEntity);
//
//    // Test updating to list of Strings
//    runtimeService.setVariable(processInstance.getId(), "list", Arrays.asList("TEST", "TESTING"));
//    assertEquals(2L, ((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).size());
//    assertTrue(((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).get(0) instanceof String);
//
//    runtimeService.setVariable(processInstance.getId(), "list", Arrays.asList(simpleEntityFieldAccess, simpleEntityFieldAccess));
//    assertEquals(2L, ((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).size());
//    assertTrue(((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).get(0) instanceof FieldAccessJPAEntity);
//
//    // Test updating to null
//    runtimeService.setVariable(processInstance.getId(), "list", null);
//    assertNull(runtimeService.getVariable(processInstance.getId(), "list"));
//
//    runtimeService.setVariable(processInstance.getId(), "list", Arrays.asList(simpleEntityFieldAccess, simpleEntityFieldAccess));
//    assertEquals(2L, ((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).size());
//    assertTrue(((List<?>) runtimeService.getVariable(processInstance.getId(), "list")).get(0) instanceof FieldAccessJPAEntity);
//  }

    @Deployment(resources="org/activiti/standalone/jpa/JPAVariableTest.testQueryJPAVariable.bpmn20.xml") public void testReplaceExistingJPAEntityWithAnotherOfSameType(){EntityManager manager=entityManagerFactory.createEntityManager();manager.getTransaction().begin();FieldAccessJPAEntity oldVariable=new FieldAccessJPAEntity();oldVariable.setId(11L);oldVariable.setValue("value1");manager.persist(oldVariable);FieldAccessJPAEntity newVariable=new FieldAccessJPAEntity();newVariable.setId(12L);newVariable.setValue("value2");manager.persist(newVariable);manager.flush();manager.getTransaction().commit();manager.close();ProcessInstance processInstance=runtimeService.startProcessInstanceByKey("JPAVariableProcess");String executionId=processInstance.getId();String variableName="testVariable";runtimeService.setVariable(executionId,variableName,oldVariable);runtimeService.setVariable(executionId,variableName,newVariable);Object variable=runtimeService.getVariable(executionId,variableName);assertEquals(newVariable.getId(),((FieldAccessJPAEntity)variable).getId());}
}
