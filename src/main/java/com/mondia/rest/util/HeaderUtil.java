package com.mondia.rest.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

/**
 * Utility class for HTTP headers creation.
 */
public final class HeaderUtil {
	

	private static final Logger	log					= LoggerFactory.getLogger(HeaderUtil.class);

	private static final String	APPLICATION_NAME	= "hypercellApp";

	private HeaderUtil() {
	}

	public static HttpHeaders createAlert(String message, String param) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-hypercellApp-alert", message);
		headers.add("X-hypercellApp-params", param);
		return headers;
	}

	public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
		return createAlert(APPLICATION_NAME + "." + entityName + ".created", param);
	}

	public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
		return createAlert(APPLICATION_NAME + "." + entityName + ".updated", param);
	}

	public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
		return createAlert(APPLICATION_NAME + "." + entityName + ".deleted", param);
	}

	public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
		log.error("Entity processing failed, {}", defaultMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-hypercellApp-error", "error." + errorKey);
		headers.add("X-hypercellApp-params", entityName);
		return headers;
	}

	public static HttpHeaders createExceptionAlert(String errorKey) {
		log.error("Entity processing for exception with error code {}", errorKey);
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-hypercellApp-error", "error." + errorKey);
		headers.add("X-hypercellApp-params", "error");
		return headers;
	}

	public static HttpHeaders createAlert(String message, Long id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-hypercellApp-alert", message);
		String convert = id.toString();
		headers.add("X-hypercellApp-params", convert);
		return headers;
	}
}
