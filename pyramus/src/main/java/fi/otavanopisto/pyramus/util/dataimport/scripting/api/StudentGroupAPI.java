package fi.otavanopisto.pyramus.util.dataimport.scripting.api;

import java.util.Date;

import fi.otavanopisto.pyramus.dao.DAOFactory;
import fi.otavanopisto.pyramus.domainmodel.users.User;
import fi.otavanopisto.pyramus.util.dataimport.scripting.InvalidScriptException;

public class StudentGroupAPI {
  
  public StudentGroupAPI(Long loggedUserId) {
    this.loggedUserId = loggedUserId;
  }
  
  public Long create(String name, String description, Date beginDate) throws InvalidScriptException {
    User loggedUser = DAOFactory.getInstance().getStaffMemberDAO().findById(loggedUserId);
    if (loggedUser == null) {
      throw new InvalidScriptException("Logged user could not be found");  
    }
    
    return (DAOFactory.getInstance().getStudentGroupDAO().create(name, description, beginDate, loggedUser, false).getId());
  }

  private Long loggedUserId;
}
