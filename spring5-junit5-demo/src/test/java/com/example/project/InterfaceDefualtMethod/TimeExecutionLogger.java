package com.example.project.InterfaceDefualtMethod;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.project.lifecycle.TimingExtension;

//The following example shows how to use these callbacks to calculate and log the execution time of a test method. 
//TimingExtension implements both BeforeTestExecutionCallback and AfterTestExecutionCallback in order to time and log the test execution.
@Tag("timed")
@ExtendWith(TimingExtension.class)
interface TimeExecutionLogger {
}