package fi.pyramus.plugin.muikku;

import javax.servlet.jsp.PageContext;

import org.apache.commons.lang3.StringUtils;

import fi.pyramus.dao.DAOFactory;
import fi.pyramus.dao.system.SettingDAO;
import fi.pyramus.dao.system.SettingKeyDAO;
import fi.pyramus.domainmodel.courses.Course;
import fi.pyramus.domainmodel.system.Setting;
import fi.pyramus.domainmodel.system.SettingKey;
import fi.pyramus.plugin.PageHookContext;
import fi.pyramus.plugin.PageHookController;

public abstract class AbstractCourseBasicPageHookController implements PageHookController {

  @Override
  public void execute(PageHookContext pageHookContext) {
    String muikkuHost = getMuikkuHost();
    if (StringUtils.isNotBlank(muikkuHost)) {
      PageContext pageContext = pageHookContext.getPageContext();
      Object courseObject = pageContext.getRequest().getAttribute("course");
      if (courseObject instanceof Course) {
        Long courseId = ((Course) courseObject).getId();
        pageContext.getRequest().setAttribute("muikkuWorkspaceUrl", String.format("http://%s/pyramusWorkspaceRedirect?courseId=%d", muikkuHost, courseId));
        pageHookContext.setIncludeFtl("/plugin/muikku/editcoursebasichook.ftl");
      }
    }
  }
  
  private String getMuikkuHost() {
    SettingKeyDAO settingKeyDAO = DAOFactory.getInstance().getSettingKeyDAO();
    SettingDAO settingDAO = DAOFactory.getInstance().getSettingDAO();
    SettingKey key = settingKeyDAO.findByName("muikkuplugin.muikkuhost");
    if (key != null) {
      Setting setting = settingDAO.findByKey(key);
      if (setting != null) {
        return setting.getValue();
      }
    }
    
    return null;
  }
  
}
