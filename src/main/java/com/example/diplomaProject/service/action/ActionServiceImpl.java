package com.example.diplomaProject.service.action;

import com.example.diplomaProject.domain.constant.Code;
import com.example.diplomaProject.domain.entity.Action;
import com.example.diplomaProject.domain.response.Response;
import com.example.diplomaProject.domain.response.SuccessResponse;
import com.example.diplomaProject.domain.response.error.Error;
import com.example.diplomaProject.domain.response.error.ErrorResponse;
import com.example.diplomaProject.repository.ActionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActionServiceImpl implements ActionService{

    private final ActionRepository actionRepository;

    @Override
    public ResponseEntity<Response> getRecent() {

        List<Action> actions = actionRepository.getRecentActions();
        if(actions == null) {
            return new ResponseEntity<>(ErrorResponse.builder().error(Error.builder()
                            .code(Code.NOT_FOUND)
                            .message("actions haven't found")
                    .build()).build(), HttpStatus.NOT_FOUND);
        } else {
          return new ResponseEntity<>(SuccessResponse.builder().data(actions).build(), HttpStatus.OK);
        }
    }
}
