package com.issoft.log.mailer;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Quartz job that sends e-mail notifications.
 *
 * @author: AS
 */
public class QuartzMailJob extends QuartzJobBean {
    private MailService runMeTask;

    public void setRunMeTask(MailService runMeTask) {
        this.runMeTask = runMeTask;
    }

    protected void executeInternal(JobExecutionContext context)
            throws JobExecutionException {
        runMeTask.prepareMail();
    }
}
