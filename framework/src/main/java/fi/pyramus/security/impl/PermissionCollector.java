package fi.pyramus.security.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import fi.pyramus.dao.security.EnvironmentRolePermissionDAO;
import fi.pyramus.dao.security.PermissionDAO;
import fi.pyramus.dao.users.RoleEntityDAO;
import fi.pyramus.domainmodel.security.Permission;
import fi.pyramus.domainmodel.users.RoleEntity;

@Singleton
@Startup
public class PermissionCollector {
  
  @Inject
  @Any
  private Instance<PyramusPermissionCollection> permissionCollections;

  @Inject
  private PermissionDAO permissionDAO;
  
  @Inject
  private RoleEntityDAO roleEntityDAO;
  
  @Inject
  private EnvironmentRolePermissionDAO environmentRolePermissionDAO;
  
  @PostConstruct
  private void collectPermissions() {
    for (PyramusPermissionCollection collection : permissionCollections) {
      List<String> permissions = collection.listPermissions();

      for (String permissionName : permissions) {
        Permission permission = permissionDAO.findByName(permissionName);
        
        if (permission == null) {
          try {
            String permissionScope = collection.getPermissionScope(permissionName);

            permission = permissionDAO.create(permissionName, permissionScope);
            
            String[] defaultRoles = collection.getDefaultRoles(permissionName);
            
            if (defaultRoles != null) {
              switch (permissionScope) {
                case PermissionScope.ENVIRONMENT:
                  for (int i = 0; i < defaultRoles.length; i++) {
                    String roleName = defaultRoles[i];
                    RoleEntity roleEntity = roleEntityDAO.findByName(roleName);
                    
                    environmentRolePermissionDAO.create(roleEntity, permission);
                  }
                break;
                
//                case PermissionScope.WORKSPACE:
//                  List<WorkspaceEntity> workspaces = workspaceEntityDAO.listAll();
//                  WorkspaceSettingsTemplate workspaceSettingsTemplate = workspaceSettingsTemplateDAO.findById(1l); 
//                  
//                  for (int i = 0; i < defaultRoles.length; i++) {
//                    String roleName = defaultRoles[i];
//                    RoleEntity roleEntity = roleEntityDAO.findByName(roleName);
//
//                    workspaceSettingsTemplateRolePermissionDAO.create(workspaceSettingsTemplate, roleEntity, permission);
//
//                    // TODO Workspace creation & templates - is this necessary and bulletproof?
//                    for (WorkspaceEntity workspace: workspaces) {
//                      workspaceRolePermissionDAO.create(workspace, roleEntity, permission);
//                    }
//                  }
//                break;
//                
//                case PermissionScope.USERGROUP:
//                  List<UserGroup> userGroups = userGroupDAO.listAll();
//                  
//                  for (int i = 0; i < defaultRoles.length; i++) {
//                    String roleName = defaultRoles[i];
//                    RoleEntity roleEntity = roleEntityDAO.findByName(roleName);
//
//                    // TODO Workspace creation & templates - is this necessary and bulletproof?
//                    for (UserGroup userGroup: userGroups) {
//                      userGroupRolePermissionDAO.create(userGroup, roleEntity, permission);
//                    }
//                  }
//                break;
              }
            }
            
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
  
}