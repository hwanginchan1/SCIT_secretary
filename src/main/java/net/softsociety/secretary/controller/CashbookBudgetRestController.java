package net.softsociety.secretary.controller;

import java.util.Calendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.secretary.dao.CashbookDAO;
import net.softsociety.secretary.dao.UserMapper;
import net.softsociety.secretary.domain.Budget;
import net.softsociety.secretary.domain.User;
import net.softsociety.secretary.service.CashbookService;

@Slf4j
@RequestMapping("/cashbook/budget")
@RestController
public class CashbookBudgetRestController {

	@Autowired
	CashbookService service;
	
	@Autowired
	CashbookDAO dao;
	
	@Autowired
	UserMapper userdao;
	
	
	/** 예산 입력 */
	@PostMapping("setBudget")
	public void setBudget(@AuthenticationPrincipal UserDetails user
			, Budget budget, Model model) {
		log.debug("컨트롤러에 넘어온 예산:{}", budget);
		
		// 현재 월 구하기
		Calendar calendar = Calendar.getInstance();
        int budgetMonth = calendar.get(Calendar.MONTH) + 1;
        int budgetYear = calendar.get(Calendar.YEAR);
		
		
		// 유저id 불러오기
		String userId = userdao.findByEmailOrUserId(user.getUsername()).getUserId();
		
		// 유저id로 familyId 찾기
		User loginUser = (User) model.getAttribute("loginUser");
		int familyId = loginUser.getFamilyId();
		
		// budget에 넣기
		budget.setFamilyId(familyId);
		budget.setCashbookId(familyId);
		budget.setBudgetYear(budgetYear);
		budget.setBudgetMonth(budgetMonth);
		log.debug("DAO에 넘길 budget:{}", budget);
		
		int n = dao.setBudget(budget);
	}
	
	/** 예산 수정 */
	@PostMapping("updateBudget")
	public void updateBudget(@AuthenticationPrincipal UserDetails user
			, Budget budget, Model model) {
		log.debug("컨트롤러에 넘어온 수정할 예산:{}", budget);
		
		// 현재 월 구하기
		Calendar calendar = Calendar.getInstance();
		int budgetMonth = calendar.get(Calendar.MONTH) + 1;
		int budgetYear = calendar.get(Calendar.YEAR);
		
		
		// 유저id 불러오기
		String userId = userdao.findByEmailOrUserId(user.getUsername()).getUserId();
		
		User loginUser = (User) model.getAttribute("loginUser");
		int familyId = loginUser.getFamilyId();
		
		// budget에 넣기
		budget.setFamilyId(familyId);
		budget.setBudgetYear(budgetYear);
		budget.setBudgetMonth(budgetMonth);
		log.debug("DAO에 넘길 budget:{}", budget);
		
		int n = dao.updateBudget(budget);
	}
	
	/** 예산 삭제 */
	@PostMapping("deleteBudget")
	public void deleteBudget(@AuthenticationPrincipal UserDetails user
			, int curYear
			, int  curMonth
			, Model model) {
		User loginUser = (User) model.getAttribute("loginUser");
		int familyId = loginUser.getFamilyId();
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("familyId", familyId);
		map.put("budgetYear", curYear);
		map.put("budgetMonth", curMonth);
		Budget budget = dao.selectBudget(map);
		
		int n = dao.deleteBudget(budget);
		
	}
	
}
