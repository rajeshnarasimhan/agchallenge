package com.autogeneral.agchallenge.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * component for custom /status endpoint
 * @author rajesh
 *
 */
@Component
@Endpoint(id = "status")
public class StatusEndPoint {
	
	@ReadOperation
    public Map<String, String> getStatus() {
		Map<String, String> statusMap = new HashMap<>();
		statusMap.put("status", "healthy");
        return statusMap;
    }
}