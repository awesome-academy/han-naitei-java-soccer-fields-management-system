package app.controller;

import app.info.FieldTypeInfo;
import app.model.FieldType;
import app.service.FieldTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FieldTypeController {
    private static final Logger logger = Logger.getLogger(FieldTypeController.class);

    private final FieldTypeService fieldTypeService;

    @Autowired
    public FieldTypeController(FieldTypeService fieldTypeService) {
        this.fieldTypeService = fieldTypeService;
    }

    @GetMapping(path = "/field-types")
    public ModelAndView index() {
        logger.info("Index");
        ModelAndView model = new ModelAndView("views/field-types/index");
        String title = "Field Type List";

        model.addObject("title", title);
        model.addObject("data", fieldTypeService.loadFieldTypes());
        return model;
    }

    @GetMapping(path = "/field-types/{id}")
    public String show(@PathVariable("id") int id, Model model, final RedirectAttributes redirectAttributes) {
        logger.info("Show");
        String title = "Field Type Details";

        FieldType fieldType = fieldTypeService.findById(id, false);
        if (fieldType == null) {
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "Field type not found.");
            return "redirect:/field-types";
        }

        model.addAttribute("title", title);
        model.addAttribute("data", fieldType);

        return "views/field-types/show";
    }

    @GetMapping(path = "/field-types/create")
    public String create(Model model) {
        logger.info("Create");
        String title = "Create New Field Type";

        FieldTypeInfo fieldTypeInfo = new FieldTypeInfo();

        model.addAttribute("title", title);
        model.addAttribute("fieldTypeForm", fieldTypeInfo);

        return "views/field-types/create";
    }

    @GetMapping(path = "/field-types/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        logger.info("Edit");
        String title = "Edit Field Type";

        FieldType fieldType = fieldTypeService.findById(id, true);

        FieldTypeInfo fieldTypeInfo = new FieldTypeInfo(fieldType.getId(), fieldType.getName(), fieldType.getDescription(), fieldType.getPhotoURL());

        model.addAttribute("title", title);
        model.addAttribute("fieldTypeForm", fieldTypeInfo);

        return "views/field-types/edit";
    }

    @GetMapping(path = "/field-types/{id}/delete")
    public String delete(@PathVariable("id") int id, final RedirectAttributes redirectAttributes) {
        logger.info("DELETE");

        if (fieldTypeService.deleteFieldType(id)) {
            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", "Field type deleted.");
        } else {
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "Error deleting field type.");
        }

        return "redirect:/field-types";
    }

    @PostMapping(path = "/field-types")
    public String post(FieldTypeInfo fieldTypeInfo, final RedirectAttributes redirectAttributes) {
        logger.info("POST");

        fieldTypeInfo.setId(null);

        if (fieldTypeService.saveOrUpdate(fieldTypeInfo.toFieldType())) {
            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", "Field type created.");
        } else {
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "Error deleting field type.");
        }

        return "redirect:/field-types";
    }

    @PutMapping(path = "/field-types/{id}")
    public String put(@PathVariable("id") int id, FieldTypeInfo fieldTypeInfo, final RedirectAttributes redirectAttributes) {
        logger.info("PUT");

        if (fieldTypeService.saveOrUpdate(fieldTypeInfo.toFieldType())) {
            redirectAttributes.addFlashAttribute("status", "success");
            redirectAttributes.addFlashAttribute("message", "Field type details updated.");
        } else {
            redirectAttributes.addFlashAttribute("status", "error");
            redirectAttributes.addFlashAttribute("message", "Error updating field type details.");
        }

        return "redirect:/field-types/" + id;
    }
}
