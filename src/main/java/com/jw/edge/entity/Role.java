package com.jw.edge.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Role {
    @Id
    private String roleId;
    private String roleName;

}
