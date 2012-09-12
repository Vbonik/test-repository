package com.issoft.database.log;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: AS
 */
public class EntryController extends MultiActionController {

    private EntryDAO entryDAO;

    public void setEntryDAO(EntryDAO entryDAO) {
        this.entryDAO = entryDAO;
    }

    public ModelAndView add(HttpServletRequest request,
                            HttpServletResponse response, Entry entry) throws Exception {
       // entryDAO.saveEntry();
        return new ModelAndView("redirect:list.htm");
    }

    public ModelAndView list(HttpServletRequest request,
                             HttpServletResponse response) throws Exception {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("entryList", entryDAO.listEntry());
        modelMap.addAttribute("entry", new Entry());
        return new ModelAndView("entryForm", modelMap);
    }
}
