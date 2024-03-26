package com.example.diplomaProject.service.dynamicDb;

import com.example.diplomaProject.domain.api.allDbData.TableResp;
import com.example.diplomaProject.domain.api.constructor.QueryField;
import com.example.diplomaProject.domain.api.constructor.QueryResp;
import com.example.diplomaProject.domain.mapper.connDb.ConnDbMapper;
import com.example.diplomaProject.repository.ActionRepository;
import com.example.diplomaProject.repository.TableRepository;
import com.example.diplomaProject.repository.UserRepository;
import com.example.diplomaProject.service.connectedDB.ConnectedDbService;
import com.example.diplomaProject.service.security.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class DynamicDbServiceImplTest {

//    @InjectMocks
//    private DynamicDbServiceImpl dynamicDbService;
//
//    @Mock
//    private ConnectedDbService connectedDbService;
//    @Mock
//    private ConnDbMapper connDbMapper;
//    @Mock
//    private ActionRepository actionRepository;
//    @Mock
//    private TableRepository tableRepository;
//    @Mock
//    private JwtService jwtService;
//    @Mock
//    private UserRepository userRepository;
//
//
//    @Test
//    void getAllTest() {
//
//        TableResp table1 = new TableResp("Movie",
//                new ArrayList<QueryResp>(new ArrayList<QueryField>(
//                        new QueryField("title", "Star wars"))));
//
//        Mockito.when()
//
//
//    }

}
