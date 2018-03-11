package com.mondia.web.rest.error;

public final class ErrorConstants {

	private ErrorConstants() {
	}

	public static final class SERVICE {
		public static final String OPERATOR_SERVICE_ID_MANDATORY = "S0001";
		public static final String OPERATOR_PACKAGE_ID_MANDATORY = "S0002";
		public static final String OPERATOR_PACKAGE_AND_SERVICE_ID_MANDATORY = "S0003";

	}

	public static class VALIDATION {
		public static final String DUPLICATE_RECORD = "V001";
	}


}
