package com.example.customer.management.services.rest.mapper;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;

@Component
public class JsonPatchMapper {

	public JsonPatchMapper(ObjectMapper objectMapper){
		this.objectMapper = objectMapper;
	}
	
	private final ObjectMapper objectMapper;

	public <T> T apply(JsonPatch jsonPatch, T target, Class<T> targetType) {
        JsonNode targetNode = objectMapper.convertValue(target, JsonNode.class);
        try {
            JsonNode patchedNode = jsonPatch.apply(targetNode);
            
            return objectMapper.treeToValue(patchedNode, targetType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to apply JSON patch", e);
        }
    }

}
