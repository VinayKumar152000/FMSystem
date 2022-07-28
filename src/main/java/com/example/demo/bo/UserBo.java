package com.example.demo.bo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBo {

	private int id;
	private String name;
	private String address;
	private String number;
	private String email;
	private List<RoleBo> roles;
}
