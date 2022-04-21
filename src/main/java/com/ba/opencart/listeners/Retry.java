package com.ba.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
private int count =0;
private static int maxTry =3;
@Override
public boolean retry(ITestResult iTestResult) {
if(!iTestResult.isSuccess()) { //check if test not suceed
	if(count<maxTry) {//check if maxtry count is reached
		count++; //Increase the maxtry count by 1
		iTestResult.setStatus(ITestResult.FAILURE);//mark test as failed
		return true; //tells testng to re-run the test
		
	}
	else {
		iTestResult.setStatus(ITestResult.FAILURE);	//if maxcount reached marked as failed
	}
}else {
	iTestResult.setStatus(ITestResult.SUCCESS);//if test passes,testNG marks it as passed
}
return false;
}
}
