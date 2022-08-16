package org.activiti.spring.boot;

import org.activiti.api.runtime.shared.identity.UserGroupManager;
import org.activiti.api.runtime.shared.security.SecurityManager;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ContextConfiguration
public class RuntimeConfigurationTest {


    @Autowired
    private SecurityManager securityManager;

    @Autowired
    private UserGroupManager userGroupManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Test @WithUserDetails(value="admin", userDetailsServiceBeanName="myUserDetailsService") public void validatingConfigurationForAdmin(){String authenticatedUserId=securityManager.getAuthenticatedUserId();assertThat(authenticatedUserId).isNotBlank();UserDetails userDetails=userDetailsService.loadUserByUsername(authenticatedUserId);assertThat(userDetails).isNotNull();assertThat(userDetails.getAuthorities()).hasSize(1);List<String> userRoles=userGroupManager.getUserRoles(authenticatedUserId);assertThat(userRoles).isNotNull();assertThat(userRoles).hasSize(1);assertThat(userRoles.get(0)).isEqualTo("ACTIVITI_ADMIN");List<String> userGroups=userGroupManager.getUserGroups(authenticatedUserId);assertThat(userGroups).isNotNull();assertThat(userGroups).hasSize(0);}

}
