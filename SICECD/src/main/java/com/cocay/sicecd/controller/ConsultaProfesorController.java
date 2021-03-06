package com.cocay.sicecd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cocay.sicecd.model.Profesor;
import com.cocay.sicecd.repo.ProfesorRep;

@Controller
public class ConsultaProfesorController {

	@Autowired
	ProfesorRep profesor;

	@RequestMapping(value = "/consultarProfesor", method = RequestMethod.GET)
	public String consultaProfesor(Model model) {
		return "ConsultarProfesor/consultaProfesor";
	}

	@RequestMapping(value = "/consultarProfesorRFC", method = RequestMethod.POST)
	public ModelAndView consultarProfesor(ModelMap model, HttpServletRequest request) {
		String rfcs = request.getParameter("rfc");
		Profesor p = profesor.findByRfc(rfcs);
		System.out.println(rfcs);
		if (p != null) {
			System.out.println(p.getCorreo());
			System.out.println(p.getRfc());
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("apellido_paterno", p.getApellido_paterno());
			model.addAttribute("apellido_materno", p.getApellido_materno());
			model.addAttribute("correo", p.getCorreo());
			model.addAttribute("rfc", p.getRfc());
			model.addAttribute("estado", p.getFk_id_estado().getNombre());
			model.addAttribute("grado", p.getFk_id_grado_profesor().getNombre());
			model.addAttribute("clave-plantel", p.getClave_plantel());
			model.addAttribute("ocupacion", p.getOcupacion());
			model.addAttribute("inscripcion", p.getInscripciones());
			return new ModelAndView("/ConsultarProfesor/muestraProfesor", model);
		} else {
			return new ModelAndView("/ConsultarProfesor/consultaProfesor");
		}
	}

	@RequestMapping(value = "/consultarProfesorNombre", method = RequestMethod.POST)
	public ModelAndView consultarProfesornoNombre(ModelMap model, HttpServletRequest request) {
		String nombre = request.getParameter("nombre");
		String apellido_paterno = request.getParameter("apellido_paterno");
		String apellido_materno = request.getParameter("apellido_materno");
		if (nombre == null) {
			nombre = "";
		} else {
			nombre = nombre.toUpperCase();
		}
		if (apellido_paterno == null) {
			apellido_paterno = "";
		} else {
			apellido_paterno = apellido_paterno.toUpperCase();
		}
		if (apellido_materno == null) {
			apellido_materno = "";
		} else {
			apellido_materno = apellido_materno.toUpperCase();
		}
		Profesor p = profesor.findByCompleteName(nombre, apellido_paterno, apellido_materno);
		if (p != null) {
			model.addAttribute("nombre", p.getNombre());
			model.addAttribute("apellido_paterno", p.getApellido_paterno());
			model.addAttribute("apellido_materno", p.getApellido_materno());
			model.addAttribute("correo", p.getCorreo());
			model.addAttribute("rfc", p.getRfc());
			model.addAttribute("estado", p.getFk_id_estado().getNombre());
			model.addAttribute("grado", p.getFk_id_grado_profesor().getNombre());
			model.addAttribute("clave-plantel", p.getClave_plantel());
			model.addAttribute("ocupacion", p.getOcupacion());
			model.addAttribute("inscripcion", p.getInscripciones());
			return new ModelAndView("/ConsultarProfesor/muestraProfesor", model);

		} else {
			return new ModelAndView("/ConsultarProfesor/consultaProfesor");

		}
	}
}
