/*package com.customer.management.tool.configuration;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class Cipher {

	public String encode(String data) {

		String encoded = null;
		if (!StringUtils.isEmpty(data)) {
			byte[] authBytes = data.getBytes(StandardCharsets.UTF_8);
			encoded = Base64.getEncoder().encodeToString(authBytes);
		}
		return encoded;
	}

	public String decode(String data) {

		byte[] decoder = null;
		if (!StringUtils.isEmpty(data)) {
			decoder = Base64.getDecoder().decode(data);
		}
		return new String(decoder, StandardCharsets.UTF_8);
	}
}
*/