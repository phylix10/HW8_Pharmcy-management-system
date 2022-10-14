package com.alireza.service;

import com.alireza.model.Admin;
import com.alireza.repository.AdminRepository;

import java.util.List;

public class AdminService {

    public boolean createAdmin(Admin admin){
        if (AdminRepository.findAdmin(admin.getUsername(), admin.getNationalCode()) == null){
            AdminRepository.createAdmin(admin);
            return true;
        }
        else {
            return false;
        }
    }

    public void updateAdmin(Admin admin){
        AdminRepository.updateAdmin(admin);
    }

    public Admin findAdmin(String username, String nationalCode){
        return AdminRepository.findAdmin(username, nationalCode);
    }

    public void deleteAdmin(Admin admin){
        AdminRepository.deleteAdminById(admin);
    }

    public Admin findSuperAdmin(String username){
        return AdminRepository.findSuperAdmin(username);
    }

    public List<Admin> showAllAdmin(){
        return AdminRepository.showAllAdmin();
    }
}
