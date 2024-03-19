package com.example.diplomaProject.service.action;

import com.example.diplomaProject.domain.response.Response;
import org.springframework.http.ResponseEntity;

public interface ActionService {

    ResponseEntity<Response> getRecent();

}
