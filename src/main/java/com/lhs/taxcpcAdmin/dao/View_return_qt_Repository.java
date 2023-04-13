package com.lhs.taxcpcAdmin.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lhs.taxcpcAdmin.dao.generic.GenericCustomRepository;
import com.lhs.taxcpcAdmin.model.entity.View_return_qt;

	public interface View_return_qt_Repository  extends JpaRepository<View_return_qt,String> , GenericCustomRepository<String,View_return_qt>{

}
