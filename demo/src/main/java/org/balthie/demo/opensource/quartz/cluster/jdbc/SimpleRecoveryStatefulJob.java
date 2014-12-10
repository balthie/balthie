package org.balthie.demo.opensource.quartz.cluster.jdbc;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class SimpleRecoveryStatefulJob extends SimpleRecoveryJob
{
}