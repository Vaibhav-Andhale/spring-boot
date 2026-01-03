package com.vaibhav.project.repository.user;

import com.vaibhav.project.entities.user.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDetailsRepo extends JpaRepository<UserDetails,String> {
}
