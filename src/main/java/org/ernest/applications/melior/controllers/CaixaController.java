package org.ernest.applications.melior.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CaixaController {

	@RequestMapping({ "/caixa", "/" })
	public String getDashboard(Model model) {

		return "caixa";
	}
}