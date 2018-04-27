package com.atnt.rest.json;

public class ConsumeRestJSON {

	//Program argument takes only one argument Rest Service URl 
	// Example : http://dummy.restapiexample.com/api/v1/employees
	public static void main(String[] args) {

		String restWSUrl = "";
		ConsumeJSONHelper consumeJSONHelper;

		if (null != args && args.length == 1) {

			restWSUrl = args[0];

			consumeJSONHelper = new ConsumeJSONHelper();

			try {
				consumeJSONHelper.consumRestEndpoint(restWSUrl);
			} catch (Exception ex) {
				System.out.println("There is some problem while executing the program. Please fix it " + ex);
			}

		}else {
			System.out.println("Please specify a valid REST Service URL as mentioned in the Test");
		}

	}

}
