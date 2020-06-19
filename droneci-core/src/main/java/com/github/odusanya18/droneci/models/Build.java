package com.github.odusanya18.droneci.models;

import java.util.List;

public class Build {
    private long id;
    private long repoId;
    private String trigger;
    private long number;
    private String status;
    private String event;
    private String action;
    private String link;
    private long timestamp;
    private String message;
    private String before;
    private String after;
    private String ref;
    private String sourceRepo;
    private String source;
    private String target;
    private String authorLogin;
    private String authorName;
    private String authorEmail;
    private String authorAvatar;
    private String sender;
    private long started;
    private long finished;
    private long created;
    private long updated;
    private long version;
    private List<Stage> stages;

    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public long getRepoId() { return repoId; }
    public void setRepoId(long value) { this.repoId = value; }

    public String getTrigger() { return trigger; }
    public void setTrigger(String value) { this.trigger = value; }

    public long getNumber() { return number; }
    public void setNumber(long value) { this.number = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getEvent() { return event; }
    public void setEvent(String value) { this.event = value; }

    public String getAction() { return action; }
    public void setAction(String value) { this.action = value; }

    public String getLink() { return link; }
    public void setLink(String value) { this.link = value; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long value) { this.timestamp = value; }

    public String getMessage() { return message; }
    public void setMessage(String value) { this.message = value; }

    public String getBefore() { return before; }
    public void setBefore(String value) { this.before = value; }

    public String getAfter() { return after; }
    public void setAfter(String value) { this.after = value; }

    public String getRef() { return ref; }
    public void setRef(String value) { this.ref = value; }

    public String getSourceRepo() { return sourceRepo; }
    public void setSourceRepo(String value) { this.sourceRepo = value; }

    public String getSource() { return source; }
    public void setSource(String value) { this.source = value; }

    public String getTarget() { return target; }
    public void setTarget(String value) { this.target = value; }

    public String getAuthorLogin() { return authorLogin; }
    public void setAuthorLogin(String value) { this.authorLogin = value; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String value) { this.authorName = value; }

    public String getAuthorEmail() { return authorEmail; }
    public void setAuthorEmail(String value) { this.authorEmail = value; }

    public String getAuthorAvatar() { return authorAvatar; }
    public void setAuthorAvatar(String value) { this.authorAvatar = value; }

    public String getSender() { return sender; }
    public void setSender(String value) { this.sender = value; }

    public long getStarted() { return started; }
    public void setStarted(long value) { this.started = value; }

    public long getFinished() { return finished; }
    public void setFinished(long value) { this.finished = value; }

    public long getCreated() { return created; }
    public void setCreated(long value) { this.created = value; }

    public long getUpdated() { return updated; }
    public void setUpdated(long value) { this.updated = value; }

    public long getVersion() { return version; }
    public void setVersion(long value) { this.version = value; }

    public List<Stage> getStages() { return stages; }
    public void setStages(List<Stage> value) { this.stages = value; }
}

class Stage {
    private long id;
    private long buildId;
    private long number;
    private String name;
    private String status;
    private boolean errignore;
    private long exitCode;
    private String machine;
    private String os;
    private String arch;
    private long started;
    private long stopped;
    private long created;
    private long updated;
    private long version;
    private boolean onSuccess;
    private boolean onFailure;
    private List<Step> steps;
    private List<String> dependsOn;

    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public long getBuildId() { return buildId; }
    public void setBuildId(long value) { this.buildId = value; }

    public long getNumber() { return number; }
    public void setNumber(long value) { this.number = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public boolean getErrignore() { return errignore; }
    public void setErrignore(boolean value) { this.errignore = value; }

    public long getExitCode() { return exitCode; }
    public void setExitCode(long value) { this.exitCode = value; }

    public String getMachine() { return machine; }
    public void setMachine(String value) { this.machine = value; }

    public String getOs() { return os; }
    public void setOs(String value) { this.os = value; }

    public String getArch() { return arch; }
    public void setArch(String value) { this.arch = value; }

    public long getStarted() { return started; }
    public void setStarted(long value) { this.started = value; }

    public long getStopped() { return stopped; }
    public void setStopped(long value) { this.stopped = value; }

    public long getCreated() { return created; }
    public void setCreated(long value) { this.created = value; }

    public long getUpdated() { return updated; }
    public void setUpdated(long value) { this.updated = value; }

    public long getVersion() { return version; }
    public void setVersion(long value) { this.version = value; }

    public boolean getOnSuccess() { return onSuccess; }
    public void setOnSuccess(boolean value) { this.onSuccess = value; }

    public boolean getOnFailure() { return onFailure; }
    public void setOnFailure(boolean value) { this.onFailure = value; }

    public List<Step> getSteps() { return steps; }
    public void setSteps(List<Step> value) { this.steps = value; }

    public List<String> getDependsOn() { return dependsOn; }
    public void setDependsOn(List<String> value) { this.dependsOn = value; }
}

class Step {
    private long id;
    private long stepId;
    private long number;
    private String name;
    private String status;
    private long exitCode;
    private long started;
    private long stopped;
    private long version;

    public long getId() { return id; }
    public void setId(long value) { this.id = value; }

    public long getStepId() { return stepId; }
    public void setStepId(long value) { this.stepId = value; }

    public long getNumber() { return number; }
    public void setNumber(long value) { this.number = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public long getExitCode() { return exitCode; }
    public void setExitCode(long value) { this.exitCode = value; }

    public long getStarted() { return started; }
    public void setStarted(long value) { this.started = value; }

    public long getStopped() { return stopped; }
    public void setStopped(long value) { this.stopped = value; }

    public long getVersion() { return version; }
    public void setVersion(long value) { this.version = value; }
}
