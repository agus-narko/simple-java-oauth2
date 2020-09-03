package com.agus.java.model.userManagement;

import org.springframework.stereotype.Repository;

import com.agus.java.model.userManagement.UserEntity;
import com.agus.java.model.BaseCoreDaoImpl;;

@Repository
public class ClientDaoImpl extends BaseCoreDaoImpl<Client, Long> implements ClientDao {

}