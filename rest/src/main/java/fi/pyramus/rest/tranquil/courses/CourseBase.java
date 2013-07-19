package fi.pyramus.rest.tranquil.courses;

import fi.tranquil.TranquilModel;
import fi.tranquil.TranquilModelType;

@TranquilModel (entityClass = fi.pyramus.domainmodel.courses.Course.class, entityType = TranquilModelType.BASE)
public class CourseBase implements fi.tranquil.TranquilModelEntity {

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public java.util.Date getCreated() {
    return created;
  }

  public void setCreated(java.util.Date created) {
    this.created = created;
  }

  public java.util.Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(java.util.Date lastModified) {
    this.lastModified = lastModified;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Boolean getArchived() {
    return archived;
  }

  public void setArchived(Boolean archived) {
    this.archived = archived;
  }

  public Integer getCourseNumber() {
    return courseNumber;
  }

  public void setCourseNumber(Integer courseNumber) {
    this.courseNumber = courseNumber;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Long getMaxParticipantCount() {
    return maxParticipantCount;
  }

  public void setMaxParticipantCount(Long maxParticipantCount) {
    this.maxParticipantCount = maxParticipantCount;
  }

  public java.util.Date getBeginDate() {
    return beginDate;
  }

  public void setBeginDate(java.util.Date beginDate) {
    this.beginDate = beginDate;
  }

  public java.util.Date getEndDate() {
    return endDate;
  }

  public void setEndDate(java.util.Date endDate) {
    this.endDate = endDate;
  }

  public String getNameExtension() {
    return nameExtension;
  }

  public void setNameExtension(String nameExtension) {
    this.nameExtension = nameExtension;
  }

  public Double getLocalTeachingDays() {
    return localTeachingDays;
  }

  public void setLocalTeachingDays(Double localTeachingDays) {
    this.localTeachingDays = localTeachingDays;
  }

  public Double getTeachingHours() {
    return teachingHours;
  }

  public void setTeachingHours(Double teachingHours) {
    this.teachingHours = teachingHours;
  }

  public Double getDistanceTeachingDays() {
    return distanceTeachingDays;
  }

  public void setDistanceTeachingDays(Double distanceTeachingDays) {
    this.distanceTeachingDays = distanceTeachingDays;
  }

  public Double getAssessingHours() {
    return assessingHours;
  }

  public void setAssessingHours(Double assessingHours) {
    this.assessingHours = assessingHours;
  }

  public Double getPlanningHours() {
    return planningHours;
  }

  public void setPlanningHours(Double planningHours) {
    this.planningHours = planningHours;
  }

  public java.util.Date getEnrolmentTimeEnd() {
    return enrolmentTimeEnd;
  }

  public void setEnrolmentTimeEnd(java.util.Date enrolmentTimeEnd) {
    this.enrolmentTimeEnd = enrolmentTimeEnd;
  }

  private Long id;

  private String name;

  private java.util.Date created;

  private java.util.Date lastModified;

  private String description;

  private Boolean archived;

  private Integer courseNumber;

  private Long version;

  private Long maxParticipantCount;

  private java.util.Date beginDate;

  private java.util.Date endDate;

  private String nameExtension;

  private Double localTeachingDays;

  private Double teachingHours;

  private Double distanceTeachingDays;

  private Double assessingHours;

  private Double planningHours;

  private java.util.Date enrolmentTimeEnd;

  public final static String[] properties = {"id","name","created","lastModified","description","archived","courseNumber","version","maxParticipantCount","beginDate","endDate","nameExtension","localTeachingDays","teachingHours","distanceTeachingDays","assessingHours","planningHours","enrolmentTimeEnd"};
}