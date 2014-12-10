package org.balthie.demo.my.quartz.execute;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public abstract class SimpleRecoveryStatefulJob extends SimpleRecoveryJob
{
}