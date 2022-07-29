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

package org.activiti.spring.test.transaction;

import javax.sql.DataSource;

import org.activiti.bpmn.exceptions.XMLException;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.test.Deployment;
import org.activiti.spring.impl.test.SpringActivitiTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;

/**

 */
@ContextConfiguration("classpath:org/activiti/spring/test/transaction/SpringTransactionIntegrationTest-context.xml")
public class SpringTransactionIntegrationTest extends SpringActivitiTestCase {

  @Autowired
  protected UserBean userBean;

  @Autowired
  protected DeployBean deployBean;

  @Autowired
  protected DataSource dataSource;

  @Deployment public void testRollbackTransactionOnActivitiException(){JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);jdbcTemplate.execute("create table MY_TABLE (MY_TEXT varchar);");userBean.hello();assertEquals(Long.valueOf(0),jdbcTemplate.queryForObject("select count(*) from MY_TABLE",Long.class));try {userBean.completeTask(taskService.createTaskQuery().singleResult().getId());fail();} catch (Exception e){}assertEquals("My Task",taskService.createTaskQuery().singleResult().getName());assertEquals(Long.valueOf(0),jdbcTemplate.queryForObject("select count(*) from MY_TABLE",Long.class));jdbcTemplate.execute("drop table MY_TABLE if exists;");}

}
